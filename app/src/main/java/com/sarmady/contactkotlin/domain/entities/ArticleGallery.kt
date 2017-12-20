package com.sarmady.contactkotlin.domain.entities

class ArticleGallery(private val cover: Image? = null,
                     private val images: List<Image>? = null) {

    val imagesSmall: List<String?>?
        get() = getImages(0)

    val imagesLarge: List<String?>?
        get() = getImages(1)

    val coverImage: String?
        get() = cover?.large

    private fun getImages(size: Int): List<String?>? {
        if (images == null)
            return null

        return images.map { if (size == 0) it.small else it.large }
    }
}
