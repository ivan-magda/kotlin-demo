package com.ivanmagda.listview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.ivanmagda.listview.R
import com.ivanmagda.listview.model.Person
import kotlinx.android.synthetic.main.row_main.view.*

class NameAdapter(context: Context, data: Array<Person>) : BaseAdapter() {

    private val mContext: Context = context
    private val mData: Array<Person> = data

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(mContext)
        val view = layoutInflater.inflate(R.layout.row_main, viewGroup, false)

        configure(view, position)

        return view
    }

    override fun getItem(position: Int): Person {
        return mData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return mData.size
    }

    private fun configure(view: View, position: Int) {
        val person = getItem(position)

        view.tv_title.text = person.getName()
        view.tv_subtitle.text = person.job
    }
}
