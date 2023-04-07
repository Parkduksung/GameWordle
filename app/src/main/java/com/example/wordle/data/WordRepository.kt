package com.example.wordle.data

import android.content.Context
import kotlin.random.Random

class WordRepository(private val context: Context) {
    fun getRandomWordle(): String =
        try {
            val wordList = getWordList()

            val randomIndex = Random.nextInt(getWordList().size)

            wordList[randomIndex]
        } catch (e: Exception) {
            ""
        }

    fun getWordList(): List<String> =
        try {
            val inputStream = context.assets.open("wordle_words.txt")
            inputStream.readBytes().toString(Charsets.UTF_8).split("\n").map { it.trim() }
        } catch (e: Exception) {
            emptyList()
        }
}