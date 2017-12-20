package com.sarmady.fashiononwheels.domain.cache

import kotlin.reflect.KClass


interface Cache {

    fun put(key:String, value:Any)

    fun <T: Any> get(key: String, type: KClass<T>) : T?
}