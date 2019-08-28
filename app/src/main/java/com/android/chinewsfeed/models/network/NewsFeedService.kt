package com.android.chinewsfeed.models.network


import com.android.chinewsfeed.models.data.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface NewsFeedService{

    @GET
    fun getNewsFeed(
        @Url endPoint : String,
        @Query("country") country : String,
        @Query("category") category: String,
        @Query("apiKey") API_KEY : String) : Call<News>
}