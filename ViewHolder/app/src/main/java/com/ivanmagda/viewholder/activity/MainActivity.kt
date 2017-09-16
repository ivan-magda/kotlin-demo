package com.ivanmagda.viewholder.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ivanmagda.viewholder.R
import com.ivanmagda.viewholder.adapter.NameAdapter
import com.ivanmagda.viewholder.model.Person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val persons = arrayOf(
            Person("Donald", "Trump", "President of the USA"),
            Person("Steve", "Jobs", "CEO Apple"),
            Person("Mark", "Zuckerberg", "CEO Facebook"),
            Person("Tim", "Cook", "CEO Apple"),
            Person("Barack", "Obama", "President of the USA"),
            Person("Donald", "Trump", "President of the USA"),
            Person("Steve", "Jobs", "CEO Apple"),
            Person("Mark", "Zuckerberg", "CEO Facebook"),
            Person("Tim", "Cook", "CEO Apple"),
            Person("Barack", "Obama", "President of the USA"),
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
        this.main_listview.adapter = NameAdapter(persons)
    }
}
