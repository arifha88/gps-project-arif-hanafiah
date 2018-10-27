package com.basekotlin.apps.ui.detail

import android.app.ProgressDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.basekotlin.apps.R
import com.basekotlin.apps.data.model.movie.detail.ResponseDetailMovie
import com.basekotlin.apps.ui.base.BaseActivity
import com.basekotlin.apps.util.DialogFactory
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.content_detail_movie.*
import javax.inject.Inject

class DetailMovieActivity : BaseActivity(), DetailMovieView {

    @Inject
    lateinit var mDetailMoviePresenter: DetailMoviePresenter

    lateinit var mProgressDialog: ProgressDialog

    override fun onDetailMovie(data: ResponseDetailMovie) {
        Glide.with(this).load("https://image.tmdb.org/t/p/original/${data.poster_path}").into(header)
        text_rating.setText(data.vote_average.toString())
        text_title.setText(data.title)
        text_content.setText(data.overview)
    }

    override fun onDismissLoading() {
        mProgressDialog.dismiss()
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

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent()?.inject(this)
        setContentView(R.layout.activity_detail_movie)
        initData()
    }

    fun initData() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mDetailMoviePresenter.attachView(this)
        mDetailMoviePresenter.getDetailMovie(intent.getIntExtra("movie_id", 0))
    }
}
