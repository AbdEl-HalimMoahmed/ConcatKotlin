package com.example.halim.contactkotlin.data.mapper

import com.example.halim.contactkotlin.data.model.ArticleGallery as GalleryModel
import com.example.halim.contactkotlin.data.model.ArticleImage as ArticleImageModel
import com.example.halim.contactkotlin.domain.entities.ArticleGallery as GalleryEntity
import com.example.halim.contactkotlin.domain.entities.Image as ArticleImageEntity

class ArticleGalleryMapper(private val imageMapper: Mapper<ArticleImageModel, ArticleImageEntity>) : Mapper<GalleryModel, GalleryEntity>() {

    override fun transform(from: GalleryModel?): GalleryEntity =
            GalleryEntity(imageMapper.transform(from?.cover), imageMapper.transform(from?.images))
}