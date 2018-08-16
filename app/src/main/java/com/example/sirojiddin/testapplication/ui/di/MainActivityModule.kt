package com.example.sirojiddin.testapplication.ui.di

import android.support.v7.app.AppCompatActivity
import com.example.sirojiddin.testapplication.common.BaseActivityModule
import com.example.sirojiddin.testapplication.config.scope.PerActivity
import com.example.sirojiddin.testapplication.config.scope.PerFragment
import com.example.sirojiddin.testapplication.ui.MainActivity
import com.example.sirojiddin.testapplication.ui.MainActivityContract
import com.example.sirojiddin.testapplication.ui.MainActivityPresenter
import com.example.sirojiddin.testapplication.ui.messages.MessagesFragment
import com.example.sirojiddin.testapplication.ui.messages.di.MessagesFragmentModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = arrayOf(BaseActivityModule::class))
abstract class MainActivityModule {
    @Binds
    @PerActivity
    internal abstract fun provideMainActivity(mainActivity: MainActivity): AppCompatActivity

    @Binds
    @PerActivity
    abstract fun provideMainActivityView(mainActivity: MainActivity): MainActivityContract.View

    @Binds
    @PerActivity
    abstract fun provideMainActivityPresenter(mainActivityPresenter: MainActivityPresenter): MainActivityContract.Presenter

    @PerFragment
    @ContributesAndroidInjector(modules = [MessagesFragmentModule::class])
    abstract fun contributeMessagesFragment(): MessagesFragment
}