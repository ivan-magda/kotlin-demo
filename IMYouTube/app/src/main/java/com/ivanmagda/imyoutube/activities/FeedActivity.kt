/**
 * Copyright (c) 2017 Ivan Magda
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.ivanmagda.imyoutube.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.GsonBuilder
import com.ivanmagda.imyoutube.R
import com.ivanmagda.imyoutube.adapters.FeedAdapter
import com.ivanmagda.imyoutube.model.Feed
import com.ivanmagda.imyoutube.model.Video
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class FeedActivity : AppCompatActivity(), FeedAdapter.OnClickListener {

    private val LOG_TAG = FeedActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
        fetchData()
    }

    override fun onClick(selectedVideo: Video, position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        startActivity(intent)
    }

    private fun setup() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_main.layoutManager = layoutManager
        rv_main.adapter = FeedAdapter()

        val dividerItemDecoration = DividerItemDecoration(rv_main.context, layoutManager.orientation)
        rv_main.addItemDecoration(dividerItemDecoration)
    }

    private fun fetchData() {
        val request = Request.Builder()
                .url("http://api.letsbuildthatapp.com/youtube/home_feed")
                .build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.e(LOG_TAG, "Failed to execute request with error: ${e?.localizedMessage}")
            }

            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                Log.d(LOG_TAG, "Request response: $body")

                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, Feed::class.java)
                Log.d(LOG_TAG, "Home feed object: $homeFeed")

                runOnUiThread {
                    rv_main.adapter = FeedAdapter(homeFeed, this@FeedActivity)
                }
            }
        })
    }
}
