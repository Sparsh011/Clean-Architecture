package com.example.cleanarchitecturedictionaryapp.data.remote

import com.example.cleanarchitecturedictionaryapp.data.remote.dto.stackoverflow.StackOverflowDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StackOverflowApi {
    @GET("/2.3/articles")
    suspend fun getArticles(
        @Query("todate") toDate: Long,
        @Query("fromdate") fromDate: Long,
        @Query("site") site: String
    ) : StackOverflowDto

    @GET("/2.3/articles/{id}")
    suspend fun getArticleInfo(
        @Path("id") id: Int,
        @Query("site") site: String
    ) : StackOverflowDto

    companion object {
        const val BASE_URL = "https://api.stackexchange.com"
    }
}