package com.basekotlin.apps.injection.component

import com.basekotlin.apps.injection.PerActivity
import com.basekotlin.apps.injection.module.ActivityModule
import com.basekotlin.apps.ui.detail.DetailMovieActivity
import com.basekotlin.apps.ui.main.MainActivity

import dagger.Subcomponent

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(detailMovieActivity: DetailMovieActivity)
}
