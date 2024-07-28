package com.example.mylibrary.network

import android.app.Activity
import android.content.Context
import com.applovin.sdk.AppLovinSdk
import com.applovin.mediation.ads.MaxAdView

class AppLovinManager(context: Context) {
    init {
        AppLovinSdk.getInstance(context).mediationProvider = "max"
        AppLovinSdk.getInstance(context).initializeSdk()
    }

    fun createAdView(activity: Activity, adUnitId: String): MaxAdView {
        val adView = MaxAdView(adUnitId, activity)
        return adView
    }

    fun loadAd(adView: MaxAdView) {
        adView.loadAd()
    }
}