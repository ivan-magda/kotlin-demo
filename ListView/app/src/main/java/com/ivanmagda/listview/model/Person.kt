package com.ivanmagda.listview.model

class Person(val firstName: String, val lastName: String, val job: String) {

    fun getName(): String {
        return "$firstName $lastName"
    }
}
