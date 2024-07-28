package com.example.mylibrary.network

import android.content.Context
import com.ironsource.mediationsdk.IronSource
import com.ironsource.mediationsdk.adunit.adapter.listener.InterstitialAdListener
import com.ironsource.mediationsdk.sdk.BannerSmashListener
import com.ironsource.mediationsdk.sdk.LevelPlayBannerListener
import com.ironsource.mediationsdk.sdk.LevelPlayInterstitialListener

class IronSourceManager(private val context: Context) {

    fun loadAd(adUnitId: String, listener: LevelPlayInterstitialListener) {
        IronSource.setLevelPlayInterstitialListener(listener)
        IronSource.loadInterstitial()
    }

    fun showAd() {
        if (IronSource.isInterstitialReady()) {
            IronSource.showInterstitial()
        } else {
            // Handle ad not ready scenario
        }
    }
}