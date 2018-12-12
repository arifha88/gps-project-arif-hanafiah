package com.gpsbpr.apps.ui.base

import android.content.Context
import android.os.Handler
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

/**
 * Created by test(test@gmail.com) on 11/11/16.
 */

abstract class BaseAdapterItemReyclerView<Data, Holder : BaseItemRecyclerViewHolder<Data>> : RecyclerView.Adapter<Holder> {

    protected var context: Context
    internal var data: MutableList<Data?>
    var itemClickListener: OnItemClickListener? = null
    var longItemClickListener: OnLongItemClickListener? = null
    private var onLoadMoreListener: OnLoadMoreListener? = null
    private var mLinearLayoutManager: LinearLayoutManager? = null
    private var mGridLayoutManager: GridLayoutManager? = null

    protected val VIEW_TYPE_ITEM = 0
    protected val VIEW_TYPE_LOADING = 1
    protected val VIEW_TYPE_HEADER = 3
    protected val VIEW_TYPE_LOADING_NULL = 2

    // For Load More
    private var isMoreLoading: Boolean = false
    private var isLastPage: Boolean = false
    private var isRefreshPage: Boolean = false
    private val visibleItemCount: Int = 0
    private var lastVisibleItem: Int = 0
    private var totalItemCount: Int = 0

    constructor(context: Context) {
        this.context = context
        data = ArrayList()
    }

    constructor(context: Context, data: MutableList<Data?>) {
        this.context = context
        this.data = data
    }

    protected fun getView(parent: ViewGroup, viewType: Int): View {
        return LayoutInflater.from(context).inflate(getItemResourceLayout(viewType), parent, false)
    }

    protected abstract fun getItemResourceLayout(viewType: Int): Int

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        try {
            return data.size
        } catch (e: Exception) {
            return 0
        }

    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setOnItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    fun setOnLongItemClickListener(longItemClickListener: OnLongItemClickListener) {
        this.longItemClickListener = longItemClickListener
    }

    fun setOnLoadMoreListener(onLoadMoreListener: OnLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener
    }

    fun getData(): MutableList<Data?> {
        return data
    }

    fun add(item: Data?) {
        if (item != null) {
            data.add(item)
        }
        notifyItemInserted(data.size - 1)
    }

    fun add(item: Data, position: Int) {
        data.add(position, item)
        notifyItemInserted(position)
    }

    fun addAll(items: List<Data>) {
        data.addAll(items)

        notifyDataSetChanged()
    }

    fun addOrUpdate(item: Data) {
        val i = data.indexOf(item)
        if (i >= 0) {
            data[i] = item
            notifyItemChanged(i)
        } else {
            add(item)
        }
    }

    fun addOrUpdate(items: List<Data>) {
        val size = items.size

        for (i in 0 until size) {
            val item = items[i]
            val x = data.indexOf(item)
            if (x >= 0) {
                data[x] = item
            } else {
                add(item)
            }
        }
    }

    fun remove(position: Int) {
        if (position >= 0 && position < data.size) {
            data.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun remove(item: Data) {
        val position = data.indexOf(item)
        remove(position)
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    fun setLinearLayoutManager(linearLayoutManager: LinearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager
    }

    fun setGridLayoutManager(gridLayoutManager: GridLayoutManager) {
        this.mGridLayoutManager = gridLayoutManager
    }

    fun setRecyclerView(mView: RecyclerView) {
        mView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (mLinearLayoutManager != null) {
                    totalItemCount = mLinearLayoutManager!!.itemCount
                    lastVisibleItem = mLinearLayoutManager!!.findLastVisibleItemPosition()
                }

                if (mGridLayoutManager != null) {
                    totalItemCount = mGridLayoutManager!!.itemCount
                    lastVisibleItem = mGridLayoutManager!!.findLastVisibleItemPosition()
                }

                if (!isRefreshPage && !isMoreLoading && !isLastPage) {
                    if (!recyclerView.canScrollVertically(1) && onLoadMoreListener != null) {
                        onLoadMoreListener!!.onLoadMore()

                        isMoreLoading = true
                    }
                }
            }
        })
    }

    fun setProgressMore(isProgress: Boolean) {
        if (isProgress) {
            Handler().post { add(null) }
        } else {
            remove(data.size - 1)
        }
    }

    fun setProgressMore(isProgress: Boolean, isNotify: Boolean) {
        if (isProgress) {
            data.add(null)
            notifyDataSetChanged()
        } else {
            remove(data.size - 1)
        }
    }

    fun setProgressMore(isProgress: Boolean, count: Int) {
        if (isProgress) {
            for (i in 0 until count) {
                add(null)
            }
        } else {
            for (i in 0 until count) {
                remove(data.size - 1)
            }
        }
    }

    fun setMoreLoading(isMoreLoading: Boolean) {
        this.isMoreLoading = isMoreLoading
    }

    fun setLastPage(isLastPage: Boolean) {
        this.isLastPage = isLastPage
    }

    fun setRefreshPage(isRefreshPage: Boolean) {
        this.isRefreshPage = isRefreshPage
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    interface OnLongItemClickListener {
        fun onLongItemClick(view: View, position: Int)
    }

    interface OnLoadMoreListener {
        fun onLoadMore()
    }
}
