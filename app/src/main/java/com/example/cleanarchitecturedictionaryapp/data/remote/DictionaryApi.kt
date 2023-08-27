package com.example.cleanarchitecturedictionaryapp.data.remote

import com.example.cleanarchitecturedictionaryapp.data.remote.dto.dictionary.WordInfoDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {
    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordInfo(
        @Path("word") word: String
    ) : List<WordInfoDTO>

    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev"
    }
}