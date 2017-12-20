package com.sarmady.contactkotlin.domain.entities

import android.os.Parcel
import android.os.Parcelable

class Article(id: Long = 0,
              val date: String? = null,
              val title: String? = null,
              val body: String? = null,
              val image: Image? = null,
              val writer: List<ArticleAuthor>? = null,
              val status: Int = 0,
              val bodyImages: List<Image>? = null,
              val articleType: ArticleType? = null,
              val bodyGalleries: List<ArticleGallery>? = null,
              val bodyVideos: List<ArticleVideo>? = null,
              val quotes: List<ArticleQuote>? = null) : Entity(id), Parcelable {

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
            parcel.readString(), status = parcel.readInt())

    // Used in case article type is TEXT_NATIVE
    enum class MediaType constructor(val key: String) {
        IMAGE("image"), VIDEO("video"), GALLERY("gallery"), QUOTE("quote"),
        NONE("");

        companion object {

            fun getType(key: String): MediaType {
                return values().firstOrNull { key.contains(it.key) } ?: NONE
            }
        }
    }

    enum class ArticleType {
        NONE, TEXT, TEXT_NATIVE, EMBEDDED;
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(date)
        parcel.writeString(title)
        parcel.writeString(body)
        parcel.writeInt(status)
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
