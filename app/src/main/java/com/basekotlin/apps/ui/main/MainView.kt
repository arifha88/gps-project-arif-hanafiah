package com.basekotlin.apps.ui.main

import com.basekotlin.apps.data.model.category.ResponseCategory
import com.basekotlin.apps.data.model.movie.ResponseMovie
import com.basekotlin.apps.ui.base.MvpView

interface MainView : MvpView {
    fun onSuccess(data: ResponseMovie)
    fun onCrash()
}
