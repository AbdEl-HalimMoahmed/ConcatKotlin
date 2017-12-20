package com.sarmady.contactkotlin.domain.entities

class ArticleVideo(private val cover: Image? = null,
                   private val host: Map<String, String>? = null) {

    val coverImageLarge: String?
        get() = cover?.large

    val provider: Provider?
        get() = if (host == null) Provider.NONE else Provider.getType(host["type"]!!)

    val videoData: String?
        get() = if (host == null) null else host["data"]

    enum class Provider constructor(val key: String) {
        YOUTUBE("Youtube"), SCRIPT("Script"), LOCAL("LocalFile"), NONE("");

        companion object {

            fun getType(key: String): Provider {
                return values().firstOrNull { key.contains(it.key) } ?: NONE
            }
        }
    }
}
