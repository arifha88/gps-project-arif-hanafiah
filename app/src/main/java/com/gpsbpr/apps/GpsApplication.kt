package com.gpsbpr.apps

import android.app.Application
import android.content.Context
import android.support.v7.app.AppCompatDelegate
import com.gpsbpr.apps.injection.component.AppComponent
import com.gpsbpr.apps.injection.component.DaggerAppComponent
import com.gpsbpr.apps.injection.module.AppModule
import io.realm.Realm
import io.realm.RealmConfiguration
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

public class GpsApplication : Application() {

    internal var mApplicationComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        setupFont()
        realmSetup()
    }

    fun getComponent(): AppComponent? {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerAppComponent.builder()
                    .appModule(AppModule(this))
                    .build()
        }
        return mApplicationComponent
    }

    fun setupFont() {
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/roboto_regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build())
    }

    fun realmSetup() {
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1)
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    companion object {

        operator fun get(context: Context): GpsApplication {
            return context.applicationContext as GpsApplication
        }
    }
}