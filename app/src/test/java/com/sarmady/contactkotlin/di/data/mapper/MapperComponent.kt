package com.sarmady.contactkotlin.di.data.mapper

import com.sarmady.contactkotlin.data.mapper.ArticleAuthorMapperTest
import dagger.Component
import com.sarmady.contactkotlin.data.model.ArticleAuthor as AuthorModel
import com.sarmady.contactkotlin.domain.entities.ArticleAuthor as AuthorEntity

@Component(modules = [(MapperModule::class)])
interface MapperComponent {

    fun inject(testClass: ArticleAuthorMapperTest)
}