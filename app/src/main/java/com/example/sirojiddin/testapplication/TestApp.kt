package com.example.sirojiddin.testapplication

import android.app.Activity
import android.app.Application
import com.example.sirojiddin.testapplication.config.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TestApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}