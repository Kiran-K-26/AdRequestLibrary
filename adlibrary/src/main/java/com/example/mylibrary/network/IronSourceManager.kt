//package com.example.mylibrary.network
//
//import android.content.Context
//import com.ironsource.mediationsdk.IronSource
//import com.ironsource.mediationsdk.adunit.adapter.listener.InterstitialAdListener
//import com.ironsource.mediationsdk.sdk.BannerSmashListener
//import com.ironsource.mediationsdk.sdk.LevelPlayBannerListener
//import com.ironsource.mediationsdk.sdk.LevelPlayInterstitialListener
//
//class IronSourceManager(private val context: Context) {
//
//    fun loadAd(adUnitId: String, listener: LevelPlayInterstitialListener) {
//        IronSource.setLevelPlayInterstitialListener(listener)
//        IronSource.loadInterstitial()
//    }
//
//    fun showAd() {
//        if (IronSource.isInterstitialReady()) {
//            IronSource.showInterstitial()
//        } else {
//            // Handle ad not ready scenario
//        }
//    }
//}

//package com.example.mylibrary.network
//
//import android.content.Context
//import android.widget.FrameLayout
//import com.ironsource.mediationsdk.IronSource
//import com.ironsource.mediationsdk.IronSourceBannerLayout
//import com.ironsource.mediationsdk.adunit.adapter.listener.InterstitialAdListener
//import com.ironsource.mediationsdk.sdk.LevelPlayBannerListener
//import com.ironsource.mediationsdk.sdk.LevelPlayInterstitialListener
//
//class IronSourceManager(private val context: Context) {
//
//    fun loadBannerAd(adUnitId: String, adViewContainer: FrameLayout) {
//        IronSource.loadBanner(adViewContainer as IronSourceBannerLayout?)
//        // Implement banner ad loading logic and listeners
//    }
//
//    fun loadInterstitialAd(adUnitId: String, listener: LevelPlayInterstitialListener) {
//        IronSource.setLevelPlayInterstitialListener(listener)
//        IronSource.loadInterstitial()
//    }
//
//    fun showInterstitialAd() {
//        if (IronSource.isInterstitialReady()) {
//            IronSource.showInterstitial()
//        } else {
//            // Handle ad not ready scenario
//        }
//    }
//}


//package com.example.mylibrary.network
//
//import android.content.Context
//import android.widget.FrameLayout
//import com.ironsource.mediationsdk.IronSource
//import com.ironsource.mediationsdk.ISBannerSize
//import com.ironsource.mediationsdk.sdk.LevelPlayBannerListener
//import com.ironsource.mediationsdk.sdk.LevelPlayInterstitialListener
//import com.ironsource.mediationsdk.adunit.adapter.utility.AdInfo
//import com.ironsource.mediationsdk.logger.IronSourceError
//
//class IronSourceManager(private val context: Context) {
//
//    fun loadBannerAd(adUnitId: String, adViewContainer: FrameLayout) {
//        val bannerAdView = IronSource.createBanner(context, ISBannerSize.BANNER)
//        IronSource.setLevelPlayBannerListener(object : LevelPlayBannerListener {
//            override fun onBannerAdLoaded() {
//                adViewContainer.addView(bannerAdView)
//            }
//
//            override fun onBannerAdLoadFailed(p0: IronSourceError?) {
//                // Handle ad load failed
//            }
//
//            override fun onBannerAdClicked() {
//                // Handle ad clicked
//            }
//
//            override fun onBannerAdScreenPresented() {
//                // Handle ad screen presented
//            }
//
//            override fun onBannerAdScreenDismissed() {
//                // Handle ad screen dismissed
//            }
//
//            override fun onBannerAdLeftApplication() {
//                // Handle ad left application
//            }
//        })
//
//        IronSource.loadBanner(bannerAdView, adUnitId)
//    }
//
//    fun loadInterstitialAd(adUnitId: String, listener: LevelPlayInterstitialListener) {
//        IronSource.setLevelPlayInterstitialListener(listener)
//        IronSource.loadInterstitial()
//    }
//}

