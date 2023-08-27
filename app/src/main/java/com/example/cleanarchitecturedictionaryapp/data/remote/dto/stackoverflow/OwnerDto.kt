package com.example.cleanarchitecturedictionaryapp.data.remote.dto.stackoverflow

import com.example.cleanarchitecturedictionaryapp.domain.model.stackoverflow.Owner

data class OwnerDto(
    val accept_rate: Int,
    val account_id: Int,
    val display_name: String,
    val link: String,
    val profile_image: String,
    val reputation: Int,
    val user_id: Int,
    val user_type: String
) {
    fun toOwner() : Owner {
        return Owner(
            display_name = display_name,
            link = link,
            profile_image = profile_image,
            reputation = reputation
        )
    }
}