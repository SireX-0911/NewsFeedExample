package com.example.sirojiddin.testapplication.data.db.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Relation

@Entity
data class FileWithMessages (
        var fileId: Int,
        @Relation(parentColumn = "id", entityColumn = "fileId", entity = Message::class)
        var messages: ArrayList<Message>
)