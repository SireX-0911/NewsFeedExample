package com.example.sirojiddin.testapplication.data

import android.app.Application
import android.arch.persistence.room.Room
import com.example.sirojiddin.testapplication.data.db.entity.FileDao
import com.example.sirojiddin.testapplication.data.db.entity.FileWithMessagesDao
import com.example.sirojiddin.testapplication.data.db.entity.MessageDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class DatabaseModule {

    @Singleton
    @Provides
    fun provideInvDatabase(context: Application): MyDatabase {
        return Room.databaseBuilder(
                context,
                MyDatabase::class.java,
                "myDatabase.db").build()
    }

    @Singleton
    @Provides
    fun provideMessageDao(db: MyDatabase): MessageDao {
        return db.messageDao()
    }

    @Singleton
    @Provides
    fun provideFileDao(db: MyDatabase): FileDao {
        return db.fileDao()
    }

    @Singleton
    @Provides
    fun provideFileWithMessagesDao(db: MyDatabase): FileWithMessagesDao {
        return db.fileWithMessagesDao()
    }

}
