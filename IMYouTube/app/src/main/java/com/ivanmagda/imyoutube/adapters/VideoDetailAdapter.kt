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

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ivanmagda.imyoutube.R
import com.ivanmagda.imyoutube.model.VideoEntry
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_detail_list_item.view.*

class VideoDetailAdapter(entries: Array<VideoEntry> = emptyArray(),
                         val onClickListener: OnClickListener? = null)
    : RecyclerView.Adapter<VideoDetailAdapter.VideoDetailViewHolder>() {

    var entries = entries
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /**
     * The interface that receives onClick messages.
     */
    interface OnClickListener {
        /**
         * @param selectedEntry Selected video entry.
         * @param position      Index of the selected item.
         */
        fun onClick(selectedEntry: VideoEntry, position: Int)
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VideoDetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val view = layoutInflater.inflate(R.layout.video_detail_list_item, parent, false)

        return VideoDetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoDetailViewHolder?, position: Int) {
        holder?.bindAt(position)
    }

    inner class VideoDetailViewHolder(private val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            onClickListener?.onClick(entries[adapterPosition], adapterPosition)
        }

        @SuppressLint("SetTextI18n")
        fun bindAt(position: Int) {
            val entry = entries[position]

            view.tv_detail_name.text = entry.name
            view.tv_detail_subtitle.text = "Episode # ${position + 1}"
            Picasso.with(view.context).load(entry.imageUrl).into(view.iv_detail_video)
        }
    }
}
