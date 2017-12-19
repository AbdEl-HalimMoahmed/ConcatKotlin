package com.example.halim.contactkotlin.di.module.app

import com.example.halim.contactkotlin.data.dataset.ArticleDataSet
import com.example.halim.contactkotlin.data.dataset.VehicleDataSet
import com.example.halim.contactkotlin.data.dataset.cloud.CloudArticleDataSet
import com.example.halim.contactkotlin.data.dataset.cloud.CloudVehicleDataSet
import com.example.halim.contactkotlin.data.dataset.cloud.rest.ArticleService
import com.example.halim.contactkotlin.data.dataset.cloud.rest.VehicleService
import com.example.halim.contactkotlin.di.qualifier.CMSRestQualifier
import com.example.halim.contactkotlin.di.qualifier.ContactRestQualifier
import com.halim.fashiononwheels.di.scope.AppScope
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