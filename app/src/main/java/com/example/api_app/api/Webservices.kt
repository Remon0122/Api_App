package com.example.api_app.api

import com.example.api_app.api.model.ArticlesResponse
import com.example.api_app.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Webservices {

    @GET("/v2/top-headlines/sources")
    fun getSources(
        @Query("apikey") apiKey : String
    ) : Call<SourcesResponse>

    @GET("/v2/everything")
    fun getArticles(
        @Query("apiKey") apiKey : String
    ) : Call<ArticlesResponse>

}