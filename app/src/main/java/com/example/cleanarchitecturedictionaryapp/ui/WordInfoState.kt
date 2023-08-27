package com.example.cleanarchitecturedictionaryapp.ui

import com.example.cleanarchitecturedictionaryapp.domain.model.dictionary.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
