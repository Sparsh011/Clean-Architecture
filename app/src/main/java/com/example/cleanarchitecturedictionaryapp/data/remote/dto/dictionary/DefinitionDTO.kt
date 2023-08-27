package com.example.cleanarchitecturedictionaryapp.data.remote.dto.dictionary

import com.example.cleanarchitecturedictionaryapp.domain.model.dictionary.Definition

data class DefinitionDTO(
    val antonyms: List<Any>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
) {
    fun toDefinition() : Definition {
        return Definition(
            antonyms = antonyms,
            definition = definition,
            example = example,
            synonyms = synonyms
        )
    }
}