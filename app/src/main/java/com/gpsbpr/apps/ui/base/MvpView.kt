package com.gpsbpr.apps.ui.base

interface MvpView {
    fun onDismissLoading()
    fun onShowLoading()
    fun onFailed(message: String)
}