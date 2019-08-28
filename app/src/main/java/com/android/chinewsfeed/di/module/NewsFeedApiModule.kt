package com.android.chinewsfeed.di.module

import android.app.Application
import com.android.chinewsfeed.R
import com.android.chinewsfeed.models.network.NewsFeedService

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NewsFeedApiModule(application: Application) {


    private var baseURL: String = application.getString(R.string.base_url)
    private var retrofitBuilder: Retrofit.Builder

    init {
        retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(MoshiConverterFactory.create())

    }


    @Provides
    @Singleton
    fun createService(): NewsFeedService {
        val retrofit = retrofitBuilder.build()
        return retrofit.create(NewsFeedService::class.java)
    }


}