package com.example.api_app.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager  {
    companion object{
        const val API_KEY = "3e73e550b9bc4f1fbe36d751c08ab9c6"
        var retrofit : Retrofit?= null

        private fun getInstance() : Retrofit{
            if (retrofit == null){
                val logging = HttpLoggingInterceptor { Log.e("API_CALL", "Massage") }
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
                retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
            return retrofit!!
        }
        fun webservices(): Webservices{
            return getInstance().create(Webservices::class.java)
        }
    }
}