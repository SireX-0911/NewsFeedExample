package com.example.sirojiddin.testapplication.common

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.example.sirojiddin.testapplication.config.scope.PerActivity
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class BaseActivityModule {


    @Binds
    @PerActivity
    abstract fun activityContext(activity: AppCompatActivity): Context

    @Module
    companion object {
        @JvmStatic
        @Provides
        @PerActivity
        fun activityFragmentManager(activity: AppCompatActivity): FragmentManager = activity.supportFragmentManager
    }
}