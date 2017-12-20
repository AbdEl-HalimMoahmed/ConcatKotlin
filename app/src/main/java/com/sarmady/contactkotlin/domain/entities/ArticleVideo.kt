package com.sarmady.contactkotlin.domain.entities

class ArticleVideo(private val cover: Image? = null,
                   val provider: Provider?,
                   val videoData: String?) {

    val coverImageLarge: String?
        get() = cover?.large

    enum class Provider {
        YOUTUBE, SCRIPT, LOCAL, NONE;
    }
}
