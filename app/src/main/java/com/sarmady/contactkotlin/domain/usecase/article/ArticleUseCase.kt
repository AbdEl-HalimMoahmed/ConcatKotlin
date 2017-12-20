package com.sarmady.contactkotlin.domain.usecase.article

import com.sarmady.contactkotlin.domain.repository.ArticleRepository
import com.sarmady.contactkotlin.domain.usecase.UseCase
import com.sarmady.fashiononwheels.domain.excutor.PostExecutionThread
import com.sarmady.fashiononwheels.domain.excutor.ThreadExecutor


abstract class ArticleUseCase<T, in Params : ArticleUseCase.Param>(protected val repository: ArticleRepository, threadExecutor: ThreadExecutor,
                                                                   uiExecutor: PostExecutionThread) : UseCase<T, Params>(threadExecutor, uiExecutor) {

    sealed class Param {
        class ListArticlesParam(val pageNum: Int) : Param()
    }
}