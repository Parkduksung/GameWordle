package com.example.wordle

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout.VERTICAL
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.wordle.databinding.ActivityMainBinding
import com.example.wordle.ui.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by viewModels<MainViewModel>()

    private val wordListAdapter = WordListAdapter()
    private val grayAdapter = WordAdapter(WordType.Gray)
    private val yellowAdapter = WordAdapter(WordType.Yellow)
    private val greenAdapter = WordAdapter(WordType.Green)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
    }

    private fun initUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        title = "PA1"

        val decoration = DividerItemDecoration(this, VERTICAL)


        with(binding) {
            rvInput.adapter = wordListAdapter
            rvInput.addItemDecoration(decoration)
            rvGray.adapter = grayAdapter
            rvGray.addItemDecoration(decoration)
            rvYellow.adapter = yellowAdapter
            rvYellow.addItemDecoration(decoration)
            rvGreen.adapter = greenAdapter
            rvGreen.addItemDecoration(decoration)
        }

        uiState()
    }

    private fun uiState() {
        mainViewModel.uiStateLiveDate.observe(this) { uiState ->
            when (uiState) {
                is UiState.Toast -> {
                    Toast.makeText(this@MainActivity, uiState.message, Toast.LENGTH_SHORT).show()
                    hideKeyboard()
                }
                is UiState.Result -> {
                    grayAdapter.add(uiState.grayList)
                    yellowAdapter.add(uiState.yellowList)
                    greenAdapter.add(uiState.greenList)
                    wordListAdapter.add(uiState.convertList)
                    hideKeyboard()
                }
            }
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

}