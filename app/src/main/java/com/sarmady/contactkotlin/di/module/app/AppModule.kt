package com.sarmady.contactkotlin.di.module.app

import android.app.Application
import android.content.Context
import com.sarmady.contactkotlin.App
import com.sarmady.contactkotlin.domain.bus.Bus
import com.sarmady.contactkotlin.domain.bus.RXBus
import com.sarmady.contactkotlin.domain.cache.Session
import com.sarmady.contactkotlin.domain.entities.Arabic
import com.sarmady.contactkotlin.domain.entities.Language
import com.sarmady.fashiononwheels.di.qualifier.AppQualifier
import com.sarmady.fashiononwheels.di.scope.AppScope
import com.sarmady.fashiononwheels.domain.cache.Cache
import com.sarmady.fashiononwheels.domain.cache.GsonDualCache
import com.sarmady.fashiononwheels.domain.excutor.PostExecutionThread
import com.sarmady.fashiononwheels.domain.excutor.ThreadExecutor
import com.sarmady.fashiononwheels.domain.excutor.ThreadPoolExecutor
import com.sarmady.fashiononwheels.domain.excutor.UIExecutor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import java.io.File


@Module(includes = arrayOf(AndroidInjectionModule::class, AppModule.Bind::class,
        NetworkModule::class))
class AppModule {

    @Provides
    @AppScope
    fun provideCacheFile(@AppQualifier context: Context) = File(context.cacheDir, "app")

    @Provides
    @AppScope
    fun provideCache(file: File): Cache = GsonDualCache(file)

    @Provides
    @AppScope
    fun provideSession(cache: Cache) = Session(cache)

    @Module
    abstract class Bind {
        @Binds
        @AppScope
        abstract fun provideApp(app: App): Application

        @Binds
        @AppScope
        @AppQualifier
        abstract fun provideAppContext(context: App): Context

        @Binds
        @AppScope
        abstract fun getBus(rxBus: RXBus): Bus

        @Binds
        @AppScope
        abstract fun provideLanguage(language: Arabic): Language

        @Binds
        @AppScope
        abstract fun provideThreadExecutor(threadPoolExecutor: ThreadPoolExecutor): ThreadExecutor

        @Binds
        @AppScope
        abstract fun provideUIExecutor(uiExecutor: UIExecutor): PostExecutionThread
    }
}