package com.android.chinewsfeed.di.component

import com.android.chinewsfeed.di.module.NewsFeedApiModule
import com.android.chinewsfeed.models.dao.NewsFeedData
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsFeedApiModule::class])
interface NewsFeedApiComponent {
    fun injectNewsFeedApiModule(data : NewsFeedData)
}