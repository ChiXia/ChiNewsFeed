package com.android.chinewsfeed.di.module


import com.android.chinewsfeed.models.dao.NewsFeedData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class NewsFeedModule {

    @Provides
    @Singleton
    fun  provideDao() : NewsFeedData {
        return NewsFeedData()
    }
}