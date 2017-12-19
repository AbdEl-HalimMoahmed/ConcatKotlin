package com.example.halim.contactkotlin.domain.cache.dualCache;

public class ReferenceLruCache extends RamLruCache<String, Object> {

    private SizeOf mHandlerSizeOf;

    public ReferenceLruCache(int maxSize, SizeOf handler) {
        super(maxSize);
        mHandlerSizeOf = handler;
    }

    @Override
    protected int sizeOf(String key, Object value) {
        return mHandlerSizeOf.sizeOf(value);
    }
}
