package com.sarmady.contactkotlin.data.mapper

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import javax.inject.Inject


abstract class MapperContact<From : Any, To : Any> {

    abstract fun getFromValidValue(): From

    abstract fun getFromInvakidValue(): From

    abstract fun getToValidValue(): To

    @Inject
    lateinit var mapper: Mapper<From, To>

    @Test
    fun validFromTransformToValidTo() {
        val from = getFromValidValue()
        val to = getToValidValue()

        Assertions.assertEquals(to, mapper.transform(from))
    }

    @Test
    fun nullFromTransformsToNullTo() {
        val from: From? = null
        Assertions.assertNull(mapper.transform(from))
    }
}