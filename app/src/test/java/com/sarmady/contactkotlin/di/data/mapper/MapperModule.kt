package com.sarmady.contactkotlin.di.data.mapper

import com.sarmady.contactkotlin.data.mapper.ArticleAuthorMapper
import com.sarmady.contactkotlin.data.mapper.Mapper
import com.sarmady.contactkotlin.di.qualifier.PerTestClass
import dagger.Module
import dagger.Provides
import com.sarmady.contactkotlin.data.model.ArticleAuthor as AuthorModel
import com.sarmady.contactkotlin.domain.entities.ArticleAuthor as AuthorEntity

@Module
class MapperModule {

    @Provides
    @PerTestClass
    fun provideArticleAuthorMapper(): Mapper<AuthorModel, AuthorEntity> = ArticleAuthorMapper()
}