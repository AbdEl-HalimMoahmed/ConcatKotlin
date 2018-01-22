package com.sarmady.contactkotlin.di.data.dataset

import com.sarmady.contactkotlin.data.dataset.cloud.CloudArticleDataSet
import com.sarmady.contactkotlin.data.dataset.cloud.rest.ArticleService
import com.sarmady.contactkotlin.di.scope.PerTestClass
import dagger.Module
import dagger.Provides
import org.mockito.Mockito


@Module
class DataSetModule {

    @Provides
    @PerTestClass
    fun provideArticleService(): ArticleService = Mockito.mock(ArticleService::class.java)

    @Provides
    @PerTestClass
    fun provideArticleDataSet(service: ArticleService) = CloudArticleDataSet(service)
}