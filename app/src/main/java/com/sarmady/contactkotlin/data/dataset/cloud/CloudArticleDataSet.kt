package com.sarmady.contactkotlin.data.dataset.cloud

import com.sarmady.contactkotlin.data.dataset.ArticleDataSet
import com.sarmady.contactkotlin.data.dataset.cloud.rest.ArticleService
import com.sarmady.contactkotlin.data.model.Article
import io.reactivex.Observable


class CloudArticleDataSet(private val articleService: ArticleService) : ArticleDataSet {

    override fun listArticles(pageNum: Int): Observable<List<Article>> =
            articleService.listArticles(pageNum).map { it.articles?.mapNotNull { it } ?: arrayListOf() }

    override fun getArticleDetails(id: Long): Observable<Article> =
            articleService.getArticleDetails(id).map { it.article ?: Article() }
}