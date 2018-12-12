package com.gpsbpr.apps.injection.module

import android.app.Application
import android.content.Context
import com.gpsbpr.apps.injection.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(protected val app: Application) {

    @Provides
    fun provideApplication() = app

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return app
    }
}