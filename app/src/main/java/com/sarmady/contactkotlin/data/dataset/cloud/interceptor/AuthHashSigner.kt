package com.sarmady.contactkotlin.data.dataset.cloud.interceptor


interface AuthHashSigner {

    fun getAuthorizationHeader(method: String, requestUrl: String, requestBody: String): String
}