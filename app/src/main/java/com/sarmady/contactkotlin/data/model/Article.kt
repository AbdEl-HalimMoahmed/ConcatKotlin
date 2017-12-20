package com.sarmady.contactkotlin.data.model

import com.google.gson.annotations.SerializedName

class Article(@SerializedName("id") val id: Long,
              @SerializedName("coverImage") val articleImage: ArticleImage?,
              @SerializedName("publishDate") val date: String?,
              @SerializedName("title") val title: String?,
              @SerializedName("body") val body: String?,
              @SerializedName("authors") val writer: List<ArticleAuthor>?,
              @SerializedName("statusId") val status: Int,
              @SerializedName("articleBodyImages") val bodyImages: List<ArticleImage>?,
              @SerializedName("articleViewType") val articleType: String?,
              @SerializedName("articleBodyGalleries") val bodyGalleries: List<ArticleGallery>?,
              @SerializedName("articleBodyVideos") val bodyVideos: List<ArticleVideo>?,
              @SerializedName("articleBodyQuotes") val quotes: List<ArticleQuote>?)