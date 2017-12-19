package com.example.halim.contactkotlin.domain.usecase.article

import com.example.halim.contactkotlin.domain.repository.ArticleRepository
import com.example.halim.contactkotlin.domain.usecase.UseCase
import com.halim.fashiononwheels.domain.excutor.PostExecutionThread
import com.halim.fashiononwheels.domain.excutor.ThreadExecutor


abstract class ArticleUseCase<T, in Params : ArticleUseCase.Param>(protected val repository: ArticleRepository, threadExecutor: ThreadExecutor,
                                                                   uiExecutor: PostExecutionThread) : UseCase<T, Params>(threadExecutor, uiExecutor) {

    sealed class Param {
        class ListArticlesParam(val pageNum: Int) : Param()
    }
}