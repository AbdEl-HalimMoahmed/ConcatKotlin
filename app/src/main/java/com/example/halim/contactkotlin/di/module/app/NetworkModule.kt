package com.example.halim.contactkotlin.di.module.app

import android.content.Context
import com.example.halim.contactkotlin.BuildConfig
import com.example.halim.contactkotlin.data.dataset.cloud.interceptor.AuthHashSigner
import com.example.halim.contactkotlin.data.dataset.cloud.interceptor.AuthInterceptor
import com.example.halim.contactkotlin.data.dataset.cloud.interceptor.HMacHashSigner
import com.example.halim.contactkotlin.di.qualifier.CMSRestQualifier
import com.example.halim.contactkotlin.di.qualifier.ContactRestQualifier
import com.example.halim.contactkotlin.domain.NETWORK_CACHE_SIZE
import com.halim.fashiononwheels.di.qualifier.AppQualifier
import com.halim.fashiononwheels.di.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


@Module
class NetworkModule {

    @Provides
    @AppScope
    fun provideCache(@AppQualifier context: Context) = Cache(File(context.cacheDir, "retrofit"), NETWORK_CACHE_SIZE)

    @Provides
    @AppScope
    fun provideAuthHashSigner(): AuthHashSigner = HMacHashSigner()

    @Provides
    @AppScope
    fun provideOkHttpClient(cache: Cache, authHashSigner: AuthHashSigner): OkHttpClient =
            OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(AuthInterceptor(authHashSigner))
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                    })
                    .build()

    @Provides
    @AppScope
    @CMSRestQualifier
    fun provideCMSRestAdapter(client: OkHttpClient): Retrofit
            = provideRestAdapter(client, "http://cms.api.contactcars.com")

    @Provides
    @AppScope
    @ContactRestQualifier
    fun provideContactRestAdapter(client: OkHttpClient): Retrofit
            = provideRestAdapter(client, "http://newapi.contactcars.com")

    private fun provideRestAdapter(client: OkHttpClient, baseUrl: String): Retrofit =
            Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
}