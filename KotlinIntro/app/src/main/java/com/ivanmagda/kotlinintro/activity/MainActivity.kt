package com.ivanmagda.kotlinintro.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.ivanmagda.kotlinintro.R
import com.ivanmagda.kotlinintro.model.Student

class MainActivity : AppCompatActivity() {

    private val LOG_TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text_view_main)
        textView.text = getString(R.string.text_hello)

        logFizzBuzz(Array(50, { i -> i + 1 }))

        val denis = Student("Denis", 30)
        val tolya = Student(name = "Tolya", age = 27)
        val ivan = Student(age = 22, name = "Ivan")

        Log.i(LOG_TAG, "$denis, $tolya, $ivan")
    }

    private fun logFizzBuzz(numbers: Array<Int>) {
        for (number in numbers) {
            val divisibleByFive = number % 5 == 0;
            val divisibleByThree = number % 3 == 0;

            if (divisibleByFive && divisibleByThree) {
                Log.i(LOG_TAG, "FizzBuzz: $number")
            } else if (divisibleByThree) {
                Log.i(LOG_TAG, "Fizz: $number")
            } else if (divisibleByFive) {
                Log.i(LOG_TAG, "Buzz: $number")
            }
        }
    }
}
