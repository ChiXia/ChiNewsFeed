package com.android.chinewsfeed.views

import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import com.android.chinewsfeed.R
import com.android.chinewsfeed.util.NewFeedDialogsUtils


import java.lang.Exception
import kotlinx.android.synthetic.main.frag_news_description.view.*

class NewsFeedDetailsFragment : Fragment() {

    private lateinit var mContext: Context
    private var isDialogShown = false

    fun withArgs(
            argsBuilder: Bundle.() -> Unit
    ): Fragment = this.apply {
        arguments = Bundle().apply(argsBuilder)
    }
    companion object {
        private const val URL_KEY = "URL_KEY"

        fun newInstance(url: String) = NewsFeedDetailsFragment().withArgs {
            putString(URL_KEY, url)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_news_description, container, false)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    override fun onResume() {
        super.onResume()

        configureWebView(view)


        view?.wv_news_feed_description?.webViewClient = object : WebViewClient() {

            override fun onPageStarted(webView: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(webView, url, favicon)
                view?.progress_news_feed_description?.visibility = View.VISIBLE
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                view?.progress_news_feed_description?.visibility = View.GONE
                if (isAdded && !isDialogShown) {
                    isDialogShown = true
                    NewFeedDialogsUtils.showErrorMessageAlert(mContext,
                        getString(R.string.error_msg),
                        getString(R.string.error_msg),
                        DialogInterface.OnClickListener { _, _ ->

                            try {
                                if ((mContext as HomeActivity).supportFragmentManager.backStackEntryCount > 1)
                                    // remove the fragments
                                    (mContext as HomeActivity).supportFragmentManager.popBackStack()
                            } catch (e: java.lang.ClassCastException) {
                                e.printStackTrace()
                            }
                        })
                }


            }

            override fun onPageFinished(webView: WebView?, url: String?) {
                super.onPageFinished(webView, url)
                view?.progress_news_feed_description?.visibility = View.GONE

            }


        }

        view?.wv_news_feed_description?.webChromeClient = object : WebChromeClient() {

            override fun onProgressChanged(webView: WebView?, newProgress: Int) {
                view?.progress_news_feed_description?.progress = newProgress
                if (newProgress == 100) {
                    view?.progress_news_feed_description?.visibility = View.GONE
                }

            }
        }

        try {
            if (arguments != null && arguments?.containsKey(URL_KEY)!!)
                view?.wv_news_feed_description?.loadUrl(arguments?.getString(URL_KEY))
        } catch (e: Exception) {
            NewFeedDialogsUtils.showErrorMessageAlert(mContext,
                getString(R.string.error_msg),
                getString(R.string.error_msg),
                DialogInterface.OnClickListener { _, _ ->

                    if ((mContext as HomeActivity).supportFragmentManager.backStackEntryCount > 1)
                        (mContext as HomeActivity).supportFragmentManager.popBackStack()
                })
        }


    }


    private fun configureWebView(view: View?) {
        view?.wv_news_feed_description?.settings?.useWideViewPort = true
        view?.wv_news_feed_description?.settings?.displayZoomControls = true
        view?.wv_news_feed_description?.settings?.setSupportZoom(true)
        view?.wv_news_feed_description?.settings?.javaScriptEnabled = true
        view?.wv_news_feed_description?.settings?.domStorageEnabled = true
        view?.wv_news_feed_description?.settings?.builtInZoomControls = true
        view?.wv_news_feed_description?.clearCache(true)
        view?.wv_news_feed_description?.clearHistory()
        view?.wv_news_feed_description?.clearFormData()
        view?.wv_news_feed_description?.settings?.cacheMode = WebSettings.LOAD_NO_CACHE
    }



}