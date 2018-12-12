package com.gpsbpr.apps.util.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.gpsbpr.apps.GpsApplication
import com.gpsbpr.apps.data.local.Preferences
import com.gpsbpr.apps.data.model.DataGps
import com.gpsbpr.apps.injection.component.DaggerConfigPersistentComponent
import com.gpsbpr.apps.ui.main.MainActivity
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Jaison on 17/06/17.
 */
class AlarmReceiver: BroadcastReceiver(){

    internal var TAG = "AlarmReceiver"

    override fun onReceive(context: Context?, intent: Intent) {
        // TODO Auto-generated method stub

        if (intent.action != null) {
            if (intent.action!!.equals(Intent.ACTION_BOOT_COMPLETED, ignoreCase = true)) {
                // Set the alarm here.
                Log.d(TAG, "onReceive: BOOT_COMPLETED")
                NotificationScheduler.setReminder(context!!, AlarmReceiver::class.java)
                return
            }
        }

        Log.d(TAG, "onReceive: ")

        val dataNotify = DataGps()
        EventBus.getDefault().post(dataNotify)
    }
}


