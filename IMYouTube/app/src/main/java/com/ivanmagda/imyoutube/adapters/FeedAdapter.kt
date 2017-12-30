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
import com.ivanmagda.imyoutube.model.Feed
import com.ivanmagda.imyoutube.model.Video
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.feed_list_item.view.*

class FeedAdapter(feed: Feed = Feed(),
                  val clickListener: OnClickListener? = null)
    : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    var feed = feed
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /**
     * The interface that receives onClick messages.
     */
    interface OnClickListener {
        /**
         * @param selectedVideo Selected video.
         * @param position      Index of the selected item.
         */
        fun onClick(selectedVideo: Video, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FeedViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val view = layoutInflater.inflate(R.layout.feed_list_item, parent, false)

        return FeedViewHolder(view)
    }

    override fun getItemCount(): Int {
        return feed.videos.count()
    }

    override fun onBindViewHolder(holder: FeedViewHolder?, position: Int) {
        holder?.configure(feed.videos[position])
    }

    inner class FeedViewHolder(val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            clickListener?.onClick(feed.videos[adapterPosition], adapterPosition)
        }

        fun configure(video: Video) {
            view.tv_list_item_video.text = video.name
            view.tv_list_item_channel.text = video.channel.name

            val picasso = Picasso.with(view.context)
            picasso.load(video.imageUrl).into(view.iv_list_item_video)
            picasso.load(video.channel.profileImageUrl).into(view.iv_list_item_profile)
        }
    }
}
