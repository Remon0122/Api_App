package com.example.api_app.ui.screens.home.fragments.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.api_app.api.ApiManager
import com.example.api_app.api.model.Source
import com.example.api_app.api.model.SourcesResponse
import com.example.api_app.databinding.FragmentNewsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragments : Fragment() {
    lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadSources()
    }

    private fun loadSources() {
        showLoading()

        ApiManager.webservices()
            .getSources()
            .enqueue(object : Callback<SourcesResponse> {
                override fun onFailure(call: Call<SourcesResponse>, error: Throwable) {
                    showErrorView(
                        error.localizedMessage ?: "Something went wrong",
                        onTryAgainClick = { loadSources() }
                    )
                }

                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    if (response.isSuccessful) {
                        val sourcesResponse = response.body()
                        if (sourcesResponse != null) {
                            bindTabLayout(sourcesResponse.sources)
                        } else {
                            showErrorView("No sources available.",
                                onTryAgainClick = { loadSources() })
                        }
                    } else {
                        val errorResponse = response.errorBody()?.string()
                        val errorMessage = Gson().fromJson(
                            errorResponse,
                            SourcesResponse::class.java
                        ).massage ?: "Something went wrong"
                        showErrorView(
                            errorMessage,
                            onTryAgainClick = { loadSources() }
                        )
                    }
                }
            })
    }

    private fun bindTabLayout(sources: List<Source?>?) {
        binding.loadingView.isVisible = false
        binding.errorView.isVisible = false

        binding.sourceTabs.removeAllTabs()

        sources?.forEach { source ->
            val tab = binding.sourceTabs.newTab()
            tab.text = source?.name
            binding.sourceTabs.addTab(tab)
        }
        binding.sourceTabs.addOnTabSelectedListener(
            object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    TODO("Not yet implemented")
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    TODO("Not yet implemented")
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    TODO("Not yet implemented")
                }
            }
        )
        binding.sourceTabs.getTabAt(0)?.select()
    }

    fun showLoading() {
        binding.loadingView.isVisible = true
        binding.errorView.isVisible = false
    }

    fun showErrorView(errorMessage: String, onTryAgainClick: () -> Unit) {
        binding.loadingView.isVisible = false
        binding.errorView.isVisible = true
        binding.errorMassage.text = errorMessage
        binding.tryAgainBtn.setOnClickListener {
            onTryAgainClick.invoke()
        }
    }
}
