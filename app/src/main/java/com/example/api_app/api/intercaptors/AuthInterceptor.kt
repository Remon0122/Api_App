package com.example.api_app.api.intercaptors

import com.example.api_app.api.ApiManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    val apiKey ="3e73e550b9bc4f1fbe36d751c08ab9c6"
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequestBuilder =  chain.request().newBuilder()
        newRequestBuilder.addHeader("Authorization", "Bearer $apiKey")
        return chain.proceed(newRequestBuilder.build())
    }
}