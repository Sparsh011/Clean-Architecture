package com.example.cleanarchitecturedictionaryapp.data.remote.dto.stackoverflow

import com.example.cleanarchitecturedictionaryapp.data.local.entities.StackOverflowEntity

data class ItemDto(
    val article_id: Int,
    val article_type: String,
    val creation_date: Int,
    val last_activity_date: Int,
    val last_edit_date: Int,
    val link: String,
    val owner: OwnerDto,
    val score: Int,
    val tags: List<String>,
    val title: String,
    val view_count: Int,
) {
    fun toStackOverflowEntity(): StackOverflowEntity {
        return StackOverflowEntity(
            creation_date = creation_date,
            last_edit_date = last_edit_date,
            link = link,
            owner = owner.toOwner(),
            article_id = article_id,
            tags = tags,
            title = title
        )
    }
}