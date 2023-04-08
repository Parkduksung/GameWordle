package com.example.wordle.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.wordle.databinding.ItemWordlistBinding
import com.example.wordle.utils.Color

class WordListAdapter : RecyclerView.Adapter<WordListViewHolder>() {

    private val itemList = mutableListOf<List<Pair<String, WordType>>>()

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

    fun add(wordList: List<Pair<String, WordType>>) {
        itemList.add(wordList)
        notifyDataSetChanged()
    }
}

class WordListViewHolder(private val binding: ItemWordlistBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(word: List<Pair<String, WordType>>) {

        with(binding) {

            with(text1) {
                text = word[0].first.uppercase()
                val (backgroundColorResId, textColorResId) = Color.getColorResourceIds(word[0].second)
                setTextColor(
                    ContextCompat.getColor(itemView.context, textColorResId)
                )
                setBackgroundColor(
                    ContextCompat.getColor(itemView.context, backgroundColorResId)
                )
            }

            with(text2) {
                text = word[1].first.uppercase()
                val (backgroundColorResId, textColorResId) = Color.getColorResourceIds(word[1].second)
                setTextColor(
                    ContextCompat.getColor(itemView.context, textColorResId)
                )
                setBackgroundColor(
                    ContextCompat.getColor(itemView.context, backgroundColorResId)
                )
            }

            with(text3) {
                text = word[2].first.uppercase()
                val (backgroundColorResId, textColorResId) = Color.getColorResourceIds(word[2].second)
                setTextColor(
                    ContextCompat.getColor(itemView.context, textColorResId)
                )
                setBackgroundColor(
                    ContextCompat.getColor(itemView.context, backgroundColorResId)
                )
            }
            with(text4) {
                text = word[3].first.uppercase()
                val (backgroundColorResId, textColorResId) = Color.getColorResourceIds(word[3].second)
                setTextColor(
                    ContextCompat.getColor(itemView.context, textColorResId)
                )
                setBackgroundColor(
                    ContextCompat.getColor(itemView.context, backgroundColorResId)
                )
            }

            with(text5) {
                text = word[4].first.uppercase()
                val (backgroundColorResId, textColorResId) = Color.getColorResourceIds(word[4].second)
                setTextColor(
                    ContextCompat.getColor(itemView.context, textColorResId)
                )
                setBackgroundColor(
                    ContextCompat.getColor(itemView.context, backgroundColorResId)
                )
            }

        }

    }
}