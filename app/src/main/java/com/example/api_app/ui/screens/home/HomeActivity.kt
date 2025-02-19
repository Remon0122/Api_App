package com.example.api_app.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import com.example.api_app.api.ApiManager
import com.example.api_app.api.model.SourcesResponse
import com.example.api_app.databinding.ActivityHomeBinding
import com.example.api_app.ui.base.BaseActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : BaseActivity<ActivityHomeBinding>(){
    override fun inflateBinding(inflater: LayoutInflater): ActivityHomeBinding {
        TODO("Not yet implemented")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSources()
    }
    private fun getSources(){
        ApiManager.webservices().getSources(ApiManager.API_KEY)
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