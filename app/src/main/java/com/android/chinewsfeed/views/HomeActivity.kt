package com.android.chinewsfeed.views

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.chinewsfeed.R
import com.android.chinewsfeed.viewModel.NewsFeedViewModel


class HomeActivity : AppCompatActivity() {

    private lateinit var newsFeedViewModel: NewsFeedViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        newsFeedViewModel = ViewModelProviders.of(this).get(NewsFeedViewModel::class.java)
        supportFragmentManager.beginTransaction().replace(R.id.news_feed_fl, NewsFeedFragment()).commit()

    }

    fun getViewModel(): NewsFeedViewModel {
        return newsFeedViewModel
    }



    override fun onBackPressed() {
        super.onBackPressed()

    }


}
