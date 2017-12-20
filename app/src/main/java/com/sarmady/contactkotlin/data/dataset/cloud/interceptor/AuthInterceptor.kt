package com.sarmady.contactkotlin.data.dataset.cloud.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.io.IOException

class AuthInterceptor(private val authHashSigner: AuthHashSigner) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val requestBody = readRequest(chain.request())
        return if (requestBody != null) {
            chain.proceed(oldRequest.newBuilder()
                    .header("Authorization", authHashSigner.getAuthorizationHeader(oldRequest.method(),
                            oldRequest.url().toString(), requestBody)).build())
        } else {
            chain.proceed(oldRequest)
        }
    }

    private fun readRequest(request: Request): String? =
            try {
                val copy = request.newBuilder().build()
                if (copy.body() != null) {
                    val buffer = Buffer()
                    copy.body()?.writeTo(buffer)
                    buffer.readUtf8()
                } else {
                    null
                }
            } catch (e: IOException) {
                null
            }


}