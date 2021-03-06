package com.sarmady.contactkotlin.data.mapper


// Clashes with dagger 2
@Suppress("AddVarianceModifier")
abstract class Mapper<From: Any, To: Any> {

    abstract fun transform(from: From?) : To?

    fun transform(from: List<From?>?):List<To>? = from?.mapNotNull { transform(it) }
}