package com.example.mylibrary.network

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.FrameLayout
import com.ironsource.mediationsdk.IronSource
import com.ironsource.mediationsdk.ISBannerSize
import com.ironsource.mediationsdk.IronSourceBannerLayout
import com.ironsource.mediationsdk.adunit.adapter.utility.AdInfo
import com.ironsource.mediationsdk.sdk.LevelPlayBannerListener
import com.ironsource.mediationsdk.sdk.LevelPlayInterstitialListener
import com.ironsource.mediationsdk.logger.IronSourceError
//
//class IronSourceManager(private val context: Context) {
//
//    init {
//        IronSource.setAdaptersDebug(true) // Enable debug mode for IronSource
//    }
//
//    fun loadBannerAd(adUnitId: String, adViewContainer: FrameLayout) {
//        val bannerAdView = IronSource.createBanner(context as Activity?, ISBannerSize.BANNER)
//
//        bannerAdView.levelPlayBannerListener = object : LevelPlayBannerListener {
//            override fun onAdLoaded(p0: AdInfo?) {
//                Log.d("IronSourceManager", "IronSource banner ad loaded")
//                adViewContainer.removeAllViews()
//                adViewContainer.addView(bannerAdView)
//            }
//
//            override fun onAdLoadFailed(error: IronSourceError?) {
//                Log.e("IronSourceManager", "IronSource banner ad failed to load: ${error?.errorMessage}")
//            }
//
//            override fun onAdClicked(p0: AdInfo?) {
//                Log.d("IronSourceManager", "IronSource banner ad clicked")
//            }
//
//            override fun onAdLeftApplication(p0: AdInfo?) {
//                Log.d("IronSourceManager", "IronSource banner ad left application")
//            }
//
//            override fun onAdScreenPresented(p0: AdInfo?) {
//                Log.d("IronSourceManager", "IronSource banner ad screen presented")
//            }
//
//            override fun onAdScreenDismissed(p0: AdInfo?) {
//                Log.d("IronSourceManager", "IronSource banner ad screen dismissed")
//            }
//        }
//
//        IronSource.loadBanner(bannerAdView, adUnitId)
//    }
//
//    fun loadInterstitialAd(adUnitId: String, listener: LevelPlayInterstitialListener) {
//        IronSource.setLevelPlayInterstitialListener(listener)
//        IronSource.loadInterstitial()
//    }
//}

class IronSourceManager(private val context: Context) {
    private var isBannerLoading = false

    init {
        IronSource.setAdaptersDebug(true) // Enable debug mode for IronSource
        IronSource.init(context as Activity, "1f2de9c4d")
    }

    fun loadBannerAd(adUnitId: String, adViewContainer: FrameLayout) {
        if (isBannerLoading) {
            Log.w("IronSourceManager", "Banner ad is already loading. Skipping this load request.")
            return
        }

        isBannerLoading = true
        val bannerAdView = IronSource.createBanner(context as Activity?, ISBannerSize.BANNER)

        bannerAdView.levelPlayBannerListener = object : LevelPlayBannerListener {
            override fun onAdLoaded(p0: AdInfo?) {
                Log.d("IronSourceManager", "IronSource banner ad loaded")
                adViewContainer.removeAllViews()
                adViewContainer.addView(bannerAdView)
                isBannerLoading = false
            }

            override fun onAdLoadFailed(error: IronSourceError?) {
                Log.e("IronSourceManager", "IronSource banner ad failed to load: ${error?.errorMessage}")
                isBannerLoading = false
            }

            override fun onAdClicked(p0: AdInfo?) {
                Log.d("IronSourceManager", "IronSource banner ad clicked")
            }

            override fun onAdLeftApplication(p0: AdInfo?) {
                Log.d("IronSourceManager", "IronSource banner ad left application")
            }

            override fun onAdScreenPresented(p0: AdInfo?) {
                Log.d("IronSourceManager", "IronSource banner ad screen presented")
            }

            override fun onAdScreenDismissed(p0: AdInfo?) {
                Log.d("IronSourceManager", "IronSource banner ad screen dismissed")
            }
        }

        IronSource.loadBanner(bannerAdView, adUnitId)
    }

        fun loadInterstitialAd(adUnitId: String, listener: LevelPlayInterstitialListener) {
        IronSource.setLevelPlayInterstitialListener(listener)
        IronSource.loadInterstitial()
    }
}



