package com.example.halim.contactkotlin.domain.presenter

import com.example.halim.contactkotlin.domain.bus.Bus
import com.example.halim.contactkotlin.domain.entities.Article
import com.example.halim.contactkotlin.domain.entities.NewCar
import com.example.halim.contactkotlin.domain.entities.UsedCar
import com.example.halim.contactkotlin.domain.usecase.SimpleDisposableObserver
import com.example.halim.contactkotlin.domain.usecase.article.ArticleUseCase
import com.example.halim.contactkotlin.domain.usecase.article.ListArticlesUseCase
import com.example.halim.contactkotlin.domain.usecase.vehicles.GetNewCarUseCase
import com.example.halim.contactkotlin.domain.usecase.vehicles.GetUsedCarUseCase
import com.example.halim.contactkotlin.domain.usecase.vehicles.VehicleUseCase
import com.example.halim.contactkotlin.domain.view.HomeView


class HomePresenter(private val listArticlesUseCase: ListArticlesUseCase,
                    private val newCarDetails: GetNewCarUseCase,
                    private val usedCarDetails: GetUsedCarUseCase,
                    bus: Bus, view: HomeView) : Presenter<HomeView>(bus, view) {

    override fun register(bus: Bus) {

    }

    override fun init() {

        newCarDetails.execute(VehicleUseCase.Params.GetVehicleDetails(4163),
                object : SimpleDisposableObserver<NewCar>() {

                    override fun onNext(t: NewCar) {
                        view.showLatestNewCars(arrayListOf(t, t, t))
                        view.showPopularNewCars(arrayListOf(t, t, t))
                        view.showUpcomingNewCars(arrayListOf(t, t, t))
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })

        usedCarDetails.execute(VehicleUseCase.Params.GetVehicleDetails(2888707),
                object : SimpleDisposableObserver<UsedCar>() {

                    override fun onNext(t: UsedCar) {
                        view.showUsedCars(arrayListOf(t, t, t), "100-200")
                        view.showUsedCars(arrayListOf(t, t, t), "400-600")
                        view.showUsedCars(arrayListOf(t, t, t), "800-1200")
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })

        listArticlesUseCase.execute(ArticleUseCase.Param.ListArticlesParam(1),
                object : SimpleDisposableObserver<List<Article?>>() {
                    override fun onNext(t: List<Article?>) {
                        if (t.isNotEmpty() && t[0] != null) {
                            val list = t.filter { it != null }.map { it as Article }
                            view.showFeaturedArticles(list)
                            view.showLatestArticles(list)
                        }
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }
                })
    }
}