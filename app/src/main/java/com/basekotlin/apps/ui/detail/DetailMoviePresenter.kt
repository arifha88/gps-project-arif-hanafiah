package com.basekotlin.apps.ui.detail

import com.basekotlin.apps.data.DataManager
import com.basekotlin.apps.ui.base.BasePresenter
import com.basekotlin.apps.util.RetrofitError
import com.basekotlin.apps.util.RxUtil
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class DetailMoviePresenter @Inject
constructor(dataManager: DataManager) : BasePresenter<DetailMovieView>() {
    private var mDisposable: Disposable? = null

    init {
        mDataManager = dataManager
    }

    override fun attachView(mvpView: DetailMovieView) {
        super.attachView(mvpView)
    }

    override fun detachView() {
        super.detachView()
        if (mDisposable != null) mDisposable!!.dispose()
    }



    fun getDetailMovie(movie_id: Int) {
        checkViewAttached()
        RxUtil.dispose(mDisposable)
        mvpView?.onShowLoading()
        mDisposable = mDataManager!!.getDetailMovie(movie_id)
                .compose(RxUtil.applySchedulersIo())
                .doOnTerminate{ mvpView?.onDismissLoading() }
                .subscribe({ dataResponse ->
                    mvpView?.onDetailMovie(dataResponse)
                }, { throwable ->
                    mvpView?.onFailed("Unable to contact the server")
                })
    }
}