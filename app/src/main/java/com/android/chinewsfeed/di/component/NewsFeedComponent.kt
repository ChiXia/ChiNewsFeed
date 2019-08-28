package com.example.shaunreddy.anthemnewsfeed.di.component

import com.android.chinewsfeed.di.module.NewsFeedModule
import com.android.chinewsfeed.viewModel.NewsFeedViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NewsFeedModule::class])
interface NewsFeedComponent {
    fun injectNewsFeedModule(viewModel : NewsFeedViewModel)
}