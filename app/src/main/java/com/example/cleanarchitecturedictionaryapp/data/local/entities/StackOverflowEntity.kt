package com.example.cleanarchitecturedictionaryapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cleanarchitecturedictionaryapp.domain.model.stackoverflow.Item
import com.example.cleanarchitecturedictionaryapp.domain.model.stackoverflow.Owner

@Entity
data class StackOverflowEntity (
    val creation_date: Int,
    val last_edit_date: Int,
    val link: String,
    val owner: Owner,
    val article_id: Int,
    val tags: List<String>,
    val title: String,
    @PrimaryKey(autoGenerate = true) val id : Int? = null
) {
    fun toItem() : Item {
        return Item(
            creation_date = creation_date,
            last_edit_date = last_edit_date,
            link = link,
            owner = owner,
            tags = tags,
            title = title,
            article_id = article_id
        )
    }
}