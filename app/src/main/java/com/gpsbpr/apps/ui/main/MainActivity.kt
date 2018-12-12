package com.gpsbpr.apps.ui.main

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.gpsbpr.apps.R
import com.gpsbpr.apps.ui.base.BaseActivity
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.gpsbpr.apps.data.local.Preferences
import com.gpsbpr.apps.data.model.DataGps
import com.gpsbpr.apps.ui.detail.ShowGpsActivity
import com.gpsbpr.apps.util.notification.AlarmReceiver
import com.gpsbpr.apps.util.notification.NotificationScheduler
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import javax.inject.Inject

class MainActivity : BaseActivity(), GoogleApiClient.ConnectionCallbacks, LocationListener {

    @Inject
    lateinit var preferences: Preferences
    private var mGoogleApiClient: GoogleApiClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent()?.inject(this)
        setContentView(R.layout.activity_main)
        initData()
    }

    fun initData() {
        mGoogleApiClient = GoogleApiClient.Builder(this).addConnectionCallbacks(this).addApi(LocationServices.API).build()
        mGoogleApiClient?.connect()

        btnGetGps.setOnClickListener {
            saveData("Button")
        }
        btnShowGps.setOnClickListener {
            startActivity(Intent(this, ShowGpsActivity::class.java))
        }
        NotificationScheduler.setReminder(this, AlarmReceiver::class.java)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    private fun saveData(action: String?){
        val data = DataGps(
                textValueTime.text.toString(),
                textValueLatitude.text.toString(),
                textValueLongitude.text.toString(),
                textValueAccuracy.text.toString(),
                action)
        preferences.saveDataGps(data)
        Toast.makeText(this, "Save Data GPS Success", Toast.LENGTH_LONG).show()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(data: DataGps?) {
        saveData("Scheduler")
    }

    @Suppress("DEPRECATION")
    override fun onConnected(bundle: Bundle?) {
        val mLocationRequest = LocationRequest()
        mLocationRequest.interval = 1000
        mLocationRequest.fastestInterval = 1000
        mLocationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        if (ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this::onLocationChanged)
        }
    }

    override fun onLocationChanged(location: Location?) {
        if (location?.latitude != 0.0 && location?.longitude != 0.0) {
            textValueLatitude.text = location?.latitude?.toString()
            textValueLongitude.text = location?.longitude?.toString()
            textValueAccuracy.text = location?.accuracy?.toString()
            textValueTime.text = android.text.format.DateFormat.format("yyyy-MM-dd HH:mm:ss", location?.time!!).toString()
        }
        //menghentikan pembaruan lokasi
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this::onLocationChanged)
        }
    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

    }

    override fun onProviderEnabled(p0: String?) {

    }

    override fun onProviderDisabled(p0: String?) {

    }
}
