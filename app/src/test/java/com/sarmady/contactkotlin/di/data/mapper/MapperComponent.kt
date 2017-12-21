package com.sarmady.contactkotlin.di.data.mapper

import com.sarmady.contactkotlin.data.mapper.ArticleAuthorMapperTest
import com.sarmady.contactkotlin.data.mapper.Mapper
import com.sarmady.contactkotlin.di.qualifier.PerTestClass
import dagger.Component

import com.sarmady.contactkotlin.data.model.ArticleAuthor as AuthorModel
import com.sarmady.contactkotlin.domain.entities.ArticleAuthor as AuthorEntity

@PerTestClass
@Component(modules = arrayOf(MapperModule::class))
interface MapperComponent {

//    fun getAuthorMapper(): Mapper<AuthorModel, AuthorEntity>
    fun inject(textClass: ArticleAuthorMapperTest)
}