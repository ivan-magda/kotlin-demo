package com.ivanmagda.listview.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import com.ivanmagda.listview.R
import com.ivanmagda.listview.adapter.NameAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configure()
    }

    private fun configure() {
        val listView = findViewById<ListView>(R.id.main_listview)
        listView.adapter = NameAdapter(this)
    }
}
