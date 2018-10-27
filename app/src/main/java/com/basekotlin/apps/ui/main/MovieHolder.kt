package com.basekotlin.apps.ui.main

import android.content.Context
import android.view.View
import com.basekotlin.apps.data.model.movie.ResultsItem
import com.basekotlin.apps.ui.base.BaseAdapterItemReyclerView
import com.basekotlin.apps.ui.base.BaseItemRecyclerViewHolder
import com.basekotlin.apps.util.GlideApp
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.content_detail_movie.view.*
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieHolder(mContext: Context, itemView: View, itemClickListener: BaseAdapterItemReyclerView.OnItemClickListener?,
                  longItemClickListener: BaseAdapterItemReyclerView.OnLongItemClickListener?) :
        BaseItemRecyclerViewHolder<ResultsItem>(mContext, itemView, itemClickListener, longItemClickListener) {

    override fun bind(data: ResultsItem?) {
        Glide.with(mContext).load("https://image.tmdb.org/t/p/original/${data?.poster_path}").into(itemView.image_movie)
        itemView.title_movie.setText(data?.title)
    }
}