package com.sarmady.contactkotlin.data.mapper

import com.sarmady.contactkotlin.data.model.ArticleVideo as VideoModel
import com.sarmady.contactkotlin.domain.entities.ArticleVideo as VideoEntity
import com.sarmady.contactkotlin.data.model.ArticleImage as ArticleImageModel
import com.sarmady.contactkotlin.domain.entities.Image as ArticleImageEntity

class ArticleVideoMapper(private val imageMapper: Mapper<ArticleImageModel, ArticleImageEntity>) : Mapper<VideoModel, VideoEntity>() {

    override fun transform(from: VideoModel?) = from?.let {
        VideoEntity(imageMapper.transform(from.cover), when (from.host?.get("type")) {
            "Script" -> VideoEntity.Provider.SCRIPT
            "LocalFile" -> VideoEntity.Provider.LOCAL
            "Youtube" -> VideoEntity.Provider.YOUTUBE
            else -> VideoEntity.Provider.NONE
        }, from.host?.get("data"))
    }
}