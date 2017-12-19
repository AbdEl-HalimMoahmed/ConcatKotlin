package com.example.halim.contactkotlin.domain.view

import com.example.halim.contactkotlin.domain.entities.Article
import com.example.halim.contactkotlin.domain.entities.NewCar
import com.example.halim.contactkotlin.domain.entities.UsedCar


interface HomeView : View {

    fun showLatestNewCars(cars: List<NewCar>)

    fun showPopularNewCars(cars: List<NewCar>)

    fun showUpcomingNewCars(cars: List<NewCar>)

    fun showFeaturedArticles(articles: List<Article>)

    fun showUsedCars(cars: List<UsedCar>, priceRange: String)

    fun showLatestArticles(articles: List<Article>)
}