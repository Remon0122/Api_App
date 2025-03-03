package com.example.api_app.model.newsResponse

import android.os.Parcelable
import com.example.api_app.model.sourseResponse.Source
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Date

@Parcelize
data class News(

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("urlToImage")
	val urlToImage: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("source")
	val source: Source? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("content")
	val content: String? = null
): Parcelable {
	fun getPublishedAtInMillis(): Long? {
		try {
			val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
			//2025-01-31T11:24:40Z
			val dateTime: Date? = publishedAt?.let { simpleDateFormat.parse(it) }
			return dateTime?.time

		} catch (ex: Exception) {
			return null
		}
	}
}