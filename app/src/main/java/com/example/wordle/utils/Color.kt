package com.example.wordle.utils

import android.content.Context
import com.example.wordle.R
import com.example.wordle.ui.WordType

object Color {

    fun getBackgroundAndWordColor( type: WordType): Pair<Int, Int> {
        return when (type) {
            WordType.Gray -> Pair(R.color.gray, R.color.white)
            WordType.Yellow -> Pair(R.color.yellow, R.color.black)
            WordType.Green -> Pair(R.color.green, R.color.black)
        }
    }
}