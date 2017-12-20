package com.sarmady.contactkotlin.data.mapper

import com.sarmady.contactkotlin.data.model.ArticleImage as ArticleImageModel
import com.sarmady.contactkotlin.domain.entities.Image as ArticleImageEntity


class ArticleImageMapper : Mapper<ArticleImageModel, ArticleImageEntity>() {

    override fun transform(from: ArticleImageModel?) = from?.let {
        ArticleImageEntity(from.id, from.urls?.get("small"), from.urls?.get("large"))
    }
}