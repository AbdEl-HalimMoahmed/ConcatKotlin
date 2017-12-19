package com.example.halim.contactkotlin.di.module.app

import com.example.halim.contactkotlin.di.module.activity.HomeModule
import com.example.halim.contactkotlin.ui.activity.home.HomeActivity
import com.halim.fashiononwheels.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivitiesModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(HomeModule::class))
    abstract fun createHomeActivityInjector(): HomeActivity
}