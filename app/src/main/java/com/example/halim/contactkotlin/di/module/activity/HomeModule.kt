package com.example.halim.contactkotlin.di.module.activity

import com.example.halim.contactkotlin.domain.bus.Bus
import com.example.halim.contactkotlin.domain.presenter.HomePresenter
import com.example.halim.contactkotlin.domain.repository.ArticleRepository
import com.example.halim.contactkotlin.domain.repository.VehiclesRepository
import com.example.halim.contactkotlin.domain.usecase.article.ListArticlesUseCase
import com.example.halim.contactkotlin.domain.usecase.vehicles.GetNewCarUseCase
import com.example.halim.contactkotlin.domain.usecase.vehicles.GetUsedCarUseCase
import com.example.halim.contactkotlin.domain.view.HomeView
import com.example.halim.contactkotlin.ui.activity.home.HomeActivity
import com.halim.fashiononwheels.di.scope.ActivityScope
import com.halim.fashiononwheels.domain.excutor.PostExecutionThread
import com.halim.fashiononwheels.domain.excutor.ThreadExecutor
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(HomeModule.Bind::class))
class HomeModule {

    @Provides
    @ActivityScope
    fun provideListArticlesUseCase(articleRepository: ArticleRepository,
                                   threadExecutor: ThreadExecutor, postExecutionThread: PostExecutionThread) =
            ListArticlesUseCase(articleRepository, threadExecutor, postExecutionThread)

    @Provides
    @ActivityScope
    fun provideGetNewCarUseCase(vehiclesRepository: VehiclesRepository, threadExecutor: ThreadExecutor,
                                postExecutionThread: PostExecutionThread)
            = GetNewCarUseCase(vehiclesRepository, threadExecutor, postExecutionThread)

    @Provides
    @ActivityScope
    fun provideGetUsedCarUseCase(vehiclesRepository: VehiclesRepository, threadExecutor: ThreadExecutor,
                                 postExecutionThread: PostExecutionThread)
            = GetUsedCarUseCase(vehiclesRepository, threadExecutor, postExecutionThread)

    @Provides
    @ActivityScope
    fun providePresenter(listArticlesUseCase: ListArticlesUseCase, newCarDetails: GetNewCarUseCase,
                         usedCarDetails: GetUsedCarUseCase, bus: Bus, view: HomeView) =
            HomePresenter(listArticlesUseCase, newCarDetails, usedCarDetails, bus, view)

    @Module
    abstract class Bind {
        @Binds
        @ActivityScope
        abstract fun provideView(activity: HomeActivity): HomeView
    }
}