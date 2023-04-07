package com.example.wordle.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.wordle.databinding.ItemWordBinding


class WordAdapter(val type : WordType)


class WordViewHolder(private val binding: ItemWordBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(word: String) {

    }
}


enum class WordType {
    Gray, Yellow, Green
}


