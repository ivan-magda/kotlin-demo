package com.ivanmagda.listview.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.ivanmagda.listview.R
import com.ivanmagda.listview.adapter.NameAdapter
import com.ivanmagda.listview.model.Person

class MainActivity : AppCompatActivity() {

    private val persons = arrayOf(
            Person("Donald", "Trump", "President of the USA"),
            Person("Steve", "Jobs", "CEO Apple"),
            Person("Mark", "Zuckerberg", "CEO Facebook"),
            Person("Tim", "Cook", "CEO Apple"),
            Person("Barack", "Obama", "President of the USA")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configure()
    }

    private fun configure() {
        val listView = findViewById<ListView>(R.id.main_listview)
        listView.adapter = NameAdapter(this, persons)
    }
}
