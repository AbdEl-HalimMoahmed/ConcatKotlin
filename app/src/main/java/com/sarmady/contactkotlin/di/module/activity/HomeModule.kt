package com.sarmady.contactkotlin.di.module.activity

import com.sarmady.contactkotlin.domain.bus.Bus
import com.sarmady.contactkotlin.domain.presenter.HomePresenter
import com.sarmady.contactkotlin.domain.repository.ArticleRepository
import com.sarmady.contactkotlin.domain.repository.VehiclesRepository
import com.sarmady.contactkotlin.domain.usecase.article.ListArticlesUseCase
import com.sarmady.contactkotlin.domain.usecase.vehicles.GetNewCarUseCase
import com.sarmady.contactkotlin.domain.usecase.vehicles.GetUsedCarUseCase
import com.sarmady.contactkotlin.domain.view.HomeView
import com.sarmady.contactkotlin.ui.activity.home.HomeActivity
import com.sarmady.fashiononwheels.di.scope.ActivityScope
import com.sarmady.fashiononwheels.domain.excutor.PostExecutionThread
import com.sarmady.fashiononwheels.domain.excutor.ThreadExecutor
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