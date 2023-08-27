package com.example.cleanarchitecturedictionaryapp.data.remote.dto.dictionary

import com.example.cleanarchitecturedictionaryapp.domain.model.dictionary.Meaning

data class MeaningDTO(
    val antonyms: List<Any>,
    val definitions: List<DefinitionDTO>,
    val partOfSpeech: String,
    val synonyms: List<String>
) {
    fun toMeaning() : Meaning {
        return Meaning(
            antonyms = antonyms,
            definitions = definitions.map { it.toDefinition() },
            partOfSpeech = partOfSpeech,
            synonyms = synonyms
        )
    }
}