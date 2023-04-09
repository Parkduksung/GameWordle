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

    private val getRandomWordle = "lousy"


    private val _uiStateLiveData = MutableLiveData<UiState>()
    val uiStateLiveDate: LiveData<UiState> = _uiStateLiveData

    private var isFinished = false

    val inputWordleLiveData = MutableLiveData("")

    private val grayList = mutableListOf<String>()
    private val yellowList = mutableListOf<String>()
    private val greenList = mutableListOf<String>()


    fun submit() {

        if (!isFinished) {


            if (inputWordleLiveData.value.isNullOrEmpty()) {
                return
            }

            if (!getWordList.contains(inputWordleLiveData.value)) {
                onChangedUiState(UiState.Toast("Word ‘${inputWordleLiveData.value}’ not in dictionary!"))
                inputWordleLiveData.value = ""
                return
            }

            val convertList = mutableListOf<Pair<String, WordType>>()

            inputWordleLiveData.value!!.forEachIndexed { inputwordleIndex, inputwordle ->

                if (getRandomWordle.toList().contains(inputwordle)) {
                    if (getRandomWordle.toList()[inputwordleIndex] == inputwordle) {
                        convertList.add(Pair(inputwordle.toString(), WordType.Green))
                    } else {
                        convertList.add(Pair(inputwordle.toString(), WordType.Yellow))
                    }
                } else {
                    convertList.add(Pair(inputwordle.toString(), WordType.Gray))
                }
            }


            val getGrayList = convertList.filter { it.second == WordType.Gray }.map { it.first }
            val getYellowList = convertList.filter { it.second == WordType.Yellow }.map { it.first }
            val getGreenList = convertList.filter { it.second == WordType.Green }.map { it.first }


            grayList.addAll(getGrayList)
            yellowList.addAll(getYellowList)
            greenList.addAll(getGreenList)


            val filterList = yellowList.filter { it !in greenList }

            yellowList.clear()
            yellowList.addAll(filterList)


            onChangedUiState(
                UiState.Result(
                    convertList,
                    grayList.distinct(),
                    yellowList.distinct(),
                    greenList.distinct()
                )
            )

            inputWordleLiveData.value = ""

            if (greenList.size == 5) {
                isFinished = !isFinished
            }

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
    data class Toast(val message: String) : UiState

    data class Result(
        val convertList: List<Pair<String, WordType>>,
        val grayList: List<String>,
        val yellowList: List<String>,
        val greenList: List<String>
    ) : UiState

}


enum class WordType {
    Gray, Yellow, Green
}
