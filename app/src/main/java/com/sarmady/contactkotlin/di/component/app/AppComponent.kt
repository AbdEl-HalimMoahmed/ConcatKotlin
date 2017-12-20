package com.sarmady.contactkotlin.di.component.app

import com.sarmady.contactkotlin.App
import com.sarmady.contactkotlin.di.module.app.ActivitiesModule
import com.sarmady.contactkotlin.di.module.app.AppModule
import com.sarmady.contactkotlin.di.module.app.DataSetModule
import com.sarmady.contactkotlin.di.module.app.RepositoryModule
import com.sarmady.fashiononwheels.di.scope.AppScope
import dagger.Component
import dagger.android.AndroidInjector


@AppScope
@Component(modules = arrayOf(AppModule::class, ActivitiesModule::class, DataSetModule::class,
        RepositoryModule::class))
interface AppComponent : AndroidInjector<App>{

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}