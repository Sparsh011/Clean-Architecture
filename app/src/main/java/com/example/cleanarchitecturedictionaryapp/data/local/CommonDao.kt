package com.example.cleanarchitecturedictionaryapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cleanarchitecturedictionaryapp.data.local.entities.StackOverflowEntity
import com.example.cleanarchitecturedictionaryapp.data.local.entities.WordInfoEntity

@Dao
interface CommonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfos(infos: List<WordInfoEntity>)

    @Query("DELETE FROM wordinfoentity WHERE word IN(:words)")
    suspend fun deleteWordInfos(words: List<String>)

    @Query("SELECT * FROM wordinfoentity WHERE word LIKE '%' || :word || '%'")
    suspend fun getWordInfos(word: String) : List<WordInfoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<StackOverflowEntity>)

    @Query("DELETE FROM stackoverflowentity")
    suspend fun deleteAllArticles()

    @Query("SELECT * FROM stackoverflowentity")
    suspend fun getArticles() : List<StackOverflowEntity>

}