package com.gpsbpr.apps.ui.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by test(test@gmail.com) on 11/11/16.
 */

abstract class BaseItemRecyclerViewHolder<Data>(protected var mContext: Context, itemView: View,
                                                private val itemClickListener: BaseAdapterItemReyclerView.OnItemClickListener?,
                                                private val longItemClickListener: BaseAdapterItemReyclerView.OnLongItemClickListener?) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {

    var isHasHeader = false

    init {
        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
    }

    abstract fun bind(data: Data?)

    override fun onClick(v: View) {
        itemClickListener?.onItemClick(v, if (isHasHeader) adapterPosition - 1 else adapterPosition)
    }

    override fun onLongClick(v: View): Boolean {
        if (longItemClickListener != null) {
            longItemClickListener.onLongItemClick(v,
                    if (isHasHeader) adapterPosition - 1 else adapterPosition)
            return true
        }
        return false
    }
}

