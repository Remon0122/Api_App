package com.example.api_app.api.model

import com.google.gson.annotations.SerializedName

data class SourcesResponse(

	@field:SerializedName("sources")
	val sources: List<SourcesResponse?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)