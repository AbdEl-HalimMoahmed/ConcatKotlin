package com.sarmady.contactkotlin.di.module.app

import com.sarmady.contactkotlin.di.module.activity.HomeModule
import com.sarmady.contactkotlin.ui.activity.home.HomeActivity
import com.sarmady.fashiononwheels.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivitiesModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(HomeModule::class))
    abstract fun createHomeActivityInjector(): HomeActivity
}