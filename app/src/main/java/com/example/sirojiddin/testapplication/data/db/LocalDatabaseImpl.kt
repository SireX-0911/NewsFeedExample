package com.example.sirojiddin.testapplication.data.db

import com.example.sirojiddin.testapplication.data.MyDatabase
import com.example.sirojiddin.testapplication.data.db.entity.File
import com.example.sirojiddin.testapplication.data.db.entity.FileWithMessages
import com.example.sirojiddin.testapplication.data.db.entity.Message
import io.reactivex.Single

class LocalDatabaseImpl(private val myDatabase: MyDatabase) : LocalDatabase {

    override fun getFileHashCode(fileId: Int): Int {
        return myDatabase.fileDao().getFileHashCode(fileId)
    }

    override fun getFileById(fileId: Int): File {
        return myDatabase.fileDao().getFileById(fileId)
    }

    override fun getMessages(fileId: Int): FileWithMessages {
        return myDatabase.fileDao().getMessages(fileId)
    }

    override fun clearAllTables() {
        return myDatabase.clearAllTables()
    }

    override fun insertFile(file: File) {
        return myDatabase.fileDao().insert(file)
    }

    override fun insertFileWithMessages(fileWithMessages: FileWithMessages) {
        return myDatabase.fileWithMessagesDao().insert(fileWithMessages)
    }

    override fun insertMessages(messages: Message) {
        return myDatabase.messageDao().insert(messages)

    }

    override fun deleteFile(file: File) {
        return myDatabase.fileDao().delete(file)
    }

    override fun deleteFileWithMessages(fileWithMessages: FileWithMessages) {
        return myDatabase.fileWithMessagesDao().delete(fileWithMessages)
    }

    override fun deleteMessages(messages: Message) {
        return myDatabase.messageDao().delete(messages)
    }

}