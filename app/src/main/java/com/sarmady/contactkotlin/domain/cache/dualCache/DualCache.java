package com.sarmady.contactkotlin.domain.cache.dualCache;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

public class DualCache implements Closeable {

    private static final int VALUES_PER_CACHE_ENTRY = 1;

    private final RamLruCache ramCacheLru;
    private DiskLruCache diskLruCache;
    private final int maxDiskSizeBytes;
    private final File diskCacheFolder;
    private final int appVersion;
    private final DualCacheRamMode ramMode;
    private final DualCacheDiskMode diskMode;
    private final CacheSerializer diskSerializer;
    private final CacheSerializer ramSerializer;
    private final DualCacheLock dualCacheLock = new DualCacheLock();

    DualCache(
            int appVersion,
            DualCacheRamMode ramMode,
            CacheSerializer ramSerializer,
            int maxRamSizeBytes,
            SizeOf sizeOf,
            DualCacheDiskMode diskMode,
            CacheSerializer diskSerializer,
            int maxDiskSizeBytes,
            File diskFolder
    ) {
        this.appVersion = appVersion;
        this.ramMode = ramMode;
        this.ramSerializer = ramSerializer;
        this.diskMode = diskMode;
        this.diskSerializer = diskSerializer;
        this.diskCacheFolder = diskFolder;

        switch (ramMode) {
            case ENABLE_WITH_SPECIFIC_SERIALIZER:
                this.ramCacheLru = new StringLruCache(maxRamSizeBytes);
                break;
            case ENABLE_WITH_REFERENCE:
                this.ramCacheLru = new ReferenceLruCache(maxRamSizeBytes, sizeOf);
                break;
            default:
                this.ramCacheLru = null;
        }

        switch (diskMode) {
            case ENABLE_WITH_SPECIFIC_SERIALIZER:
                this.maxDiskSizeBytes = maxDiskSizeBytes;
                try {
                    openDiskLruCache(diskFolder);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                this.maxDiskSizeBytes = 0;
        }
    }

    private void openDiskLruCache(File diskFolder) throws IOException {
        this.diskLruCache = DiskLruCache.open(
                diskFolder,
                this.appVersion,
                VALUES_PER_CACHE_ENTRY,
                this.maxDiskSizeBytes
        );
    }

    public long getRamUsedInBytes() {
        if (ramCacheLru == null) {
            return -1;
        } else {
            return ramCacheLru.size();
        }
    }

    public long getDiskUsedInBytes() {
        if (diskLruCache == null) {
            return -1;
        } else {
            return diskLruCache.size();
        }

    }

    /**
     * Return the way objects are cached in RAM layer.
     *
     * @return the way objects are cached in RAM layer.
     */
    public DualCacheRamMode getRAMMode() {
        return ramMode;
    }

    /**
     * Return the way objects are cached in disk layer.
     *
     * @return the way objects are cached in disk layer.
     */
    public DualCacheDiskMode getDiskMode() {
        return diskMode;
    }

    /**
     * Put an object in cache.
     *
     * @param key    is the key of the object.
     * @param object is the object to put in cache.
     */
    public <T> void put(String key, T object) {
        // Synchronize put on each entry. Gives concurrent editions on different entries, and atomic
        // modification on the same entry.
        if (ramMode.equals(DualCacheRamMode.ENABLE_WITH_REFERENCE)) {
            ramCacheLru.put(key, object);
        }

        String ramSerialized = null;
        if (ramMode.equals(DualCacheRamMode.ENABLE_WITH_SPECIFIC_SERIALIZER)) {
            ramSerialized = ramSerializer.toString(object);
            ramCacheLru.put(key, ramSerialized);
        }

        if (diskMode.equals(DualCacheDiskMode.ENABLE_WITH_SPECIFIC_SERIALIZER)) {
            try {
                dualCacheLock.lockDiskEntryWrite(key);
                DiskLruCache.Editor editor = diskLruCache.edit(key);
                if (ramSerializer == diskSerializer) {
                    // Optimization if using same serializer
                    editor.set(0, ramSerialized);
                } else {
                    editor.set(0, diskSerializer.toString(object));
                }
                editor.commit();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                dualCacheLock.unLockDiskEntryWrite(key);
            }
        }
    }

    /**
     * Return the object of the corresponding key from the cache. In no object is available,
     * return null.
     *
     * @param key is the key of the object.
     * @return the object of the corresponding key from the cache. In no object is available,
     * return null.
     */
    public <T> T get(String key, Class<T> type) {

        Object ramResult = null;
        String diskResult = null;
        DiskLruCache.Snapshot snapshotObject = null;

        // Try to get the object from RAM.
        boolean isRamSerialized = ramMode.equals(DualCacheRamMode.ENABLE_WITH_SPECIFIC_SERIALIZER);
        boolean isRamReferenced = ramMode.equals(DualCacheRamMode.ENABLE_WITH_REFERENCE);
        if (isRamSerialized || isRamReferenced) {
            ramResult = ramCacheLru.get(key);
        }

        if (ramResult == null) {
            if (diskMode.equals(DualCacheDiskMode.ENABLE_WITH_SPECIFIC_SERIALIZER)) {
                try {
                    dualCacheLock.lockDiskEntryWrite(key);
                    snapshotObject = diskLruCache.get(key);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    dualCacheLock.unLockDiskEntryWrite(key);
                }

                if (snapshotObject != null) {
                    try {
                        diskResult = snapshotObject.getString(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            T objectFromStringDisk = null;

            if (diskResult != null) {
                // Load object, no need to check disk configuration since diskresult != null.
                objectFromStringDisk = diskSerializer.fromString(diskResult, type);

                // Refresh object in ram.
                if (ramMode.equals(DualCacheRamMode.ENABLE_WITH_REFERENCE)) {
                    if (diskMode.equals(DualCacheDiskMode.ENABLE_WITH_SPECIFIC_SERIALIZER)) {
                        ramCacheLru.put(key, objectFromStringDisk);
                    }
                } else if (ramMode.equals(DualCacheRamMode.ENABLE_WITH_SPECIFIC_SERIALIZER)) {
                    if (diskSerializer == ramSerializer) {
                        ramCacheLru.put(key, diskResult);
                    } else {
                        ramCacheLru.put(key, ramSerializer.toString(objectFromStringDisk));
                    }
                }
                return objectFromStringDisk;
            }
        } else {
            if (ramMode.equals(DualCacheRamMode.ENABLE_WITH_REFERENCE)) {
                return (T) ramResult;
            } else if (ramMode.equals(DualCacheRamMode.ENABLE_WITH_SPECIFIC_SERIALIZER)) {
                return ramSerializer.fromString((String) ramResult, type);
            }
        }

        // No data is available.
        return null;
    }

    /**
     * Delete the corresponding object in cache.
     *
     * @param key is the key of the object.
     */
    public void delete(String key) {
        if (!ramMode.equals(DualCacheRamMode.DISABLE)) {
            ramCacheLru.remove(key);
        }
        if (!diskMode.equals(DualCacheDiskMode.DISABLE)) {
            try {
                dualCacheLock.lockDiskEntryWrite(key);
                diskLruCache.remove(key);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                dualCacheLock.unLockDiskEntryWrite(key);
            }
        }
    }

    /**
     * Remove all objects from cache (both RAM and disk).
     */
    public void invalidate() {
        invalidateDisk();
        invalidateRAM();
    }

    /**
     * Remove all objects from RAM.
     */
    public void invalidateRAM() {
        if (!ramMode.equals(DualCacheRamMode.DISABLE)) {
            ramCacheLru.evictAll();
        }
    }

    /**
     * Remove all objects from Disk.
     */
    public void invalidateDisk() {
        if (!diskMode.equals(DualCacheDiskMode.DISABLE)) {
            try {
                dualCacheLock.lockFullDiskWrite();
                diskLruCache.delete();
                openDiskLruCache(diskCacheFolder);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                dualCacheLock.unLockFullDiskWrite();
            }
        }
    }

    /**
     * Test if an object is present in cache.
     *
     * @param key is the key of the object.
     * @return true if the object is present in cache, false otherwise.
     */
    public boolean contains(String key) {
        if (!ramMode.equals(DualCacheRamMode.DISABLE) && ramCacheLru.snapshot().containsKey(key)) {
            return true;
        }
        try {
            dualCacheLock.lockDiskEntryWrite(key);
            if (!diskMode.equals(DualCacheDiskMode.DISABLE) && diskLruCache.get(key) != null) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dualCacheLock.unLockDiskEntryWrite(key);
        }
        return false;
    }

    /**
     * Closes the underlying Disk LRU Cache. (if one is in use)
     *
     * @throws IOException if an I/O error occurs
     * @see DiskLruCache#close()
     */
    @Override
    public void close() throws IOException {
        if (diskLruCache != null) {
            diskLruCache.close();
        }
    }
}
