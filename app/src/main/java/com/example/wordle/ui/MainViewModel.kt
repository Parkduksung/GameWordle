package com.example.wordle.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.wordle.data.WordRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val wordRepository = WordRepository(application)

    private val getWordList = wordRepository.getWordList()

    private val getRandomWordle = wordRepository.getRandomWordle()

    private val _uiStateLiveData = MutableLiveData<UiState>()
    val uiStateLiveDate: LiveData<UiState> = _uiStateLiveData

    val inputWordleLiveData = MutableLiveData("")

    fun submit() {
        if (inputWordleLiveData.value.isNullOrEmpty()) {
            return
        }

        if (!getWordList.contains(inputWordleLiveData.value)) {
            onChangedUiState(UiState.Toast("Word ‘${inputWordleLiveData.value}’ not in dictionary!"))
            return
        }


    }

    private fun onChangedUiState(uiState: UiState) {
        viewModelScope.launch {
            _uiStateLiveData.value = uiState
            _uiStateLiveData.value = null
        }
    }

}

sealed interface UiState {
    object Idle : UiState
    data class Toast(val message: String) : UiState
    data class AddList(val word: String) : UiState
    data class AddGray(val word: String) : UiState
    data class AddYellow(val word: String) : UiState
    data class AddGreen(val word: String) : UiState
}