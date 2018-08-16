package com.example.sirojiddin.testapplication.data.db.entity

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.sirojiddin.testapplication.common.BaseDao
import io.reactivex.Single

@Dao
abstract class FileDao : BaseDao<File>(){
    @Query("SELECT hashCode FROM File WHERE id =:fileId")
    abstract fun getFileHashCode(fileId : Int) : Int
    @Query("SELECT * FROM File WHERE id =:fileId")
    abstract fun getFileById(fileId: Int) : File
    @Query("SELECT * FROM FileWithMessages  WHERE fileId =:fileId")
    abstract fun getMessages(fileId: Int) : FileWithMessages
}