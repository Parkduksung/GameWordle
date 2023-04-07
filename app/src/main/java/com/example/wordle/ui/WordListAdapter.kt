package com.example.wordle.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wordle.databinding.ItemWordlistBinding

class WordListAdapter : RecyclerView.Adapter<WordListViewHolder>() {

    private val itemList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordListViewHolder {
        val binding =
            ItemWordlistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordListViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int =
        itemList.size

    fun add(word: String) {
        itemList.add(word)
        notifyDataSetChanged()
    }
}

class WordListViewHolder(private val binding: ItemWordlistBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(word: String) {

    }
}