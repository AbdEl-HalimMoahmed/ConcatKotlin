package com.sarmady.contactkotlin.data.model

import com.google.gson.annotations.SerializedName

class Article(@SerializedName("id") val id: Long = 0,
              @SerializedName("coverImage") val articleImage: ArticleImage? = null,
              @SerializedName("publishDate") val date: String? = null,
              @SerializedName("title") val title: String? = null,
              @SerializedName("body") val body: String? = null,
              @SerializedName("authors") val writer: List<ArticleAuthor?>? = null,
              @SerializedName("statusId") val status: Int = 0,
              @SerializedName("articleBodyImages") val bodyImages: List<ArticleImage?>? = null,
              @SerializedName("articleViewType") val articleType: String? = null,
              @SerializedName("articleBodyGalleries") val bodyGalleries: List<ArticleGallery?>? = null,
              @SerializedName("articleBodyVideos") val bodyVideos: List<ArticleVideo?>? = null,
              @SerializedName("articleBodyQuotes") val quotes: List<ArticleQuote?>? = null)