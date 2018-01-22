package com.sarmady.contactkotlin.data.mapper

import com.sarmady.contactkotlin.TestLifecycleLogger
import com.sarmady.contactkotlin.di.data.mapper.DaggerMapperComponent
import com.sarmady.contactkotlin.di.data.mapper.MapperComponent
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import javax.inject.Inject

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class MapperTestContract<From : Any, To : Any>: TestLifecycleLogger {

    abstract fun getFromValidValue(): From

    abstract fun getFromInvalidValue(): From

    abstract fun getToValidValue(): To

    @Inject
    lateinit var mapper: Mapper<From, To>

    @BeforeAll
    fun init() {
        inject(DaggerMapperComponent.builder().build())
    }

    abstract fun inject(component:MapperComponent)

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