package com.example.cleanarchitecturedictionaryapp.data.remote.dto.dictionary

import com.example.cleanarchitecturedictionaryapp.data.local.entities.WordInfoEntity

data class WordInfoDTO(
    val license: LicenseDTO,
    val meanings: List<MeaningDTO>,
    val phonetic: String,
    val phonetics: List<PhoneticDTO>,
    val sourceUrls: List<String>,
    val word: String
) {
    fun toWordInfoEntity() : WordInfoEntity {
        return WordInfoEntity(
            meanings = meanings.map { it.toMeaning() },
            word = word,
            phonetic = phonetic
        )
    }
}








