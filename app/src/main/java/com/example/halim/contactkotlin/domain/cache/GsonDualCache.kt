package com.halim.fashiononwheels.domain.cache

import com.example.halim.contactkotlin.domain.APP_VERSION
import com.example.halim.contactkotlin.domain.CACHE_DISK_SIZE
import com.example.halim.contactkotlin.domain.CACHE_NAME
import com.example.halim.contactkotlin.domain.CACHE_RAM_SIZE
import com.example.halim.contactkotlin.domain.cache.dualCache.Builder
import com.example.halim.contactkotlin.domain.cache.dualCache.CacheSerializer
import com.google.gson.Gson
import java.io.File
import kotlin.reflect.KClass

class GsonDualCache(file: File) : Cache {

    private val gson = Gson()

    private val serializer = object : CacheSerializer {
        override fun <T : Any?> fromString(data: String?, type: Class<T>?): T
                = gson.fromJson(data, type)

        override fun <T : Any?> toString(`object`: T): String
                = gson.toJson(`object`)
    }

    private val cache = Builder(CACHE_NAME, APP_VERSION)
            .useSerializerInRam(CACHE_RAM_SIZE, serializer)
            .useSerializerInDisk(CACHE_DISK_SIZE, file, serializer)
            .build()!!

    override fun put(key: String, value: Any) {
        cache.put(key, value)
    }

    override fun <T : Any> get(key: String, type: KClass<T>): T?
            = cache.get(key, type.java)
}