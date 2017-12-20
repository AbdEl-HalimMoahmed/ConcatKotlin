package com.sarmady.contactkotlin.data.mapper

import com.sarmady.contactkotlin.data.model.ArticleGallery as GalleryModel
import com.sarmady.contactkotlin.data.model.ArticleImage as ArticleImageModel
import com.sarmady.contactkotlin.domain.entities.ArticleGallery as GalleryEntity
import com.sarmady.contactkotlin.domain.entities.Image as ArticleImageEntity

class ArticleGalleryMapper(private val imageMapper: Mapper<ArticleImageModel, ArticleImageEntity>) : Mapper<GalleryModel, GalleryEntity>() {

    override fun transform(from: GalleryModel?): GalleryEntity =
            GalleryEntity(imageMapper.transform(from?.cover), imageMapper.transform(from?.images))
}