package com.basekotlin.apps.ui.main

import android.app.Activity
import android.view.ViewGroup

import com.basekotlin.apps.R
import com.basekotlin.apps.data.model.movie.ResultsItem
import com.basekotlin.apps.ui.base.BaseAdapterItemReyclerView

import javax.inject.Inject

class MovieAdapter @Inject
constructor(activity: Activity) : BaseAdapterItemReyclerView<ResultsItem, MovieHolder>(activity) {

    override fun getItemResourceLayout(viewType: Int): Int {
        return R.layout.item_movie
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(context, getView(parent, viewType), itemClickListener, longItemClickListener)
    }
}