package com.basekotlin.apps.data.model.local

import android.annotation.SuppressLint
import android.util.Log

import com.basekotlin.apps.data.model.movie.ResultsItem

import io.realm.Realm
import io.realm.RealmResults

object Preferences {
    @SuppressLint("StaticFieldLeak")
    var mRealm: Realm? = null
    //method save data list movie
    fun saveListMovie(movie: List<ResultsItem>) {
        mRealm = Realm.getDefaultInstance()
        mRealm!!.beginTransaction()
        mRealm!!.delete(ResultsItem::class.java)
        mRealm!!.copyToRealmOrUpdate(movie)
        mRealm!!.commitTransaction()
        mRealm!!.close()
    }

    //method get list movie
    fun getlistMovie(): List<ResultsItem> {
        mRealm = Realm.getDefaultInstance()
        val data = mRealm!!.where(ResultsItem::class.java).findAll()
        Log.i("Data Realm : ", data.toString())
        return data
    }
}
