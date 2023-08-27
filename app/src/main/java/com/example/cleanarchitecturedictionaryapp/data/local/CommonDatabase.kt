package com.example.cleanarchitecturedictionaryapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cleanarchitecturedictionaryapp.data.local.entities.StackOverflowEntity
import com.example.cleanarchitecturedictionaryapp.data.local.entities.WordInfoEntity

@Database(entities = [WordInfoEntity::class, StackOverflowEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class CommonDatabase : RoomDatabase(){
    abstract val dao : CommonDao
}