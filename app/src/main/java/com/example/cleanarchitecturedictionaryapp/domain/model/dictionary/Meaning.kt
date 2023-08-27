package com.example.cleanarchitecturedictionaryapp.domain.model.dictionary

data class Meaning (
    val antonyms: List<Any>,
    val definitions: List<Definition>,
    val partOfSpeech: String,
    val synonyms: List<String>
)