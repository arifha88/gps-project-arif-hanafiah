package com.gpsbpr.apps.ui.detail

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.gpsbpr.apps.R
import com.gpsbpr.apps.data.local.Preferences
import com.gpsbpr.apps.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_show_gps.*
import javax.inject.Inject

class ShowGpsActivity : BaseActivity() {

    @Inject
    lateinit var itemAdapter: ItemAdapter
    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent()?.inject(this)
        setContentView(R.layout.activity_show_gps)
        initData()
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

    private fun initData() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Show GPS"

        rvContent.setUpAsList()
        rvContent.adapter = itemAdapter

        if (!preferences.getDataGps().isEmpty()){
            if (layoutNoData.visibility == View.VISIBLE){
                layoutNoData.visibility = View.GONE
            }
            itemAdapter.clear()
            itemAdapter.addAll(preferences.getDataGps())
        } else {
            layoutNoData.visibility = View.VISIBLE
        }
    }
}
