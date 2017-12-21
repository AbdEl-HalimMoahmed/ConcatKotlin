package com.sarmady.contactkotlin.domain.usecase.article

import com.sarmady.contactkotlin.domain.entities.Article
import com.sarmady.contactkotlin.domain.repository.ArticleRepository
import com.sarmady.fashiononwheels.domain.excutor.PostExecutionThread
import com.sarmady.fashiononwheels.domain.excutor.ThreadExecutor
import io.reactivex.Observable


class ListArticlesUseCase(articleRepository: ArticleRepository,
                          threadExecutor: ThreadExecutor,
                          uiExecutor: PostExecutionThread) : ArticleUseCase<List<Article>, ArticleUseCase.Param.ListArticlesParam>(articleRepository, threadExecutor, uiExecutor) {

    override fun buildUseCaseObservable(params: Param.ListArticlesParam): Observable<List<Article>> =
            repository.listArticles(params.pageNum)
}