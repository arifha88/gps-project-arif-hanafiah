package com.gpsbpr.apps.ui.detail

import android.app.Activity
import android.view.ViewGroup

import com.gpsbpr.apps.R
import com.gpsbpr.apps.data.model.DataGps
import com.gpsbpr.apps.ui.base.BaseAdapterItemReyclerView

import javax.inject.Inject

class ItemAdapter @Inject
constructor(activity: Activity) : BaseAdapterItemReyclerView<DataGps, ItemHolder>(activity) {

    override fun getItemResourceLayout(viewType: Int): Int {
        return R.layout.item_gps
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(context, getView(parent, viewType), itemClickListener, longItemClickListener)
    }
}