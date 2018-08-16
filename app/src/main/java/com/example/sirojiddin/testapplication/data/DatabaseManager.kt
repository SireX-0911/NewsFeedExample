package com.example.sirojiddin.testapplication.data

import com.example.sirojiddin.testapplication.data.db.entity.File
import com.example.sirojiddin.testapplication.data.db.entity.Message

interface DatabaseManager {
    fun getFileHashCode(fileId : Int) : Int
    fun getFileById(fileId: Int) : File
    fun clearAllTables()
    fun insertFile(file: File)
    fun insertMessages(messages: Message)
    fun deleteFile(file: File)
    fun deleteMessages(messages: Message)
    fun insertFileWithMessage(file: File)
}