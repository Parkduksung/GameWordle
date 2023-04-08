package com.example.wordle.utils

import com.example.wordle.R
import com.example.wordle.ui.WordType

object Color {

    fun getColorResourceIds(type: WordType): Pair<Int, Int> {
        val wordTypeColor = WordTypeColor.valueOf(type.name)
        return Pair(wordTypeColor.backgroundColorResId, wordTypeColor.textColorResId)
    }
}

enum class WordTypeColor(val backgroundColorResId: Int, val textColorResId: Int) {
    Gray(R.color.gray, R.color.white),
    Yellow(R.color.yellow, R.color.black),
    Green(R.color.green, R.color.black);
}
