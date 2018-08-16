package com.example.sirojiddin.testapplication.data

import com.example.sirojiddin.testapplication.data.db.LocalDatabase
import com.example.sirojiddin.testapplication.data.db.entity.File
import com.example.sirojiddin.testapplication.data.db.entity.FileWithMessages
import com.example.sirojiddin.testapplication.data.db.entity.Message
import com.example.sirojiddin.testapplication.data.network.ApiDatabase

class DatabaseManagerImpl(
        private val localDatabase: LocalDatabase,
        apiDatabase: ApiDatabase
) : DatabaseManager {
    override fun insertFile(file: File) {
        return localDatabase.insertFile(file)
    }

    override fun insertFileWithMessages(fileWithMessages: FileWithMessages) {
        return localDatabase.insertFileWithMessages(fileWithMessages)
    }

    override fun insertMessages(messages: Message) {
        return localDatabase.insertMessages(messages)
    }

    override fun clearAllTables() {
        return localDatabase.clearAllTables()
    }

    override fun getFileHashCode(fileId: Int): Int {
        return localDatabase.getFileHashCode(fileId)
    }

    override fun getFileById(fileId: Int): File {
        return localDatabase.getFileById(fileId)
    }

    override fun getMessages(fileId: Int): FileWithMessages {
        return localDatabase.getMessages(fileId)
    }

    override fun deleteFile(file: File) {
        return localDatabase.deleteFile(file)
    }

    override fun deleteFileWithMessages(fileWithMessages: FileWithMessages) {
        return localDatabase.deleteFileWithMessages(fileWithMessages)
    }

    override fun deleteMessages(messages: Message) {
        return localDatabase.deleteMessages(messages)
    }


}