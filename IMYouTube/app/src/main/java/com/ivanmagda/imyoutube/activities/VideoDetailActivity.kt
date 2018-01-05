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
import com.ivanmagda.imyoutube.adapters.VideoDetailAdapter
import com.ivanmagda.imyoutube.model.Video
import com.ivanmagda.imyoutube.model.VideoEntry
import kotlinx.android.synthetic.main.activity_detail.*
import okhttp3.*
import java.io.IOException

class VideoDetailActivity : AppCompatActivity(), VideoDetailAdapter.OnClickListener {

    private val LOG_TAG = VideoDetailActivity::class.java.simpleName

    companion object {
        val VIDEO_EXTRA_KEY = "com.ivanmagda.imyoutube.adapters.VideoDetailAdapter.video"
    }

    private lateinit var video: Video
    private lateinit var videoDetailAdapter: VideoDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setup()
        fetchData()
    }

    override fun onClick(selectedEntry: VideoEntry, position: Int) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra(WebViewActivity.TITLE_EXTRA_KEY, selectedEntry.name)
        intent.putExtra(WebViewActivity.URL_EXTRA_KEY, selectedEntry.link)

        startActivity(intent)
    }

    private fun setup() {
        readExtras()

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rv_video_detail.layoutManager = layoutManager

        videoDetailAdapter = VideoDetailAdapter(onClickListener = this)
        rv_video_detail.adapter = videoDetailAdapter

        val dividerItemDecoration = DividerItemDecoration(rv_video_detail.context, layoutManager.orientation)
        rv_video_detail.addItemDecoration(dividerItemDecoration)

        supportActionBar?.title = video.name
    }

    private fun readExtras() {
        if (intent.hasExtra(VIDEO_EXTRA_KEY)) {
            video = intent.getParcelableExtra(VIDEO_EXTRA_KEY)
        } else {
            throw IllegalArgumentException("Put video in the intent extras to be able to see details!")
        }
    }

    private fun fetchData() {
        val request = Request.Builder()
                .url("http://api.letsbuildthatapp.com/youtube/course_detail?id=${video.id}")
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
                val entries = gson.fromJson(body, Array<VideoEntry>::class.java)
                Log.d(LOG_TAG, "Did fetched video detail items: $entries")

                runOnUiThread {
                    videoDetailAdapter.entries = entries
                }
            }
        })
    }
}
