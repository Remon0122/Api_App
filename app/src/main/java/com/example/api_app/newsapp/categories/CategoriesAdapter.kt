package com.example.api_app.newsapp.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.api_app.databinding.ItemCategoryBinding
import com.example.api_app.model.Category

class CategoriesAdapter (val items : List<Category> = Category.getCategories(),
                         val onCategoryClick :( (category:Category)->Unit))
    :RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder (var binding : ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(category: Category){
            binding.image.setImageResource(category.imageRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.
        inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            onCategoryClick(items[position])
        }
    }
}