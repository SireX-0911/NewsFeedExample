package com.example.sirojiddin.testapplication.config

import com.example.sirojiddin.testapplication.config.scope.PerActivity
import com.example.sirojiddin.testapplication.ui.MainActivity
import com.example.sirojiddin.testapplication.ui.di.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ApplicationModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    internal abstract fun contributeMainActivity(): MainActivity
}