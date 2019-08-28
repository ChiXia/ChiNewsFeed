package com.android.chinewsfeed.models.data

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)