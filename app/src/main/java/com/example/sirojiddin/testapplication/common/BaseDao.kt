package com.example.sirojiddin.testapplication.common

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Update
import io.reactivex.Single
import retrofit2.http.GET

abstract class BaseDao<in T>{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(type: T)

    @Update
    abstract fun update(type: T)

    @Delete
    abstract fun delete(type: T)
}