package com.android.chinewsfeed

import android.app.Application
import com.android.chinewsfeed.di.component.DaggerNewsFeedApiComponent
import com.android.chinewsfeed.di.component.NewsFeedApiComponent
import com.android.chinewsfeed.di.module.NewsFeedApiModule
import com.android.chinewsfeed.di.module.NewsFeedModule
import com.example.shaunreddy.anthemnewsfeed.di.component.DaggerNewsFeedComponent
import com.example.shaunreddy.anthemnewsfeed.di.component.NewsFeedComponent


class AnthemNewsFeedApplication : Application() {

    private lateinit var newsFeedComponent: NewsFeedComponent

    private lateinit var newsFeedApiComponent: NewsFeedApiComponent


    override fun onCreate() {
        super.onCreate()

        newsFeedComponent = DaggerNewsFeedComponent.builder()
            .newsFeedModule(NewsFeedModule())
            .build()

        newsFeedApiComponent = DaggerNewsFeedApiComponent.builder()
            .newsFeedApiModule(NewsFeedApiModule(this))
            .build()
    }

    fun getNewsFeedDaoComponent(): NewsFeedComponent {
        return newsFeedComponent
    }

    fun getNewsFeedBaseServiceComponent(): NewsFeedApiComponent {
        return newsFeedApiComponent
    }

}