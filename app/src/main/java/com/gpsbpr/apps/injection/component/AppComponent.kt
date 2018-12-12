package com.gpsbpr.apps.injection.component

import android.app.Application
import android.content.Context
import com.gpsbpr.apps.data.local.Preferences
import com.gpsbpr.apps.injection.ApplicationContext
import com.gpsbpr.apps.injection.module.AppModule
import com.gpsbpr.apps.util.notification.NotificationScheduler
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    //    fun inject(app: Application)
    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun preferences(): Preferences
}