package com.basekotlin.apps.injection.module

import android.app.Application
import android.content.Context
import com.basekotlin.apps.data.remote.RestService
import com.basekotlin.apps.injection.ApplicationContext
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

    @Provides
    @Singleton
    fun provideAppService(): RestService {
        return RestService.Creator.newRestService()
    }
}