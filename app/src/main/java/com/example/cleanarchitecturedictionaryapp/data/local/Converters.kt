package com.example.cleanarchitecturedictionaryapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.cleanarchitecturedictionaryapp.data.util.JsonParser
import com.example.cleanarchitecturedictionaryapp.domain.model.dictionary.Meaning
import com.example.cleanarchitecturedictionaryapp.domain.model.stackoverflow.Owner
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser : JsonParser
) {
    @TypeConverter
    fun fromMeaningsJson(json: String) : List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(meanings: List<Meaning>) : String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun toOwnerJson(owner: Owner) : String {
        return jsonParser.toJson(
            owner,
            object : TypeToken<Owner>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromOwnerJson(json: String) : Owner {
        return jsonParser.fromJson<Owner>(
            json = json,
            object : TypeToken<Owner>(){}.type
        ) ?: Owner()
    }

    @TypeConverter
    fun toStringJson(strs: List<String>) : String {
        return jsonParser.toJson(
            obj = strs,
            object : TypeToken<ArrayList<String>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromStringJson(json: String) : List<String> {
        return jsonParser.fromJson<ArrayList<String>>(
            json,
            object : TypeToken<ArrayList<String>>(){}.type
        ) ?: emptyList()
    }
}