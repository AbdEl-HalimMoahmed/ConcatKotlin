package com.example.halim.contactkotlin.di.component.app

import com.example.halim.contactkotlin.App
import com.example.halim.contactkotlin.di.module.app.ActivitiesModule
import com.example.halim.contactkotlin.di.module.app.AppModule
import com.example.halim.contactkotlin.di.module.app.DataSetModule
import com.example.halim.contactkotlin.di.module.app.RepositoryModule
import com.halim.fashiononwheels.di.scope.AppScope
import dagger.Component
import dagger.android.AndroidInjector


@AppScope
@Component(modules = arrayOf(AppModule::class, ActivitiesModule::class, DataSetModule::class,
        RepositoryModule::class))
interface AppComponent : AndroidInjector<App>{

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}