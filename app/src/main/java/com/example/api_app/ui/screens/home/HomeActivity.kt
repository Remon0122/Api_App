package com.example.api_app.ui.screens.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.api_app.api.ApiManager
import com.example.api_app.api.model.SourcesResponse
import com.example.api_app.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity :AppCompatActivity(){
    lateinit var binding :ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSources()
    }
    private fun getSources(){
        ApiManager.webservices().getSources()
            .enqueue(object  :Callback<SourcesResponse>{
                override fun onResponse(p0: Call<SourcesResponse>, response: Response<SourcesResponse>) {
                    if (response.isSuccessful){
                        // todo : show error with massage in response
                    }else{
                        // todo : show error view
                    }
                }
                override fun onFailure(p0: Call<SourcesResponse>, p1: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}