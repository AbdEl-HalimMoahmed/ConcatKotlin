package com.sarmady.contactkotlin.data.mapper

import com.sarmady.contactkotlin.data.model.ArticleAuthor as AuthorModel
import com.sarmady.contactkotlin.domain.entities.ArticleAuthor as AuthorEntity


class ArticleAuthorMapper : Mapper<AuthorModel, AuthorEntity>() {

    override fun transform(from: AuthorModel?) = from?.let { AuthorEntity(from.name) }

}