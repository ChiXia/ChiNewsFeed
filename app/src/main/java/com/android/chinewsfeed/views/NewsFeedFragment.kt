package com.android.chinewsfeed.views

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.android.chinewsfeed.R
import com.android.chinewsfeed.models.data.News
import com.android.chinewsfeed.util.NewFeedDialogsUtils
import com.android.chinewsfeed.viewModel.NewsFeedViewModel



import kotlinx.android.synthetic.main.frag_news.view.*

class NewsFeedFragment : Fragment(), NewsFeedAdapter.ItemClickListener {


    private lateinit var mContext: Context
    private lateinit var newsFeedViewModel: NewsFeedViewModel
    private lateinit var adapter: NewsFeedAdapter
    private lateinit var newsFeedLiveData: LiveData<News>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_news, container, false)
        newsFeedViewModel = (mContext as HomeActivity).getViewModel()

        intializeNewFeed()

        newsFeedLiveData.observe(this,
            Observer<News> {
                if (it != null) {
                    populateNewsFeedData(view)
                } else {
                    NewFeedDialogsUtils.showRetryMessage(mContext,
                        getString(R.string.error_msg),
                        getString(R.string.error_msg),
                        DialogInterface.OnClickListener { _, _ -> intializeNewFeed() })
                }
            })

        return view
    }

    private fun intializeNewFeed() {
        newsFeedLiveData = newsFeedViewModel.getNewsFeed(
            getString(R.string.news_feed_end_point),
            getString(R.string.country_name),
            getString(R.string.categories_text),
            getString(R.string.api_key),
            (mContext as HomeActivity).application
        )
    }


    override fun onItemClicked(position: Int) {

        if (!newsFeedViewModel.getWebViewUrl(position).isNullOrBlank()) {

            (mContext as HomeActivity).supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.news_feed_fl,
                    NewsFeedDetailsFragment.newInstance(newsFeedViewModel.getWebViewUrl(position)!!)
                )
                .addToBackStack(NewsFeedFragment::class.java.simpleName)
                .commit()
        } else {
            NewFeedDialogsUtils.showErrorMessageAlert(mContext,
                mContext.getString(R.string.error_msg),
                mContext.getString(R.string.incorrect_url),
                DialogInterface.OnClickListener { dialog, _ ->
                    dialog.dismiss()
                })
        }
    }

    private fun populateNewsFeedData(view: View) {
        adapter = NewsFeedAdapter(mContext,newsFeedViewModel , this)
        view.news_feed_recycler_view.adapter = adapter
        view.news_feed_recycler_view.layoutManager = LinearLayoutManager(mContext)
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
}