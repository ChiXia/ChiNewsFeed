package com.android.chinewsfeed.views

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.chinewsfeed.R
import com.android.chinewsfeed.databinding.NewsFeedItemLayoutBinding
import com.android.chinewsfeed.viewModel.NewsFeedViewModel


import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_feed_item_layout.view.*

class NewsFeedAdapter(

        private val context: Context,
        private val viewModel: NewsFeedViewModel,
        private val clickListener: ItemClickListener
) : RecyclerView.Adapter<NewsFeedAdapter.NewsFeedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): NewsFeedViewHolder {
        return NewsFeedViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.news_feed_item_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return try {
            viewModel.newsFeedLiveData.value?.articles?.size!!
        } catch (e: KotlinNullPointerException) {
            0
        }
    }

    override fun onBindViewHolder(viewHolder: NewsFeedViewHolder, position: Int) {
        viewHolder.itemLayoutBinding.newsviewModel = viewModel
        viewHolder.itemLayoutBinding.itemPosition = position
        if (viewModel.getArticleAtposition(position) != null && !viewModel.getArticleAtposition(position)?.urlToImage.isNullOrBlank()) {

                Picasso.with(context).load(viewModel.getArticleAtposition(position)?.urlToImage)
                        .resize(2048, 1600)
                        .onlyScaleDown().into(viewHolder.itemView.news_feed_item_imv)


        }

        viewHolder.itemView.setOnClickListener { clickListener.onItemClicked(position) }

    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class NewsFeedViewHolder(val itemLayoutBinding: NewsFeedItemLayoutBinding) :
        RecyclerView.ViewHolder(itemLayoutBinding.root)

    interface ItemClickListener {
        fun onItemClicked(position: Int)
    }
}