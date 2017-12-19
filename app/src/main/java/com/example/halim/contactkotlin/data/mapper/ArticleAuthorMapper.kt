package com.example.halim.contactkotlin.data.mapper

import com.example.halim.contactkotlin.data.model.ArticleAuthor as AuthorModel
import com.example.halim.contactkotlin.domain.entities.ArticleAuthor as AuthorEntity


class ArticleAuthorMapper : Mapper<AuthorModel, AuthorEntity>() {

    override fun transform(from: AuthorModel?): AuthorEntity =
            AuthorEntity(from?.name)
}