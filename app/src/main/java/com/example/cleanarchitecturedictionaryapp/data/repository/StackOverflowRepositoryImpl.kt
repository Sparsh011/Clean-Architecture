package com.example.cleanarchitecturedictionaryapp.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.cleanarchitecturedictionaryapp.core.util.Resource
import com.example.cleanarchitecturedictionaryapp.data.local.CommonDao
import com.example.cleanarchitecturedictionaryapp.data.local.entities.StackOverflowEntity
import com.example.cleanarchitecturedictionaryapp.data.remote.StackOverflowApi
import com.example.cleanarchitecturedictionaryapp.domain.repository.StackOverflowRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class StackOverflowRepositoryImpl(
    private val api : StackOverflowApi,
    private val dao : CommonDao,
    private val toDate: Long,
    private val fromDate: Long
) : StackOverflowRepository{

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun getStackOverflowArticles(): Flow<Resource<List<StackOverflowEntity>>> = flow{
        emit(Resource.Loading())

        val articles = dao.getArticles()
        emit(Resource.Loading(articles))

        try {
            val remoteArticles = api.getArticles(toDate = toDate, fromDate = fromDate, site = "stackoverflow")
            val mappedRemoteArticles = mutableListOf<StackOverflowEntity>()
            remoteArticles.items.forEach {
                mappedRemoteArticles.add(it.toStackOverflowEntity())
            }
            dao.deleteAllArticles()
            val a = dao.insertArticles(mappedRemoteArticles)
        } catch (e: HttpException) {
            emit(
                Resource.Error(error = e, data = articles)
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(error = e, data = articles)
            )
        }

        val newArticles = dao.getArticles()
        emit(Resource.Success(newArticles))
    }
}