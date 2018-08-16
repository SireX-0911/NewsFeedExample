package com.example.sirojiddin.testapplication.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity
data class File (
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var hashCode : Int
)