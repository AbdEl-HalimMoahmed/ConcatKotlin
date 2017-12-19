package com.example.halim.contactkotlin.data.dataset.cloud.model

import com.example.halim.contactkotlin.data.model.Article
import com.google.gson.annotations.SerializedName


class ArticleListResponse(@SerializedName("model") val articles: List<Article>)