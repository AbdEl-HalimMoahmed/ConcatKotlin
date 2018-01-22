package com.sarmady.contactkotlin.data.dataset

import com.sarmady.contactkotlin.data.dataset.cloud.CloudArticleDataSet
import com.sarmady.contactkotlin.data.dataset.cloud.model.ArticleListResponse
import com.sarmady.contactkotlin.data.dataset.cloud.model.ArticleResponse
import com.sarmady.contactkotlin.data.dataset.cloud.rest.ArticleService
import com.sarmady.contactkotlin.data.model.Article
import com.sarmady.contactkotlin.di.data.dataset.DaggerDataSetComponent
import io.reactivex.Observable
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.ArgumentMatchers.*
import org.mockito.Mockito.*
import javax.inject.Inject

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CloudArticleDataSetTest {

    private val validArticleId = 123L
    private val noValidArticleId = -1L
    private val validPageNum = 1
    private val notValidPageNum = 0
    private val errorParam = -1

    @Inject
    lateinit var service: ArticleService
    @Inject
    lateinit var dataSet: CloudArticleDataSet
    lateinit var article: Article
    lateinit var articles: List<Article>


    @BeforeAll
    fun setUp() {
        DaggerDataSetComponent.builder().build().inject(this)

        article = Article(validArticleId)
        articles = arrayListOf(article, article, article)

        `when`(service.getArticleDetails(validArticleId))
                .thenReturn(Observable.just(ArticleResponse(article)))
        `when`(service.getArticleDetails(noValidArticleId))
                .thenReturn(Observable.just(ArticleResponse(null)))

        `when`(service.listArticles(validPageNum))
                .thenReturn(Observable.just(ArticleListResponse(articles)))
        `when`(service.listArticles(notValidPageNum))
                .thenReturn(Observable.just(ArticleListResponse(null)))
    }

    @Test
    fun testArticleDetails() {

        val validResponse =  dataSet.getArticleDetails(validArticleId).test()

        verify(service).getArticleDetails(validArticleId)

        validResponse
                .assertNoErrors()
                .assertValue { article ->
                    article.id == this.article.id
                }

        val notValidResponse = dataSet.getArticleDetails(noValidArticleId).test()

        verify(service).getArticleDetails(noValidArticleId)

        notValidResponse
                .assertNoErrors()
                .assertValue { article ->
                    article.id <= 0
                }
    }

    @Test
    fun testArticleList() {

        val validResponse = dataSet.listArticles(validPageNum).test()

        verify(service).listArticles(validPageNum)

        validResponse
                .assertNoErrors()
                .assertValue { articles ->
                    articles.size == this.articles.size
                }

        val notValidResponse = dataSet.listArticles(notValidPageNum).test()

        verify(service).listArticles(notValidPageNum)

        notValidResponse
                .assertNoErrors()
                .assertValue { articles ->
                    articles.isEmpty()
                }
    }

    @Test
    fun testErrorPropagation() {
        val e = mock(Throwable::class.java)
        val errorLong = errorParam.toLong()
        `when`(service.getArticleDetails(errorLong))
                .thenReturn(Observable.error(e))
        `when`(service.listArticles(errorParam))
                .thenReturn(Observable.error(e))

        val detailsResponse = dataSet.getArticleDetails(errorLong).test()
        val listResponse = dataSet.listArticles(errorParam).test()

        detailsResponse.assertError(e)
        listResponse.assertError(e)
    }
}