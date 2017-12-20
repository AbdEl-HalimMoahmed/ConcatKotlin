package com.sarmady.contactkotlin.data.repository

import com.sarmady.contactkotlin.data.dataset.ArticleDataSet
import com.sarmady.contactkotlin.data.mapper.Mapper
import com.sarmady.contactkotlin.domain.entities.Article
import com.sarmady.contactkotlin.domain.repository.ArticleRepository
import io.reactivex.Observable
import com.sarmady.contactkotlin.data.model.Article as ArticleModel
import com.sarmady.contactkotlin.domain.entities.Article as ArticleEntity

class ArticleRepositoryImp(private val articleDataSet: ArticleDataSet,
                           private val mapper: Mapper<ArticleModel, ArticleEntity>) : ArticleRepository {

    override fun listArticles(pageNum: Int): Observable<List<Article>> =
            articleDataSet.listArticles(pageNum).map { mapper.transform(it) ?: arrayListOf() }

    override fun getArticle(id: Long): Observable<Article> =
            articleDataSet.getArticleDetails(id).map { mapper.transform(it) ?: Article() }
}