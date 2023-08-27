package com.example.cleanarchitecturedictionaryapp.domain.use_cases

import com.example.cleanarchitecturedictionaryapp.core.util.Resource
import com.example.cleanarchitecturedictionaryapp.data.local.entities.StackOverflowEntity
import com.example.cleanarchitecturedictionaryapp.domain.repository.StackOverflowRepository
import kotlinx.coroutines.flow.Flow

class GetStackOverflowInfo (
    private val repository: StackOverflowRepository
){
    fun getArticles(): Flow<Resource<List<StackOverflowEntity>>> {
        return repository.getStackOverflowArticles()
    }
}