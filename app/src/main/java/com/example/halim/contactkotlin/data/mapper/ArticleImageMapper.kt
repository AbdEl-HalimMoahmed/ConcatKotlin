package com.example.halim.contactkotlin.data.mapper

import com.example.halim.contactkotlin.data.model.ArticleImage as ArticleImageModel
import com.example.halim.contactkotlin.domain.entities.Image as ArticleImageEntity


class ArticleImageMapper : Mapper<ArticleImageModel, ArticleImageEntity>() {

    override fun transform(from: ArticleImageModel?): ArticleImageEntity =
            ArticleImageEntity(from?.id ?: -1L, from?.urls?.get("small"), from?.urls?.get("large"))
}