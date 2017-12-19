package com.example.halim.contactkotlin.data.model

import com.google.gson.annotations.SerializedName


class ArticleVideo(@SerializedName("coverImage") val cover: ArticleImage?,
                   @SerializedName("host") val host: Map<String, String>?)