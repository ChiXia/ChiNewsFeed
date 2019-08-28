package com.android.chinewsfeed.models.dao

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.android.chinewsfeed.AnthemNewsFeedApplication
import com.android.chinewsfeed.models.data.News
import com.android.chinewsfeed.models.network.NewsFeedService

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NewsFeedData {

    @Inject
    lateinit var newsFeedService: NewsFeedService

    fun retrieveNewsFeed(endPoint : String, country : String, category : String, API_KEY : String, application: Application) : LiveData<News>{

        (application as AnthemNewsFeedApplication).getNewsFeedBaseServiceComponent().injectNewsFeedApiModule(this)
        val newsFeed : MutableLiveData<News> = MutableLiveData()
       val call = newsFeedService.getNewsFeed(endPoint, country, category, API_KEY)
        call.enqueue(object : Callback<News>{
            override fun onFailure(call: Call<News>, t: Throwable) {
                newsFeed.value = null
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
            newsFeed.value = response.body()
            }

        })

        return newsFeed
    }
}