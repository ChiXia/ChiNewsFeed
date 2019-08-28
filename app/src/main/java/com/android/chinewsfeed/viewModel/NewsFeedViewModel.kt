package com.android.chinewsfeed.viewModel

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.android.chinewsfeed.AnthemNewsFeedApplication
import com.android.chinewsfeed.models.dao.NewsFeedData
import com.android.chinewsfeed.models.data.Article
import com.android.chinewsfeed.models.data.News

import javax.inject.Inject

class NewsFeedViewModel : ViewModel() {

    lateinit var newsFeedLiveData: LiveData<News>

    @Inject
    lateinit var newsFeedData: NewsFeedData


    fun getNewsFeed(endPoint: String, country: String, category: String, API_KEY: String, application: Application): LiveData<News> {
        (application as AnthemNewsFeedApplication).getNewsFeedDaoComponent().injectNewsFeedModule(this)
        newsFeedLiveData = newsFeedData.retrieveNewsFeed(endPoint, country, category, API_KEY, application)
        return newsFeedLiveData
    }

    fun getArticleAtposition(position: Int?) : Article?{
        return when {
            newsFeedLiveData.value?.articles == null -> null
            newsFeedLiveData.value?.articles?.size!! > position!! -> newsFeedLiveData.value?.articles!![position]
            else -> null
        }
    }



    fun getWebViewUrl(position: Int): String? {
        return if (newsFeedLiveData.value?.articles?.size!! > position)

         newsFeedLiveData.value?.articles!![position].url
        else
            null
    }

}