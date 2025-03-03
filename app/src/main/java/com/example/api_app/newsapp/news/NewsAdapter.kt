package com.example.api_app.newsapp.news


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_app.R
import com.example.api_app.model.newsResponse.News
import com.example.api_app.databinding.ItemNewsBinding


class NewsAdapter (var newsList : List<News?>? = null):RecyclerView.Adapter<NewsAdapter.ViewHolder>() {


    class ViewHolder(val binding:ItemNewsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(news: News?){
            binding.title.text = news?.title
            binding.date.text = news?.publishedAt
            binding.author.text = news?.author
            Glide.with(binding.root)
                .load(news?.urlToImage)
                .error(R.drawable.ic_launcher_background)
                .into(binding.image)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = newsList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList?.get(position))
    }
    @SuppressLint("NotifyDataSetChanged")
    fun changeData(newList : List<News?>?){
        this.newsList = newList
        notifyDataSetChanged()
    }
}