package com.example.halim.contactkotlin.data.dataset.cloud.model

import com.example.halim.contactkotlin.data.model.Article
import com.google.gson.annotations.SerializedName


class ArticleResponse(@SerializedName("model") val article: Article)