package com.basekotlin.apps.injection.module

import android.app.Activity
import android.content.Context

import com.basekotlin.apps.injection.ActivityContext

import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return mActivity
    }

    @Provides
    @ActivityContext
    fun providesContext(): Context {
        return mActivity
    }
}
