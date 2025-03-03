package com.example.api_app.newsapp.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.api_app.api.ApiManager
import com.example.api_app.model.sourseResponse.Source
import com.example.api_app.model.sourseResponse.SourcesResponse
import com.example.api_app.databinding.FragmentNewsBinding
import com.example.api_app.model.Category
import com.example.api_app.model.ErrorResponse
import com.example.api_app.model.newsResponse.News
import com.example.api_app.model.newsResponse.NewsResponse
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {

    companion object{
        fun getInstance(category: Category):NewsFragment{
            val fragment = NewsFragment()
            fragment.category = category
            return fragment
        }
    }
    lateinit var category:Category
    lateinit var viewBinding: FragmentNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentNewsBinding.inflate(
            inflater,container,false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        loadSources()
    }

    private fun initViews() {
        viewBinding.newsRecycler.adapter = adapter
    }

    private fun loadSources() {
        // loading status
        showLoading()
        ApiManager.webServices()
            .getSources(category.id)
            .enqueue(object :Callback<SourcesResponse>{

                override fun onFailure(call: Call<SourcesResponse>, error: Throwable) {
                    // handle error View
                    showErrorView(error.localizedMessage ?: "Something went Wrong",
                        onTryAgainClick = {
                            loadSources()
                        })
                }

                override fun onResponse(call: Call<SourcesResponse>, response: Response<SourcesResponse>) {
                    if(!response.isSuccessful){
                        // handle error view
                        val errorResponse = Gson().fromJson(response.errorBody()?.string(),
                            SourcesResponse::class.java)
                        val message = errorResponse.massage ?: "Something went Wrong"
                        showErrorView(message ,
                            onTryAgainClick = {
                                loadSources()
                            })
                        return
                    }
                    // success you have response with sources
                    // show sources
                    bindTabLayout(response.body()?.sources)
                }
            })
    }

    private fun bindTabLayout(sources: List<Source?>?) {
        viewBinding.loadingView.isVisible = false
        viewBinding.errorView.isVisible = false
        sources?.forEach { source->
            val tab = viewBinding.sourceTabs.newTab()
            tab.text = source?.name
            tab.tag = source
            viewBinding.sourceTabs.addTab(tab)
        }
        viewBinding.sourceTabs.addOnTabSelectedListener(
            object : OnTabSelectedListener{
                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val source =  tab?.tag as Source?
                    source?.id?.let { loadNews(it) }
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val source =  tab?.tag as Source?
                    source?.id?.let { loadNews(it) }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }
            }
        )
        viewBinding.sourceTabs.getTabAt(0)?.select()
    }

    private fun loadNews(sourceId: String) {
        showLoading()
        ApiManager.webServices()
            .getNews(source =  sourceId)
            .enqueue(object :Callback<NewsResponse>{
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    if(!response.isSuccessful){
                        // handle error view
                        val errorResponse = Gson().fromJson(response.errorBody()?.string(),
                            ErrorResponse::class.java)
                        val message = errorResponse.message ?: "Something went Wrong"
                        showErrorView(message ,
                            onTryAgainClick = {
                                loadNews(sourceId)
                            })
                        return
                    }
                    // success you have response with sources
                    // show sources
                    showSuccessView()
                    bindNewsList(response.body()?.newsList)

                }

                override fun onFailure(call: Call<NewsResponse>, throwable: Throwable) {

                    showErrorView(
                        throwable.localizedMessage ?: "Something went wrong",
                        onTryAgainClick = {
                            loadNews(sourceId)
                        }
                    )
                }
            })
    }

    val adapter = NewsAdapter()
    private fun bindNewsList(newsList: List<News?>?) {
        // adapter.newsList = newsList
        adapter.changeData(newsList)
    }

    fun showLoading(){
        viewBinding.loadingView.isVisible = true
        viewBinding.errorView.isVisible = false
    }

    fun showSuccessView(){
        viewBinding.loadingView.isVisible = false
        viewBinding.errorView.isVisible = false
    }
    fun showErrorView(errorMessage:String, onTryAgainClick : ()->Unit){
        viewBinding.loadingView.isVisible = false
        viewBinding.errorView.isVisible = true
        viewBinding.errorMassage.text = errorMessage
        viewBinding.tryAgainBtn.setOnClickListener{
            onTryAgainClick.invoke()
        }
    }
}
