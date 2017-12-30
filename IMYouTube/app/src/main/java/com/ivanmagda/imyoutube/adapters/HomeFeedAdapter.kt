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

package com.ivanmagda.imyoutube.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ivanmagda.imyoutube.R
import com.ivanmagda.imyoutube.model.HomeFeed
import com.ivanmagda.imyoutube.model.Video
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class HomeFeedAdapter(val homeFeed: HomeFeed = HomeFeed()) : RecyclerView.Adapter<HomeFeedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeFeedViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)

        return HomeFeedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    override fun onBindViewHolder(holder: HomeFeedViewHolder?, position: Int) {
        holder?.configure(homeFeed.videos[position])
    }
}

class HomeFeedViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun configure(video: Video) {
        view.tv_list_item_video.text = video.name
        view.tv_list_item_channel.text = video.channel.name

        val picasso = Picasso.with(view.context)
        picasso.load(video.imageUrl).into(view.iv_list_item_video)
        picasso.load(video.channel.profileImageUrl).into(view.iv_list_item_profile)
    }
}
