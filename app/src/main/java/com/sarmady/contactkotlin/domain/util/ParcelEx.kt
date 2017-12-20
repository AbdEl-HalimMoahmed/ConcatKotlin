package com.sarmady.contactkotlin.domain.util

import android.os.Parcel


inline fun <reified K, reified V> getMap(parcel: Parcel): Map<K, V> {

    val length = parcel.readInt()
    val map = HashMap<K, V>(length)

    for (i in 0..length) {
        map.put(parcel.readValue(K::class.java.classLoader) as K, parcel.readValue(V::class.java.classLoader) as V)
    }

    return map
}

inline fun <reified K, reified V> addMap(parcel: Parcel, map: Map<K, V>?) {

    val length = map?.size ?: 0

    parcel.writeInt(length)

    if (length > 0 && map != null)
        for (key in map.keys) {
            parcel.writeValue(key)
            parcel.writeValue(map[key])
        }
}