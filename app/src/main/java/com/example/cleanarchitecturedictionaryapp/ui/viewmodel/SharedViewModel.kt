package com.example.cleanarchitecturedictionaryapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecturedictionaryapp.core.util.Resource
import com.example.cleanarchitecturedictionaryapp.domain.model.stackoverflow.Item
import com.example.cleanarchitecturedictionaryapp.domain.model.stackoverflow.StackOverflow
import com.example.cleanarchitecturedictionaryapp.domain.use_cases.GetStackOverflowInfo
import com.example.cleanarchitecturedictionaryapp.domain.use_cases.GetWordInfo
import com.example.cleanarchitecturedictionaryapp.ui.WordInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val getWordInfo: GetWordInfo,
    private val getStackOverflowInfo: GetStackOverflowInfo
) : ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery : State<String> = _searchQuery

    private val _wordInfo = mutableStateOf(WordInfoState())
    val wordInfo : State<WordInfoState> = _wordInfo

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    private val _stackOverflowArticles = mutableStateOf(StackOverflow())
    val stackOverflowArticles = _stackOverflowArticles

    fun getArticles() {
        viewModelScope.launch {
            val articles = mutableListOf<Item>()
            getStackOverflowInfo.getArticles().collect { result ->
                when(result) {
                    is Resource.Success -> {
                        Log.d("ViewModelMe", "getArticles: ${result.data?.get(0)?.link}")
                        result.data?.map {
                            articles.add(it.toItem())
                        }

                        _stackOverflowArticles.value = StackOverflow(articles)
                        stackOverflowArticles.value = _stackOverflowArticles.value
                    }
                    else -> {

                    }
                }
            }
        }
    }

    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500L)
            getWordInfo(query)
                .onEach {  result ->
                    when(result) {
                        is Resource.Success -> {
                            _wordInfo.value = wordInfo.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _wordInfo.value = wordInfo.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(UIEvent.ShowSnackBar(
                                result.data.toString() // TODO
                            ))
                        }
                        is Resource.Loading -> {
                            _wordInfo.value = wordInfo.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowSnackBar(val message: String) : UIEvent()
    }

    init {
        getArticles()
    }
}