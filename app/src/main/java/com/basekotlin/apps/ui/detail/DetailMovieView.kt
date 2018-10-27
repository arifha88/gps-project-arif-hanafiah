package com.basekotlin.apps.ui.detail

import com.basekotlin.apps.data.model.movie.detail.ResponseDetailMovie
import com.basekotlin.apps.ui.base.MvpView

interface DetailMovieView : MvpView {
    fun onDetailMovie(data: ResponseDetailMovie)
}