package com.sarmady.contactkotlin.data.mapper

import com.sarmady.contactkotlin.di.data.mapper.MapperComponent
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import javax.inject.Inject


abstract class MapperTestContract<From : Any, To : Any> {

//    @get:Rule
//    val daggerMockRule = DaggerMock.rule<MapperComponent>(MapperModule()) {
//        set { mapper = injectMapper(it) }
//    }

    abstract fun getFromValidValue(): From

    abstract fun getFromInvalidValue(): From

    abstract fun getToValidValue(): To

//    abstract fun injectMapper(component: MapperComponent): Mapper<From, To>

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