package com.gpsbpr.apps.data.local

import android.util.Log
import com.gpsbpr.apps.data.model.DataGps
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Preferences @Inject constructor() {
    var mRealm: Realm? = null

    //method save data gps
    fun saveDataGps(data: DataGps) {
        mRealm = Realm.getDefaultInstance()
        mRealm!!.beginTransaction()
        mRealm!!.copyToRealmOrUpdate(data)
        mRealm!!.commitTransaction()
        mRealm!!.close()
    }

    //method get data gps
    fun getDataGps(): List<DataGps> {
        mRealm = Realm.getDefaultInstance()
        val data = mRealm!!.where(DataGps::class.java)
                .findAll()
        Log.i("Data Realm : ", data.toString())
        if (data.size > 10){
            return data.reversed().subList(0, 10)
        } else {
            return data.reversed()
        }
    }
}