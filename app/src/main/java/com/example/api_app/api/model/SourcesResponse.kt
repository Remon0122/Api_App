package com.example.api_app.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourcesResponse(

	@field:SerializedName("sources")
	val sources: List<Source?>? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("code")
    val code: String? = null,

	@field:SerializedName("massage")
     val massage: String? = null
) : Parcelable