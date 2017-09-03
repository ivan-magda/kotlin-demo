package com.ivanmagda.listview.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class NameAdapter(context: Context) : BaseAdapter() {

    private val mContext: Context = context

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val textView = TextView(mContext)
        textView.text = "Here is my text for row: $position"

        return textView
    }

    override fun getItem(position: Int): Any {
        return "Test string at position: $position"
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return 10
    }
}
