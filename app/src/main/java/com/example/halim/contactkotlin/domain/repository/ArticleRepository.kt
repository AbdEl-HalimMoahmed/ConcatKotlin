package com.example.halim.contactkotlin.domain.repository

import com.example.halim.contactkotlin.domain.entities.Article
import io.reactivex.Observable


interface ArticleRepository {

    fun listArticles(pageNum: Int): Observable<List<Article?>>

    fun getArticle(id: Long): Observable<Article>
}