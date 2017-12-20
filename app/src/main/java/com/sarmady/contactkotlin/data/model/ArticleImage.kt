package com.sarmady.contactkotlin.data.model

import com.google.gson.annotations.SerializedName


class ArticleImage(@SerializedName("id") val id: Long = 0,
                   @SerializedName("url") val urls: Map<String, String>)