package com.example.api_app.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.api_app.R
import com.example.api_app.databinding.ActivityHomeBinding
import com.example.api_app.model.Category
import com.example.api_app.newsapp.categories.CategoriesFragment
import com.example.api_app.newsapp.news.NewsFragment

class HomeActivity :AppCompatActivity(){
    lateinit var viewBinding:ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        showMainFragment(CategoriesFragment.getInstance(
            onCategoryClickCallBack = ::onCategoryClick
        ))

        setSupportActionBar(viewBinding.appBarActivityHome.toolbar)
        viewBinding.appBarActivityHome.toolbar.setNavigationOnClickListener {
            viewBinding.drawerLayout.open()
        }
        viewBinding.navView.setNavigationItemSelectedListener {item->
            when (item.itemId) {
                R.id.nav_view -> {
                    showMainFragment(CategoriesFragment.getInstance(
                        onCategoryClickCallBack = ::onCategoryClick
                    ))
                }
                else -> {}
            }
            viewBinding.drawerLayout.closeDrawers()
            return@setNavigationItemSelectedListener true
        }
    }

    private fun onCategoryClick(category: Category){
        showNewsFragment(category)
    }

    private fun showNewsFragment(category: Category) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,NewsFragment.getInstance(category))
            .addToBackStack(null)
            .commit()
    }

    private fun showMainFragment(fragment: CategoriesFragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .commit()

    }
}