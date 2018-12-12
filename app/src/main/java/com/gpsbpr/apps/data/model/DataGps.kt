package com.gpsbpr.apps.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class DataGps(
        @PrimaryKey
        var dateTime: String? = null,
        var latitude: String? = null,
        var longitude: String? = null,
        var accuracy: String? = null,
        var action: String? = null
) : RealmObject()
