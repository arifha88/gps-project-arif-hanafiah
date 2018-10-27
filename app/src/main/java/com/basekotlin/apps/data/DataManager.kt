package com.basekotlin.apps.data

import com.basekotlin.apps.data.model.category.ResponseCategory
import com.basekotlin.apps.data.model.movie.ResponseMovie
import com.basekotlin.apps.data.model.movie.detail.ResponseDetailMovie
import com.basekotlin.apps.data.remote.RestService
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject
constructor(val mRestService: RestService) {

    fun getCategory(): Observable<List<ResponseCategory>> {
        return mRestService.getCategory()
    }

    fun getMovie(value: String): Observable<ResponseMovie> {
        return mRestService.getMovie(value,"603a380c52261d0bd05cb6218bc057de")
    }

    fun getDetailMovie(movie_id: Int): Observable<ResponseDetailMovie> {
        return mRestService.getDetailMovie(movie_id,"603a380c52261d0bd05cb6218bc057de")
    }

}