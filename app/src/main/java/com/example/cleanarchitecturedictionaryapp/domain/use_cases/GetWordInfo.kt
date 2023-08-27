package com.example.cleanarchitecturedictionaryapp.domain.use_cases

import com.example.cleanarchitecturedictionaryapp.core.util.Resource
import com.example.cleanarchitecturedictionaryapp.domain.model.dictionary.WordInfo
import com.example.cleanarchitecturedictionaryapp.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfo (
    private val repository: WordInfoRepository
){
    operator fun invoke(word: String) : Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) return flow {  }

        return repository.getWordInfo(word)
    }
}