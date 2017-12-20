package com.sarmady.contactkotlin.domain.cache.dualCache;

public interface CacheSerializer {

    <T> T fromString(String data, Class<T> type);

    <T> String toString(T object);
}
