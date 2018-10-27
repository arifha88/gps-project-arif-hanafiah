package com.basekotlin.apps.ui.main

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.basekotlin.apps.R
import com.basekotlin.apps.data.model.local.Preferences
import com.basekotlin.apps.data.model.movie.ResponseMovie
import com.basekotlin.apps.ui.base.BaseActivity
import com.basekotlin.apps.ui.base.BaseAdapterItemReyclerView
import com.basekotlin.apps.ui.detail.DetailMovieActivity
import com.basekotlin.apps.util.DialogFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {
    override fun onCrash() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Inject
    lateinit var mMainPresenter: MainPresenter
    @Inject
    lateinit var mMovieAdapter: MovieAdapter

    lateinit var mProgressDialog: ProgressDialog

    override fun onResume() {
        super.onResume()
        getDataLocal()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_popular -> mMainPresenter.getMovie("popular")
            else -> mMainPresenter.getMovie("top_rated")
        }
        return true
    }

    override fun onSuccess(data: ResponseMovie) {
        mMovieAdapter.clear()
        mMovieAdapter.addAll(data.results)
    }

    override fun onDismissLoading() {
        if (mProgressDialog.isShowing){
            mProgressDialog.dismiss()
        }
    }

    override fun onShowLoading() {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog.setMessage("Please wait...")
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()
    }

    override fun onFailed(message: String) {
        DialogFactory.createGenericErrorDialog(this, message).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent()?.inject(this)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initData()
    }

    fun initData() {
        mMainPresenter.attachView(this)
        mMainPresenter.getMovie("popular")

        rv_content.setUpAsGrid(2)
        rv_content.setUpItemDecoration(2,16,true)
        rv_content.adapter = mMovieAdapter

        mMovieAdapter.setOnItemClickListener(object : BaseAdapterItemReyclerView.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                startActivity(Intent(baseContext, DetailMovieActivity::class.java).putExtra("movie_id", mMovieAdapter.getData().get(position)?.id));
            }
        })
    }

    fun getDataLocal(){
        mMovieAdapter.clear()
        mMovieAdapter.addAll(Preferences.getlistMovie())
    }
}
