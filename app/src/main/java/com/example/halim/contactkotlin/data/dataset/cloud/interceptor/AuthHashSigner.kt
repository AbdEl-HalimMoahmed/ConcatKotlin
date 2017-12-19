package com.example.halim.contactkotlin.data.dataset.cloud.interceptor


interface AuthHashSigner {

    fun getAuthorizationHeader(method: String, requestUrl: String, requestBody: String): String
}