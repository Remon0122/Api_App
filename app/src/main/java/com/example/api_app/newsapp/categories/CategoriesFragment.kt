package com.example.api_app.newsapp.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.api_app.databinding.FragmentCategoriesBinding
import com.example.api_app.model.Category

class CategoriesFragment : Fragment() {
    companion object{
        fun getInstance(
            onCategoryClickCallBack: OnCategoryClickCallBack
        ):CategoriesFragment{
            val fragment = CategoriesFragment()
            fragment.onCategoryClickCallBack = onCategoryClickCallBack
            return fragment
        }
    }

    fun interface OnCategoryClickCallBack {
        fun onCategoryClick(category: Category)
    }

    private var onCategoryClickCallBack :OnCategoryClickCallBack? = null

    lateinit var binding: FragmentCategoriesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(
            inflater,container,false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    val adapter = CategoriesAdapter(
        onCategoryClick = ::onCategoryClick
    )
    private fun onCategoryClick(category: Category){
        onCategoryClickCallBack?.onCategoryClick(category)
    }
    private fun initRecyclerView() {
        binding.categoriesRecycler.adapter = adapter
    }

}