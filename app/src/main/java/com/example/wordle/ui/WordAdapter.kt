package com.example.wordle.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.wordle.databinding.ItemWordBinding
import com.example.wordle.utils.Color


class WordAdapter(private val type: WordType) : RecyclerView.Adapter<WordViewHolder>() {

    private val wordList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(wordList[position], type)
    }

    override fun getItemCount(): Int =
        wordList.size

    @SuppressLint("NotifyDataSetChanged")
    fun add(word: List<String>) {
        wordList.clear()
        wordList.addAll(word)
        notifyDataSetChanged()
    }
}


class WordViewHolder(private val binding: ItemWordBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String, type: WordType) {
        with(binding.text) {
            text = item.uppercase()
            val (backgroundColorResId, textColorResId) = Color.getColorResourceIds(type)
            setTextColor(
                ContextCompat.getColor(itemView.context, textColorResId)
            )
            setBackgroundColor(
                ContextCompat.getColor(itemView.context, backgroundColorResId)
            )
        }
    }
}


