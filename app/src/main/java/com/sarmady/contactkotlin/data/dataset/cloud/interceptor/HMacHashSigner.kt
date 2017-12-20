package com.sarmady.contactkotlin.data.dataset.cloud.interceptor

import android.util.Base64
import java.io.UnsupportedEncodingException
import java.math.BigInteger
import java.net.URLEncoder
import java.nio.charset.Charset
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class HMacHashSigner : AuthHashSigner {

    companion object {
        private val SEPARATOR = ":"
        private val PREFIX = "amx "
        private val APP_ID = "0BBD9436C4684341BA90F7B4D79C12AE"
        private val APP_SECRET = "T6zQjw31lpw5FLvusEsn0jDK0Gph5ExarusZsX8XFGE="
    }

    private val nonce: String
        get() = UUID.randomUUID().toString()

    override fun getAuthorizationHeader(requestType: String, requestUrl: String, requestBody: String): String {
        val nonce = nonce
        val timestamp = System.currentTimeMillis() / 1000L
        val resultStringBuilder = StringBuilder(PREFIX)
        resultStringBuilder.append(nonce)
        resultStringBuilder.append(SEPARATOR)

        val signatureHash = getSignatureHash(requestType, requestUrl, requestBody, nonce, timestamp)
        resultStringBuilder.append(signatureHash)
        resultStringBuilder.append(SEPARATOR)

        resultStringBuilder.append(APP_ID)
        resultStringBuilder.append(SEPARATOR)

        resultStringBuilder.append(timestamp)

        return resultStringBuilder.toString()
    }

    private fun getSignatureHash(requestType: String, requestUrl: String, requestBody: String, nonce: String, timestamp: Long): String {
        var signatureHash: String
        var requestBodyHash: String? = ""
        if (requestBody.isEmpty().not()) {
            requestBodyHash = getRequestBodyMd5HashBase64(requestBody)
        }
        val signatureBaseString = (requestType
                + getEncodedRequestUrl(requestUrl)
                + APP_ID
                + nonce
                + requestBodyHash
                + timestamp)
        try {
            signatureHash = encodeHmac(signatureBaseString)
        } catch (e: Exception) {
            signatureHash = "WRONG HASH"
            e.printStackTrace()
        }

        return signatureHash
    }

    private fun getRequestBodyMd5Hash(requestBody: String): String? {
        return md5Hash(requestBody)
    }

    private fun getRequestBodyMd5HashBase64(requestBody: String): String? {
        return encodeBase64(getRequestBodyMd5Hash(requestBody))
    }

    private fun getEncodedRequestUrl(requestUrl: String): String? {
        return encodeUrl(requestUrl)
    }

    private fun md5Hash(s: String): String? {
        try {
            val m = MessageDigest.getInstance("MD5")
            m.update(s.toByteArray(), 0, s.length)

            return BigInteger(1, m.digest()).toString(16)
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return null
    }

    private fun encodeBase64(s: String?): String? {
        try {
            val data = s!!.toByteArray(charset("UTF-8"))
            return Base64.encodeToString(data, Base64.NO_WRAP)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun encodeUrl(url: String): String? {
        try {
            val encodedUrl = URLEncoder.encode(url, "UTF-8")
            return encodedUrl.toLowerCase()
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return null
    }

    @Throws(Exception::class)
    private fun encodeHmac(data: String): String {
        val asciiCs = Charset.forName("US-ASCII")
        val sha256HMAC = Mac.getInstance("HmacSHA256")
        val secretKey = SecretKeySpec(asciiCs.encode(APP_SECRET).array(), "HmacSHA256")
        sha256HMAC.init(secretKey)
        return Base64.encodeToString(sha256HMAC.doFinal(asciiCs.encode(data).array()), Base64.NO_WRAP)
    }
}