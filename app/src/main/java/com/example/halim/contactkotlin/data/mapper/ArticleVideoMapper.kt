package com.example.halim.contactkotlin.data.mapper

import com.example.halim.contactkotlin.data.model.ArticleVideo as VideoModel
import com.example.halim.contactkotlin.domain.entities.ArticleVideo as VideoEntity
import com.example.halim.contactkotlin.data.model.ArticleImage as ArticleImageModel
import com.example.halim.contactkotlin.domain.entities.Image as ArticleImageEntity

class ArticleVideoMapper(private val imageMapper: Mapper<ArticleImageModel, ArticleImageEntity>) : Mapper<VideoModel, VideoEntity>() {

    override fun transform(from: VideoModel?): VideoEntity =
            VideoEntity(imageMapper.transform(from?.cover), from?.host)
}