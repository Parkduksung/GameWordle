package com.example.wordle

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.wordle.databinding.ActivityMainBinding
import com.example.wordle.ui.MainViewModel
import com.example.wordle.ui.UiState

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
    }

    private fun initUi() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this
        setContentView(binding.root)

        uiState()
    }

    private fun uiState() {
        mainViewModel.uiStateLiveDate.observe(this) { uiState ->
            when (uiState) {
                is UiState.AddGray -> {}
                is UiState.AddGreen -> {}
                is UiState.AddList -> {}
                is UiState.AddYellow -> {}
                is UiState.Toast -> {
                    Toast.makeText(this@MainActivity, uiState.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
    }
}