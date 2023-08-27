package com.example.cleanarchitecturedictionaryapp.data.remote.dto.dictionary

data class PhoneticDTO(
    val audio: String,
    val license: LicenseDTO,
    val sourceUrl: String,
    val text: String
)