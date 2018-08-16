package com.example.sirojiddin.testapplication.data.db.entity

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.sirojiddin.testapplication.common.BaseDao

@Dao
abstract class FileDao : BaseDao<File>() {
    @Query("SELECT hashSum FROM File WHERE id =:fileId")
    abstract fun getFileHashCode(fileId: Int): Int

    @Query("SELECT * FROM File WHERE id =:fileId")
    abstract fun getFileById(fileId: Int): File

    @Query("SELECT * FROM Message WHERE fileId =:fileId")
    abstract fun getMessageById(fileId: Int): List<Message>

    @Insert
    abstract fun insertFile(file: File)

    @Insert
    abstract fun insertMessageList(messages: List<Message>)

    fun insertFileWithMessage(file: File) {
        val messages = file.messages
        for (i in messages!!.indices) {
            messages[i].fileId = file.id
        }
        insertFile(file)
        insertMessageList(messages)
    }
}