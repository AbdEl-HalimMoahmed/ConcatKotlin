package com.sarmady.contactkotlin.di.data.dataset

import com.sarmady.contactkotlin.data.dataset.CloudArticleDataSetTest
import com.sarmady.contactkotlin.di.scope.PerTestClass
import dagger.Component


@Component(modules = [DataSetModule::class])
@PerTestClass
interface DataSetComponent {

    fun inject(articleDataSetTest: CloudArticleDataSetTest)
}