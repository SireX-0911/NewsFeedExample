package com.example.sirojiddin.testapplication.data.db

import com.example.sirojiddin.testapplication.data.MyDatabase
import com.example.sirojiddin.testapplication.data.db.entity.File
import com.example.sirojiddin.testapplication.data.db.entity.Message

class LocalDatabaseImpl(private val myDatabase: MyDatabase) : LocalDatabase {
    override fun insertFileWithMessage(file: File) {
        return myDatabase.fileDao().insertFileWithMessage(file)
    }

    override fun getFileHashCode(fileId: Int): Int {
        return myDatabase.fileDao().getFileHashCode(fileId)
    }

    override fun getFileById(fileId: Int): File {
        return myDatabase.fileDao().getFileById(fileId)
    }

    override fun clearAllTables() {
        return myDatabase.clearAllTables()
    }

    override fun insertFile(file: File) {
        return myDatabase.fileDao().insert(file)
    }

    override fun insertMessages(messages: Message) {
        return myDatabase.messageDao().insert(messages)

    }

    override fun deleteFile(file: File) {
        return myDatabase.fileDao().delete(file)
    }

    override fun deleteMessages(messages: Message) {
        return myDatabase.messageDao().delete(messages)
    }

}