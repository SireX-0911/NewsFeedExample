package com.example.sirojiddin.testapplication.data

import com.example.sirojiddin.testapplication.data.db.entity.File
import com.example.sirojiddin.testapplication.data.db.entity.FileWithMessages
import com.example.sirojiddin.testapplication.data.db.entity.Message
import io.reactivex.Single

interface DatabaseManager {
    fun getFileHashCode(fileId : Int) : Int
    fun getFileById(fileId: Int) : File
    fun getMessages(fileId: Int) : FileWithMessages
    fun clearAllTables()
    fun insertFile(file: File)
    fun insertFileWithMessages(fileWithMessages: FileWithMessages)
    fun insertMessages(messages: Message)
    fun deleteFile(file: File)
    fun deleteFileWithMessages(fileWithMessages: FileWithMessages)
    fun deleteMessages(messages: Message)
}