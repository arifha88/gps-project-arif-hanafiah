package com.basekotlin.apps.data.model.movie

import io.realm.RealmList
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

open class ResultsItem : RealmObject() {
    @PrimaryKey var id: Int = 0
    var overview: String? = null
    var original_language: String? = null
    var original_title: String? = null
    var video: Boolean? = null
    var title: String? = null
    var genre_ids: RealmList<Int?>? = null
    var poster_path: String? = null
    var backdrop_path: String? = null
    var release_date: String? = null
    var vote_average: Double? = null
    var popularity: Double? = null
    var adult: Boolean? = null
    var vote_count: Int? = null

}
