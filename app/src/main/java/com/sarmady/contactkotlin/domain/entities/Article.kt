package com.sarmady.contactkotlin.domain.entities

import android.os.Parcel
import android.os.Parcelable

class Article(id: Long = 0,
              val date: String? = null,
              val title: String? = null,
              val body: String? = null,
              val image: Image? = null,
              val writer: List<ArticleAuthor?>? = null,
              val status: Int = 0,
              val bodyImages: List<Image?>? = null,
              val articleType: String? = null,
              val bodyGalleries: List<ArticleGallery?>? = null,
              val bodyVideos: List<ArticleVideo?>? = null,
              val quotes: List<ArticleQuote?>? = null) : Entity(id), Parcelable {

    val smallImage: String?
        get() = image?.small

    val largeImage: String?
        get() = image?.large

    val isAd: Boolean
        get() = status == STATUS_PREMIUM

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(), status = parcel.readInt(), articleType = parcel.readString())

    enum class MediaType constructor(val key: String) {
        IMAGE("image"), VIDEO("video"), GALLERY("gallery"), QUOTE("quote"),
        NONE("");

        companion object {

            fun getType(key: String): MediaType {
                return values().firstOrNull { key.contains(it.key) } ?: NONE
            }
        }
    }

    enum class ArticleType private constructor(val key: String) {
        NONE(""), TEXT("Text"), TEXT_NATIVE("TextWithNativeComponents"),
        EMBEDDED("TextWithEmbed");

        companion object {

            fun getType(key: String?): ArticleType {
                return values().firstOrNull { key == it.key } ?: NONE
            }
        }
    }

    fun getBodyImageUrlLarge(index: Int): String? {
        return if (isListEmpty(bodyImages, index)) null else bodyImages!![index]?.large
    }

    fun getGalleryCoverImage(index: Int): String? {
        return if (isListEmpty(bodyGalleries, index)) null else bodyGalleries!![index]?.coverImage
    }

    fun getGalleryImagesSmall(index: Int): List<String?>? {
        return if (isListEmpty(bodyGalleries, index)) null else bodyGalleries!![index]?.imagesSmall
    }

    fun getGalleryImagesLarge(index: Int): List<String?>? {
        return if (isListEmpty(bodyGalleries, index)) null else bodyGalleries!![index]?.imagesLarge
    }

    fun getQuote(index: Int): String? {
        return if (isListEmpty(quotes, index)) null else quotes!![index]?.quote
    }

    fun getBodyImageUrlSmall(index: Int): String? {
        return if (isListEmpty(bodyImages, index)) null else bodyImages!![index]?.small
    }

    fun getVideoProvider(index: Int): ArticleVideo.Provider? {
        return if (isListEmpty(bodyVideos, index)) ArticleVideo.Provider.NONE else bodyVideos!![index]?.provider
    }

    fun getVideoData(index: Int): String? {
        return if (isListEmpty(bodyVideos, index)) null else bodyVideos!![index]?.videoData
    }

    fun getVideoScreenshot(index: Int): String? {
        return if (isListEmpty(bodyVideos, index)) null else bodyVideos!![index]?.coverImageLarge
    }

    fun getMediaType(key: String): MediaType {
        return MediaType.getType(key)
    }

    fun getArticleType(): ArticleType {
        return ArticleType.getType(articleType)
    }

    private fun isListEmpty(list: List<*>?, index: Int): Boolean {
        return list == null || list.isEmpty() || index > list.size
    }

    fun getWriter(): String? {
        return if (writer == null || writer.isEmpty())
            null
        else
            writer[0]?.name
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(date)
        parcel.writeString(title)
        parcel.writeString(body)
        parcel.writeInt(status)
        parcel.writeString(articleType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Article> {
        private val STATUS_PREMIUM = 3

        override fun createFromParcel(parcel: Parcel): Article {
            return Article(parcel)
        }

        override fun newArray(size: Int): Array<Article?> {
            return arrayOfNulls(size)
        }
    }
}
