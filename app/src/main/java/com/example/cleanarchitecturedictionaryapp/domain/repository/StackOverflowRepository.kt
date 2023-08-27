package com.example.cleanarchitecturedictionaryapp.domain.repository

import com.example.cleanarchitecturedictionaryapp.core.util.Resource
import com.example.cleanarchitecturedictionaryapp.data.local.entities.StackOverflowEntity
import kotlinx.coroutines.flow.Flow

interface StackOverflowRepository {
    fun getStackOverflowArticles() : Flow<Resource<List<StackOverflowEntity>>>
}