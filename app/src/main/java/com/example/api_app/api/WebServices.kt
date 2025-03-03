package com.example.api_app.api

import com.example.api_app.model.newsResponse.NewsResponse
import com.example.api_app.model.sourseResponse.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WebServices {

    @GET("v2/top-headlines/sources")
    fun getSources(@Query("category") catId: String):Call<SourcesResponse>

    @GET("v2/everything")
    fun getNews(
        @Query("sources") source:String,
    ):Call<NewsResponse>
}