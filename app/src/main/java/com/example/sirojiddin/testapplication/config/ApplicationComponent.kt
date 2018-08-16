package com.example.sirojiddin.testapplication.config

import android.app.Application
import com.example.sirojiddin.testapplication.TestApp
import com.example.sirojiddin.testapplication.data.DatabaseModule
import com.example.sirojiddin.testapplication.data.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class), (ApplicationModule::class), (BaseModule::class), (NetworkModule::class), (DatabaseModule::class)])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: TestApp)
}