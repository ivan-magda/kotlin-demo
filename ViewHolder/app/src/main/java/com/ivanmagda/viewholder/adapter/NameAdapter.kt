package com.ivanmagda.viewholder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ivanmagda.viewholder.R
import com.ivanmagda.viewholder.model.Person
import kotlinx.android.synthetic.main.row_main.view.*

class NameAdapter(data: Array<Person>) : BaseAdapter() {

    private class ViewHolder(val nameTextView: TextView, val jobTextView: TextView)

    private val mData: Array<Person> = data

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(viewGroup!!.context).inflate(R.layout.row_main, viewGroup, false)
            view.tag = ViewHolder(view.tv_title, view.tv_subtitle)
        } else {
            view = convertView
        }

        configure(view.tag as ViewHolder, position)

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

    private fun configure(viewHolder: ViewHolder, position: Int) {
        val person = getItem(position)
        viewHolder.nameTextView.text = person.getName()
        viewHolder.jobTextView.text = person.job
    }
}
