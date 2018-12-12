package com.gpsbpr.apps.util.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.SystemClock
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder

import com.gpsbpr.apps.R

import java.util.Calendar

import android.content.Context.ALARM_SERVICE
import android.location.Location
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.format.DateFormat
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.gpsbpr.apps.data.local.Preferences
import com.gpsbpr.apps.data.model.DataGps
import javax.inject.Inject
import javax.inject.Singleton

@SuppressLint("StaticFieldLeak")
object NotificationScheduler{

    val DAILY_REMINDER_REQUEST_CODE = 100
    val TAG = "NotificationScheduler"

    fun setReminder(context: Context, cls: Class<*>) {

        val receiver = ComponentName(context, cls)
        val pm = context.packageManager

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP)


        val intent1 = Intent(context, cls)
        val pendingIntent = PendingIntent.getBroadcast(context, DAILY_REMINDER_REQUEST_CODE, intent1, PendingIntent.FLAG_UPDATE_CURRENT)
        val am = context.getSystemService(ALARM_SERVICE) as AlarmManager
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(),
                60000,
                pendingIntent)

    }

}
