package com.example.sirojiddin.testapplication.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Message(
        @Json(name = "id")
        @PrimaryKey
        val id: String,
        @Json(name = "time")
        val time: Long,
        @Json(name = "text")
        val text: String,
        var fileId: Int
)