package com.example.sirojiddin.testapplication.config

import com.example.sirojiddin.testapplication.data.DatabaseManager
import com.example.sirojiddin.testapplication.data.DatabaseManagerImpl
import com.example.sirojiddin.testapplication.data.MyDatabase
import com.example.sirojiddin.testapplication.data.db.LocalDatabase
import com.example.sirojiddin.testapplication.data.db.LocalDatabaseImpl
import com.example.sirojiddin.testapplication.data.network.ApiDatabase
import com.example.sirojiddin.testapplication.data.network.ApiDatabaseImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class BaseModule {

    @Provides
    @Singleton
    fun provideDatabaseManager(localDatabase: LocalDatabase, apiDatabase: ApiDatabase): DatabaseManager =
            DatabaseManagerImpl(localDatabase, apiDatabase)

    @Provides
    @Singleton
    fun provideLocalDatabase(myDatabase: MyDatabase): LocalDatabase = LocalDatabaseImpl(myDatabase)

    @Provides
    @Singleton
    fun provideApiDatabase(retrofit: Retrofit): ApiDatabase =
            ApiDatabaseImpl(retrofit)
}