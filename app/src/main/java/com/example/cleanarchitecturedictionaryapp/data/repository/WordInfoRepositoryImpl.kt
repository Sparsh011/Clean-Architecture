package com.example.cleanarchitecturedictionaryapp.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.cleanarchitecturedictionaryapp.core.util.Resource
import com.example.cleanarchitecturedictionaryapp.data.local.CommonDao
import com.example.cleanarchitecturedictionaryapp.data.remote.DictionaryApi
import com.example.cleanarchitecturedictionaryapp.domain.model.dictionary.WordInfo
import com.example.cleanarchitecturedictionaryapp.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: CommonDao
) : WordInfoRepository{

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow{
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfos(word = word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word = word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(
                Resource.Error(error = e, data = wordInfos)
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(error = e, data = wordInfos)
            )
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}