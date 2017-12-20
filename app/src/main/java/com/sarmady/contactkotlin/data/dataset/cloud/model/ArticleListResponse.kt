package com.sarmady.contactkotlin.data.dataset.cloud.model

import com.sarmady.contactkotlin.data.model.Article
import com.google.gson.annotations.SerializedName


class ArticleListResponse(@SerializedName("model") val articles: List<Article?>?)