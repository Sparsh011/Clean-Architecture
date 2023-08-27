package com.example.cleanarchitecturedictionaryapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cleanarchitecturedictionaryapp.domain.model.dictionary.Meaning
import com.example.cleanarchitecturedictionaryapp.domain.model.dictionary.WordInfo

@Entity
data class WordInfoEntity (
    val word: String?,
    val phonetic : String?,
    val meanings : List<Meaning>?,
    @PrimaryKey val id: Int? = null
) {
    fun toWordInfo() : WordInfo {
        return WordInfo(
            meanings = meanings,
            word = word
        )
    }
}