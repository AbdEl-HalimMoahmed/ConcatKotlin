package com.sarmady.contactkotlin.data.mapper

import com.sarmady.contactkotlin.data.model.ArticleImage as ArticleImageModel
import com.sarmady.contactkotlin.domain.entities.Image as ArticleImageEntity


class ArticleImageMapper : Mapper<ArticleImageModel, ArticleImageEntity>() {

    override fun transform(from: ArticleImageModel?): ArticleImageEntity =
            ArticleImageEntity(from?.id ?: -1L, from?.urls?.get("small"), from?.urls?.get("large"))
}