package com.basekotlin.apps.ui.main

import com.basekotlin.apps.data.DataManager
import com.basekotlin.apps.data.model.local.Preferences
import com.basekotlin.apps.data.model.movie.ResponseMovie
import com.basekotlin.apps.injection.ConfigPersistent
import com.basekotlin.apps.ui.base.BasePresenter
import com.basekotlin.apps.util.RetrofitError
import com.basekotlin.apps.util.RxUtil

import javax.inject.Inject

import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import okhttp3.MultipartBody

@ConfigPersistent
class MainPresenter @Inject
constructor(dataManager: DataManager) : BasePresenter<MainView>() {

    private var mDisposable: Disposable? = null

    init {
        mDataManager = dataManager
    }

    override fun attachView(mvpView: MainView) {
        super.attachView(mvpView)
    }

    override fun detachView() {
        super.detachView()
        if (mDisposable != null) mDisposable!!.dispose()
    }

    fun getMovie(value: String) {
        checkViewAttached()
        RxUtil.dispose(mDisposable)
        mvpView?.onShowLoading()
        mDisposable = mDataManager!!.getMovie(value)
                .compose(RxUtil.applySchedulersIo())
                .doOnTerminate { mvpView?.onDismissLoading() }
                .subscribe({ dataResponse ->
                    Preferences.saveListMovie(dataResponse.results)
                    mvpView?.onSuccess(dataResponse)
                }, { throwable ->
                    mvpView?.onFailed("Unable to contact the server")
                })
    }
}
