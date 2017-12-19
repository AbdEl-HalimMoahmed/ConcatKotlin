package com.example.halim.contactkotlin.data.dataset.cloud.rest

import com.example.halim.contactkotlin.data.dataset.cloud.model.ArticleListResponse
import com.example.halim.contactkotlin.data.dataset.cloud.model.ArticleResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ArticleService {

    @GET("/news")
    fun listArticles(@Query("page") pageNum: Int): Observable<ArticleListResponse>

    @GET("/news/{id}")
    fun getArticleDetails(@Path("id") id: Long): Observable<ArticleResponse>
}