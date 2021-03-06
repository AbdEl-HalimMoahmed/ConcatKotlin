package com.sarmady.contactkotlin.domain.cache.dualCache;

/**
 * Define the behaviour of the disk layer.
 */
public enum DualCacheDiskMode {
    /**
     * Means that object will be serialized with a specific serializer in disk.
     */
    ENABLE_WITH_SPECIFIC_SERIALIZER,

    /**
     * The disk layer is not used.
     */
    DISABLE
}
