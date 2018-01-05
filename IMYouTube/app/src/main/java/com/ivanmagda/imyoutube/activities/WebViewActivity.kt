package com.ivanmagda.imyoutube.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ivanmagda.imyoutube.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {

    private lateinit var url: String

    companion object {
        val TITLE_EXTRA_KEY = "com.ivanmagda.imyoutube.activities.WebViewActivity.title"
        val URL_EXTRA_KEY = "com.ivanmagda.imyoutube.activities.WebViewActivity.url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        setup()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setup() {
        readExtras()

        web_view.settings.javaScriptEnabled = true
        web_view.settings.loadWithOverviewMode = true
        web_view.settings.useWideViewPort = true
        web_view.loadUrl(url)
    }

    private fun readExtras() {
        if (intent.hasExtra(TITLE_EXTRA_KEY)) {
            supportActionBar?.title = intent.getStringExtra(TITLE_EXTRA_KEY)
        }

        if (intent.hasExtra(URL_EXTRA_KEY)) {
            url = intent.getStringExtra(URL_EXTRA_KEY)
        } else {
            throw IllegalArgumentException("Put url in the intent extras to be able to see details!")
        }
    }
}
