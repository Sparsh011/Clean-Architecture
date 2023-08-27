package com.example.cleanarchitecturedictionaryapp.domain.repository

import com.example.cleanarchitecturedictionaryapp.core.util.Resource
import com.example.cleanarchitecturedictionaryapp.domain.model.dictionary.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String) : Flow<Resource<List<WordInfo>>>
}