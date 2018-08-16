package com.example.sirojiddin.testapplication.ui.messages.di

import android.support.v4.app.Fragment
import com.example.sirojiddin.testapplication.config.scope.PerFragment
import com.example.sirojiddin.testapplication.ui.messages.MessagesContract
import com.example.sirojiddin.testapplication.ui.messages.MessagesFragment
import com.example.sirojiddin.testapplication.ui.messages.MessagesPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class MessagesFragmentModule {
    @Binds
    @PerFragment
    abstract fun provideFragment(fragment: MessagesFragment): Fragment

    @Binds
    @PerFragment
    abstract fun provideMessagesView(fragment: MessagesFragment): MessagesContract.View

    @Binds
    @PerFragment
    abstract fun provideMessagesPresenter(presenter: MessagesPresenter): MessagesContract.Presenter
}