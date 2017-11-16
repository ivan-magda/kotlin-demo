package com.ivanmagda.listview.model

class Person(private val firstName: String, private val lastName: String, val job: String) {

    fun getName(): String {
        return "$firstName $lastName"
    }
}
