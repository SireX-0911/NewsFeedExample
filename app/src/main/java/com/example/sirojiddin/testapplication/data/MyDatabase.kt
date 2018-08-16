package com.example.sirojiddin.testapplication.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.sirojiddin.testapplication.data.db.entity.*

@Database(entities = [(Message::class), (File::class)], version = 1)
abstract class MyDatabase : RoomDatabase() {
    abstract fun messageDao(): MessageDao
    abstract fun fileDao(): FileDao
}