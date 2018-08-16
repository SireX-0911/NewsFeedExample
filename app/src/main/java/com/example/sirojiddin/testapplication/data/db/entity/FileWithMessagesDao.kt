package com.example.sirojiddin.testapplication.data.db.entity

import android.arch.persistence.room.Dao
import com.example.sirojiddin.testapplication.common.BaseDao

@Dao
abstract class FileWithMessagesDao : BaseDao<FileWithMessages>() {
}