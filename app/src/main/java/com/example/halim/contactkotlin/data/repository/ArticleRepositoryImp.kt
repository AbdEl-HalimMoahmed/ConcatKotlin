package com.example.halim.contactkotlin.data.repository

import com.example.halim.contactkotlin.data.dataset.ArticleDataSet
import com.example.halim.contactkotlin.data.mapper.Mapper
import com.example.halim.contactkotlin.domain.entities.Article
import com.example.halim.contactkotlin.domain.repository.ArticleRepository
import io.reactivex.Observable
import com.example.halim.contactkotlin.data.model.Article as ArticleModel
import com.example.halim.contactkotlin.domain.entities.Article as ArticleEntity

class ArticleRepositoryImp(private val articleDataSet: ArticleDataSet,
                           private val mapper: Mapper<ArticleModel, ArticleEntity>) : ArticleRepository {

    override fun listArticles(pageNum: Int): Observable<List<Article?>> =
            articleDataSet.listArticles(pageNum).map { mapper.transform(it) ?: arrayListOf<Article>() }

    override fun getArticle(id: Long): Observable<Article> =
            articleDataSet.getArticleDetails(id).map { mapper.transform(it) ?: Article() }
}