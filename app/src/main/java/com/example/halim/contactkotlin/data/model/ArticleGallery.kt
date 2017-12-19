package com.example.halim.contactkotlin.data.model

import com.google.gson.annotations.SerializedName

class ArticleGallery(@SerializedName("coverImage") val cover: ArticleImage?,
                     @SerializedName("images") val images: List<ArticleImage?>?)