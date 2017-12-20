package com.sarmady.contactkotlin.di.module.app

import com.sarmady.contactkotlin.data.dataset.ArticleDataSet
import com.sarmady.contactkotlin.data.dataset.VehicleDataSet
import com.sarmady.contactkotlin.data.dataset.cloud.CloudArticleDataSet
import com.sarmady.contactkotlin.data.dataset.cloud.CloudVehicleDataSet
import com.sarmady.contactkotlin.data.dataset.cloud.rest.ArticleService
import com.sarmady.contactkotlin.data.dataset.cloud.rest.VehicleService
import com.sarmady.contactkotlin.di.qualifier.CMSRestQualifier
import com.sarmady.contactkotlin.di.qualifier.ContactRestQualifier
import com.sarmady.fashiononwheels.di.scope.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class DataSetModule {


    @Provides
    @AppScope
    fun provideArticleService(@CMSRestQualifier retrofit: Retrofit): ArticleService = retrofit.create(ArticleService::class.java)

    @Provides
    @AppScope
    fun provideArticleDataSet(articleService: ArticleService): ArticleDataSet = CloudArticleDataSet(articleService)


    @Provides
    @AppScope
    fun provideVehicleService(@ContactRestQualifier retrofit: Retrofit): VehicleService = retrofit.create(VehicleService::class.java)

    @Provides
    @AppScope
    fun provideVehicleDataSet(vehicleService: VehicleService): VehicleDataSet = CloudVehicleDataSet(vehicleService)
}