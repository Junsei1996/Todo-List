package com.example.todolist.ui.activity

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.todolist.BuildConfig
import com.example.todolist.R
import com.example.todolist.base.BaseActivity
import com.example.todolist.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : BaseActivity() {

    lateinit var mBinding: ActivityMainBinding;

    lateinit var navController: NavController

    override fun getActivityLayout(): Int = R.layout.activity_main

    override fun getViewBinding() {

        val backgroundScope = CoroutineScope(Dispatchers.IO)
        backgroundScope.launch {
            // Initialize the Google Mobile Ads SDK on a background thread.
            MobileAds.initialize(this@MainActivity) {}
        }

        mBinding = binding as ActivityMainBinding

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fHomeFrag) as NavHostFragment
        navController = navHostFragment.navController
        navController.setGraph(R.navigation.main_nav)

        mBinding.apply {
//            adview.adUnitId = "ca-app-pub-4595499267737034/3887601742" // ACCOUNT UNIT ID
//            adview.adUnitId = "ca-app-pub-3940256099942544/6300978111" // TEST UNIT ID
//            adview.setAdSize(AdSize.BANNER)

            val adView : AdView = AdView(this@MainActivity);
            val adUnitId = BuildConfig.BANNER_AD_UNIT_ID
            adView.adUnitId = adUnitId;
            adView.setAdSize(AdSize.BANNER);

            llAdview.addView(adView)
            loadAd(adView)
        }

    }

    private fun loadAd(adView: AdView) {
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    override fun setClickListeners() {
        mBinding.tabLayout.setOnItemSelectedListener {

            navController.setGraph(
                when (it.itemId) {
                    R.id.iHome -> R.navigation.main_nav
                    R.id.iCompleted -> R.navigation.completed_nav
                    R.id.iArchived -> R.navigation.history_nav
                    else -> R.navigation.main_nav
                }
            )

            return@setOnItemSelectedListener true
        }
    }

    override fun onClick(p0: View?) {

    }





}