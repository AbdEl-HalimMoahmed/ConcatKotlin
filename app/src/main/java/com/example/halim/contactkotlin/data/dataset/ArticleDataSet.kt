package com.example.halim.contactkotlin.data.dataset

import com.example.halim.contactkotlin.data.model.Article
import io.reactivex.Observable


interface ArticleDataSet {

    fun listArticles(pageNum: Int): Observable<List<Article>>

    fun getArticleDetails(id: Long): Observable<Article>
}