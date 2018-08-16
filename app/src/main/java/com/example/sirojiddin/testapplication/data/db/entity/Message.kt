package com.example.sirojiddin.testapplication.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Message(
        @PrimaryKey
        var id: String,
        var time: Long,
        var text: String,
        var fileId: Int
)