package com.gpsbpr.apps.injection.component

import com.gpsbpr.apps.injection.PerActivity
import com.gpsbpr.apps.injection.module.ActivityModule
import com.gpsbpr.apps.ui.detail.ShowGpsActivity
import com.gpsbpr.apps.ui.main.MainActivity

import dagger.Subcomponent

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(showGpsActivity: ShowGpsActivity)
}
