package com.sarmady.contactkotlin.di.data.mapper

import com.sarmady.contactkotlin.data.mapper.ArticleAuthorMapper
import com.sarmady.contactkotlin.data.mapper.Mapper
import dagger.Module
import dagger.Provides
import com.sarmady.contactkotlin.data.model.ArticleAuthor as AuthorModel
import com.sarmady.contactkotlin.domain.entities.ArticleAuthor as AuthorEntity

@Module
class MapperModule {

    @Provides
    fun provideArticleAuthorMapper(): Mapper<AuthorModel, AuthorEntity> = ArticleAuthorMapper()
}