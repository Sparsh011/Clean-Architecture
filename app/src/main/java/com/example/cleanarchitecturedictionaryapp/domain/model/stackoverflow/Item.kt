package com.example.cleanarchitecturedictionaryapp.domain.model.stackoverflow

data class Item(
    val creation_date: Int,
    val last_edit_date: Int,
    val link: String,
    val owner: Owner,
    val tags: List<String>,
    val title: String,
    val article_id: Int
)