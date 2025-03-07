package com.example.api_app.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.api_app.R

data class Category(
    val id:String,
    @StringRes
    val title:Int,
    @DrawableRes
    val imageRes :Int
){
    companion object{

        fun getCategories():List<Category> = listOf(
            Category(
                id = "sports",
                title = R.string.sports,
                imageRes = R.drawable.cat_sports
            ),Category(
                id = "business",
                title = R.string.business,
                imageRes = R.drawable.cat_bussiness
            ),Category(
                id = "technology",
                title = R.string.technology,
                imageRes = R.drawable.cat_tech
            ),
            Category(
                id = "entertainment",
                title = R.string.entertainment,
                imageRes = R.drawable.cat_entartainment

            ),Category(
                id = "general",
                title = R.string.general,
                imageRes = R.drawable.cat_general
            ),Category(
                id = "health",
                title = R.string.health,
                imageRes = R.drawable.cat_health
            ),Category(
                id = "science",
                title = R.string.science,
                imageRes = R.drawable.cat_science
            ),
        )
    }
}