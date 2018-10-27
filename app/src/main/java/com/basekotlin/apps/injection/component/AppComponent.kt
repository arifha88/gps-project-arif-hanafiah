package com.basekotlin.apps.injection.component

import android.app.Application
import android.content.Context
import com.basekotlin.apps.data.DataManager
import com.basekotlin.apps.injection.ApplicationContext
import com.basekotlin.apps.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    //    fun inject(app: Application)
    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager
}