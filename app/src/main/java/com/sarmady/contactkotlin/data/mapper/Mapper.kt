package com.sarmady.contactkotlin.data.mapper


// Clashes with dagger 2
@Suppress("AddVarianceModifier")
abstract class Mapper<From, To> {

    abstract fun transform(from: From?) : To?

    fun transform(from: List<From?>?) = from?.map { transform(it) }
}