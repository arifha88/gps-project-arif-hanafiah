package com.gpsbpr.apps.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.gpsbpr.apps.ui.base.BaseAdapterItemReyclerView
import com.gpsbpr.apps.ui.base.BaseItemRecyclerViewHolder
import com.bumptech.glide.Glide
import com.gpsbpr.apps.data.model.DataGps
import kotlinx.android.synthetic.main.item_gps.view.*

class ItemHolder(mContext: Context, itemView: View, itemClickListener: BaseAdapterItemReyclerView.OnItemClickListener?,
                 longItemClickListener: BaseAdapterItemReyclerView.OnLongItemClickListener?) :
        BaseItemRecyclerViewHolder<DataGps>(mContext, itemView, itemClickListener, longItemClickListener) {

    @SuppressLint("SetTextI18n")
    override fun bind(data: DataGps?) {
        itemView.textDate.text = data?.dateTime
        itemView.textLatitude.text = "Latitude : ${data?.latitude}"
        itemView.textLongitude.text = "Longitude : ${data?.longitude}"
        itemView.textAccuracy.text = "Accuracy : ${data?.accuracy}"
        itemView.textSrc.text = "Src : ${data?.action}"

    }
}