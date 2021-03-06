package com.javid.roomdbsample.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MainEntity::class],version = 1, exportSchema = false)
abstract class MainDatabase: RoomDatabase() {
    abstract fun mainDao(): MainDao
}