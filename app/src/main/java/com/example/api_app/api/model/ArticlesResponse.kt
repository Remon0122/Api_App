package com.example.api_app.api.model

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<Article?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)