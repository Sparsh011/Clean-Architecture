package com.example.cleanarchitecturedictionaryapp.data.remote.dto.stackoverflow

import com.example.cleanarchitecturedictionaryapp.data.local.entities.StackOverflowEntity

data class StackOverflowDto(
    val has_more: Boolean,
    val items: List<ItemDto>,
    val quota_max: Int,
    val quota_remaining: Int
) {
    fun toStackOverflowEntity(index: Int): StackOverflowEntity? {
        if (index >= 0 && index < items.size) {
            val itemDto = items[index]
            return StackOverflowEntity(
                creation_date = itemDto.creation_date,
                last_edit_date = itemDto.last_edit_date,
                link = itemDto.link,
                owner = itemDto.owner.toOwner(),
                article_id = itemDto.article_id,
                tags = itemDto.tags,
                title = itemDto.title
            )
        }
        return null
    }
}