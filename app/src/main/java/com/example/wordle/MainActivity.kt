package com.example.wordle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getWords()
    }

    private fun getWords() {

        val inputStream = assets.open("wordle_words.txt")

        val string = inputStream.readBytes().toString(Charsets.UTF_8).split("\n")

        Log.d("결과", string.size.toString())

    }
}