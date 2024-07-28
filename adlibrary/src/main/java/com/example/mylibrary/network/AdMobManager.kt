package com.example.mylibrary.network

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class AdMobManager(context: Context) {
    init {
        MobileAds.initialize(context) {}
    }

    fun loadAd(adUnitId: String, adView: AdView) {
        val adRequest = AdRequest.Builder().build()
        adView.adUnitId = adUnitId
        adView.loadAd(adRequest)
    }
}