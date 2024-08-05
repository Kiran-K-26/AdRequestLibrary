//package com.example.mylibrary.network
//
//import android.content.Context
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import com.google.android.gms.ads.AdView
//import com.applovin.mediation.ads.MaxAdView
//import com.example.mylibrary.util.AdRequestDistributor
//import com.google.firebase.analytics.FirebaseAnalytics
//import com.ironsource.mediationsdk.adunit.adapter.utility.AdInfo
//import com.ironsource.mediationsdk.logger.IronSourceError
//import com.ironsource.mediationsdk.sdk.LevelPlayInterstitialListener
//
//class AdManager(
//    private val context: Context,
//    private val distributor: AdRequestDistributor,
//    private val analyticsManager: FirebaseAnalytics
//) {
//
//    fun requestAd(adView: View, adUnitIds: Map<String, String>) {
//        val adNetwork = distributor.getNextAdNetwork()
//        val adUnitId = adUnitIds[adNetwork] ?: return
//
//        try {
//            when (adNetwork) {
//                "AdMob" -> {
//                    if (adView is AdView) {
//                        AdMobManager(context).loadAd(adUnitId, adView)
//                        val bundle = Bundle()
//                        bundle.putString("ad_unit_id", adUnitId)
//                        analyticsManager.logEvent("admob_ad_requested", bundle)
//                    }
//                }
//                "ironSource" -> {
//                    IronSourceManager(context).loadAd(adUnitId, object : LevelPlayInterstitialListener {
//
//                        override fun onAdReady(p0: AdInfo?) {
//                            // Handle ad ready
//                            val bundle = Bundle()
//                            bundle.putString("ad_unit_id", adUnitId)
//                            analyticsManager.logEvent("ironSource_ad_ready", bundle)
//                        }
//
//                        override fun onAdLoadFailed(error: IronSourceError) {
//                            Log.e("AdManager", "IronSource ad load failed: ${error.errorMessage}")
//                        }
//
//                        override fun onAdOpened(p0: AdInfo?) {
//                            // Handle ad opened
//                            val bundle = Bundle()
//                            bundle.putString("ad_unit_id", adUnitId)
//                            analyticsManager.logEvent("ironSource_ad_opened", bundle)
//                        }
//
//                        override fun onAdShowSucceeded(p0: AdInfo?) {
//                            // Handle ad show succeeded
//                            val bundle = Bundle()
//                            bundle.putString("ad_unit_id", adUnitId)
//                            analyticsManager.logEvent("ironSource_ad_show_succeeded", bundle)
//                        }
//
//                        override fun onAdShowFailed(p0: IronSourceError?, p1: AdInfo?) {
//                            Log.e("AdManager", "IronSource ad show failed: ${p0?.errorMessage}")
//                        }
//
//                        override fun onAdClicked(p0: AdInfo?) {
//                            // Handle ad clicked
//                            val bundle = Bundle()
//                            bundle.putString("ad_unit_id", adUnitId)
//                            analyticsManager.logEvent("ironSource_ad_clicked", bundle)
//                        }
//
//                        override fun onAdClosed(p0: AdInfo?) {
//                            // Handle ad closed
//                            val bundle = Bundle()
//                            bundle.putString("ad_unit_id", adUnitId)
//                            analyticsManager.logEvent("ironSource_ad_closed", bundle)
//                        }
//                    })
//                }
//            }
//        }catch (e: Exception) {
//            Log.e("AdManager", "Failed to load ad from $adNetwork: ${e.message}")
//        }
//    }
//}

//package com.example.mylibrary.network
//
//import android.app.Activity
//import android.content.Context
//import android.os.Bundle
//import android.util.Log
//import android.widget.FrameLayout
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.AdView
//import com.google.android.gms.ads.interstitial.InterstitialAd
//import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
//import com.google.android.gms.ads.LoadAdError
//import com.example.mylibrary.util.AdRequestDistributor
//import com.google.firebase.analytics.FirebaseAnalytics
//import com.ironsource.mediationsdk.adunit.adapter.utility.AdInfo
//import com.ironsource.mediationsdk.logger.IronSourceError
//import com.ironsource.mediationsdk.sdk.LevelPlayInterstitialListener
//
//class AdManager(
//    private val context: Context,
//    private val distributor: AdRequestDistributor,
//    private val analyticsManager: FirebaseAnalytics
//) {
//    private var interstitialAd: InterstitialAd? = null
//
//    fun requestBannerAd(adViewContainer: FrameLayout, adUnitIds: Map<String, String>) {
//        val adNetwork = distributor.getNextAdNetwork()
//        val adUnitId = adUnitIds[adNetwork] ?: return
//
//        try {
//            when (adNetwork) {
//                "AdMob" -> {
//                    val adView = AdView(context)
//                    adView.adUnitId = adUnitId
//                    adView.adSize = com.google.android.gms.ads.AdSize.BANNER
//                    val adRequest = AdRequest.Builder().build()
//                    adView.loadAd(adRequest)
//                    adViewContainer.addView(adView)
//
//                    val bundle = Bundle()
//                    bundle.putString("ad_unit_id", adUnitId)
//                    analyticsManager.logEvent("admob_banner_ad_requested", bundle)
//                }
//                "ironSource" -> {
//                    IronSourceManager(context).loadBannerAd(adUnitId, adViewContainer)
//
//                    val bundle = Bundle()
//                    bundle.putString("ad_unit_id", adUnitId)
//                    analyticsManager.logEvent("ironsource_banner_ad_requested", bundle)
//                }
//            }
//        } catch (e: Exception) {
//            Log.e("AdManager", "Failed to load banner ad from $adNetwork: ${e.message}")
//        }
//    }
//
//    fun requestInterstitialAd(adUnitIds: Map<String, String>) {
//        val adNetwork = distributor.getNextAdNetwork()
//        val adUnitId = adUnitIds[adNetwork] ?: return
//
//        try {
//            when (adNetwork) {
//                "AdMob" -> {
//                    InterstitialAd.load(context, adUnitId, AdRequest.Builder().build(), object : InterstitialAdLoadCallback() {
//                        override fun onAdLoaded(ad: InterstitialAd) {
//                            interstitialAd = ad
//                            val bundle = Bundle()
//                            bundle.putString("ad_unit_id", adUnitId)
//                            analyticsManager.logEvent("admob_interstitial_ad_loaded", bundle)
//                        }
//
//                        override fun onAdFailedToLoad(error: LoadAdError) {
//                            Log.e("AdManager", "AdMob interstitial ad failed to load: ${error.message}")
//                        }
//                    })
//                }
//                "ironSource" -> {
//                    IronSourceManager(context).loadInterstitialAd(adUnitId, object : LevelPlayInterstitialListener {
//                        override fun onAdReady(p0: AdInfo?) {
//                            // Handle ad ready
//                            val bundle = Bundle()
//                            bundle.putString("ad_unit_id", adUnitId)
//                            analyticsManager.logEvent("ironsource_interstitial_ad_ready", bundle)
//                        }
//
//                        override fun onAdLoadFailed(error: IronSourceError) {
//                            Log.e("AdManager", "IronSource ad load failed: ${error.errorMessage}")
//                        }
//
//                        override fun onAdOpened(p0: AdInfo?) {
//                            // Handle ad opened
//                            val bundle = Bundle()
//                            bundle.putString("ad_unit_id", adUnitId)
//                            analyticsManager.logEvent("ironsource_ad_opened", bundle)
//                        }
//
//                        override fun onAdShowSucceeded(p0: AdInfo?) {
//                            // Handle ad show succeeded
//                            val bundle = Bundle()
//                            bundle.putString("ad_unit_id", adUnitId)
//                            analyticsManager.logEvent("ironsource_ad_show_succeeded", bundle)
//                        }
//
//                        override fun onAdShowFailed(p0: IronSourceError?, p1: AdInfo?) {
//                            Log.e("AdManager", "IronSource ad show failed: ${p0?.errorMessage}")
//                        }
//
//                        override fun onAdClicked(p0: AdInfo?) {
//                            // Handle ad clicked
//                            val bundle = Bundle()
//                            bundle.putString("ad_unit_id", adUnitId)
//                            analyticsManager.logEvent("ironsource_ad_clicked", bundle)
//                        }
//
//                        override fun onAdClosed(p0: AdInfo?) {
//                            // Handle ad closed
//                            val bundle = Bundle()
//                            bundle.putString("ad_unit_id", adUnitId)
//                            analyticsManager.logEvent("ironsource_ad_closed", bundle)
//                        }
//                    })
//                }
//            }
//        } catch (e: Exception) {
//            Log.e("AdManager", "Failed to load interstitial ad from $adNetwork: ${e.message}")
//        }
//    }
//
//    fun showInterstitialAd() {
//        interstitialAd?.show(context as Activity)
//    }
//}

//package com.example.mylibrary.network
//
//import android.app.Activity
//import android.content.Context
//import android.os.Bundle
//import android.util.Log
//import android.widget.FrameLayout
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.AdView
//import com.google.android.gms.ads.interstitial.InterstitialAd
//import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
//import com.google.android.gms.ads.LoadAdError
//import com.example.mylibrary.util.AdRequestDistributor
//import com.google.firebase.analytics.FirebaseAnalytics
//import com.ironsource.mediationsdk.IronSource
//import com.ironsource.mediationsdk.adunit.adapter.utility.AdInfo
//import com.ironsource.mediationsdk.logger.IronSourceError
//import com.ironsource.mediationsdk.sdk.LevelPlayInterstitialListener
//
//class AdManager(
//    private val context: Context,
//    private val distributor: AdRequestDistributor,
//    private val analyticsManager: FirebaseAnalytics
//) {
//    private var interstitialAd: InterstitialAd? = null
//
//    fun requestBannerAd(adViewContainer: FrameLayout, adUnitIds: Map<String, String>) {
//        val adNetwork = distributor.getNextAdNetwork()
//        val adUnitId = adUnitIds[adNetwork] ?: return
//
//        try {
//            when (adNetwork) {
//                "AdMob_Banner" -> {
//                    Log.e("AdMob_Banner", "adUnitId"+adUnitId)
//                    val adView = AdView(context)
//                    adView.adUnitId = adUnitId
//                    adView.adSize = com.google.android.gms.ads.AdSize.BANNER
//                    val adRequest = AdRequest.Builder().build()
//                    adView.loadAd(adRequest)
//                    adViewContainer.addView(adView)
//
//                    val bundle = Bundle()
//                    bundle.putString("ad_unit_id", adUnitId)
//                    analyticsManager.logEvent("admob_banner_ad_requested", bundle)
//                }
//                "ironSource_Banner" -> {
//                    Log.e("ironSource_Banner", "ironSource_Banner"+adUnitId)
//                    IronSourceManager(context).loadBannerAd(adUnitId, adViewContainer)
//
//                    val bundle = Bundle()
//                    bundle.putString("ad_unit_id", adUnitId)
//                    analyticsManager.logEvent("ironsource_banner_ad_requested", bundle)
//                }
//            }
//        } catch (e: Exception) {
//            Log.e("AdManager", "Failed to load banner ad from $adNetwork: ${e.message}")
//        }
//    }
//
//    fun requestInterstitialAd(adUnitIds: Map<String, String>) {
//        val adNetwork = distributor.getNextAdInterstitialNetwork()
//        val adUnitId = adUnitIds[adNetwork] ?: return
//
//        try {
//            when (adNetwork) {
//                "AdMob_Interstitial" -> {
//                    Log.e("AdMob_Interstitial", "requestInterstitialAd: "+adUnitId )
//                    InterstitialAd.load(context, adUnitId, AdRequest.Builder().build(), object : InterstitialAdLoadCallback() {
//                        override fun onAdLoaded(ad: InterstitialAd) {
//                            Log.e("AdMob_Interstitial", "ad loading"+ad)
//                            interstitialAd = ad
//                            val bundle = Bundle()
//                            bundle.putString("ad_unit_id", adUnitId)
//                            analyticsManager.logEvent("admob_interstitial_ad_loaded", bundle)
//
//                            // Show the ad
//                            ad.show(context as Activity)
//                        }
//
//                        override fun onAdFailedToLoad(error: LoadAdError) {
//                            interstitialAd = null
//                            Log.e("AdManager", "AdMob interstitial ad failed to load: ${error.message}")
//                        }
//                    })
//                }
//                "ironSource_Interstitial" -> {
//                    IronSourceManager(context).loadInterstitialAd(adUnitId, object : LevelPlayInterstitialListener {
//                        override fun onAdReady(p0: AdInfo?) {
//                            // Handle ad ready
//                            val bundle = Bundle()
//                            bundle.putString("ad_unit_id", adUnitId)
//                            analyticsManager.logEvent("ironsource_interstitial_ad_ready", bundle)
//
//                            // Show the ad
//                            IronSource.showInterstitial(adUnitId)
//                        }
//
//                        override fun onAdLoadFailed(error: IronSourceError) {
//                            Log.e("AdManager", "IronSource ad load failed: ${error.errorMessage}")
//                        }
//
//                        override fun onAdOpened(p0: AdInfo?) {
//                            // Handle ad opened
//                            val bundle = Bundle()
//                            bundle.putString("ad_unit_id", adUnitId)
//                            analyticsManager.logEvent("ironsource_ad_opened", bundle)
//                        }
//
//                        override fun onAdShowSucceeded(p0: AdInfo?) {
//                            // Handle ad show succeeded
//                            val bundle = Bundle()
//                            bundle.putString("ad_unit_id", adUnitId)
//                            analyticsManager.logEvent("ironsource_ad_show_succeeded", bundle)
//                        }
//
//                        override fun onAdShowFailed(p0: IronSourceError?, p1: AdInfo?) {
//                            Log.e("AdManager", "IronSource ad show failed: ${p0?.errorMessage}")
//                        }
//
//                        override fun onAdClicked(p0: AdInfo?) {
//                            // Handle ad clicked
//                            val bundle = Bundle()
//                            bundle.putString("ad_unit_id", adUnitId)
//                            analyticsManager.logEvent("ironsource_ad_clicked", bundle)
//                        }
//
//                        override fun onAdClosed(p0: AdInfo?) {
//                            // Handle ad closed
//                            val bundle = Bundle()
//                            bundle.putString("ad_unit_id", adUnitId)
//                            analyticsManager.logEvent("ironsource_ad_closed", bundle)
//                        }
//                    })
//                }
//            }
//        } catch (e: Exception) {
//            Log.e("AdManager", "Failed to load interstitial ad from $adNetwork: ${e.message}")
//        }
//    }
//
//    fun showInterstitialAd() {
//        interstitialAd?.show(context as Activity)
//    }
//}


package com.example.mylibrary.network

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.FrameLayout
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.example.mylibrary.util.AdRequestDistributor
import com.google.android.gms.ads.*
import com.google.firebase.analytics.FirebaseAnalytics
import com.ironsource.mediationsdk.IronSource
import com.ironsource.mediationsdk.adunit.adapter.utility.AdInfo
import com.ironsource.mediationsdk.logger.IronSourceError
import com.ironsource.mediationsdk.sdk.LevelPlayInterstitialListener

class AdManager(
    private val context: Context,
    private val distributor: AdRequestDistributor,
    private val analyticsManager: FirebaseAnalytics,
    private val onAdLoadComplete: (String) -> Unit
) {
    private var interstitialAd: InterstitialAd? = null
    private var bannerAdLoaded = false
    private var interstitialAdLoaded = false

    fun requestBannerAds(adViewContainer: FrameLayout, adUnitIds: Map<String, String>, admobCount: Int, ironSourceCount: Int) {
        val adNetworks = distributor.getAdNetworksForBatch(admobCount + ironSourceCount)

        for (index in 0.rangeTo(adNetworks.size - 1)) {
            val adNetwork = adNetworks[index]
            val adUnitId = adUnitIds[adNetwork] ?: continue

            try {
                when (adNetwork) {
                    "AdMob" -> {
                        if (index < admobCount) {
                            val adView = AdView(context)
                            adView.adUnitId = adUnitId
                            adView.adSize = AdSize.BANNER
                            val adRequest = AdRequest.Builder().build()

                            Log.d("AdManager", "AdMob ad request: $adRequest")
                            adView.loadAd(adRequest)

                            // Add AdListener for debugging
                            adView.adListener = object : AdListener() {
                                override fun onAdFailedToLoad(adError: LoadAdError) {
                                    Log.e("AdManager", "AdMob banner ad failed to load: ${adError.message}")
                                    bannerAdLoaded = true // Consider it as loaded to avoid blocking
                                    checkAllAdsLoaded("AdMob banner ad failed to load: ${adError.message}")
                                }

                                override fun onAdLoaded() {
                                    Log.d("AdManager", "AdMob banner ad loaded successfully")
                                    adViewContainer.removeAllViews()
                                    adViewContainer.addView(adView)
                                    bannerAdLoaded = true
                                    checkAllAdsLoaded("AdMob banner ad loaded successfully")
                                }
                            }

                            val bundle = Bundle()
                            bundle.putString("ad_unit_id", adUnitId)
                            analyticsManager.logEvent("admob_banner_ad_requested", bundle)
                        }
                    }
                    "ironSource_Banner" -> {
                        if (index >= admobCount) {
//                            Handler(Looper.getMainLooper()).postDelayed({
                                IronSourceManager(context).loadBannerAd(adUnitId, adViewContainer)

                                val bundle = Bundle()
                                bundle.putString("ad_unit_id", adUnitId)
                                analyticsManager.logEvent("ironsource_banner_ad_requested", bundle)
                            bannerAdLoaded = true // Assume it's loaded for now
                            checkAllAdsLoaded("ironSource")
//                            }, 3000)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("AdManager", "Failed to load banner ad from $adNetwork: ${e.message}")
            }
        }

        Log.d("AdManager", "AdNetworks: $adNetworks" + adUnitIds)
    }

    fun requestInterstitialAds(adUnitIds: Map<String, String>, admobCount: Int, ironSourceCount: Int) {
        val adNetworks = distributor.getInterstitialAdNetworksForBatch(admobCount + ironSourceCount)

        for (index in 0.rangeTo(adNetworks.size - 1)) {
            val adNetwork = adNetworks[index]
            val adUnitId = adUnitIds[adNetwork] ?: continue

            try {
                when (adNetwork) {
                    "AdMob_Interstitial" -> {
                        if (index < admobCount) {
                            InterstitialAd.load(context, adUnitId, AdRequest.Builder().build(), object : InterstitialAdLoadCallback() {
                                override fun onAdLoaded(ad: InterstitialAd) {
                                    interstitialAd = ad
                                    val bundle = Bundle()
                                    bundle.putString("ad_unit_id", adUnitId)
                                    analyticsManager.logEvent("admob_interstitial_ad_loaded", bundle)
                                    interstitialAdLoaded = true
                                    checkAllAdsLoaded("AdMob_Interstitial Success")
                                    // Show the ad
                                    ad.show(context as Activity)
                                }

                                override fun onAdFailedToLoad(error: LoadAdError) {
                                    interstitialAd = null
                                    interstitialAdLoaded = true // Consider it as loaded to avoid blocking
                                    checkAllAdsLoaded("AdMob interstitial ad failed to load: ${error.message}")
                                    Log.e("AdManager", "AdMob interstitial ad failed to load: ${error.message}")
                                }
                            })
                        }
                    }
                    "ironSource_Interstitial" -> {
                        if (index >= admobCount) {
                            IronSourceManager(context).loadInterstitialAd(adUnitId, object : LevelPlayInterstitialListener {
                                override fun onAdReady(p0: AdInfo?) {
                                    val bundle = Bundle()
                                    bundle.putString("ad_unit_id", adUnitId)
                                    analyticsManager.logEvent("ironsource_interstitial_ad_ready", bundle)
                                    interstitialAdLoaded = true
                                    checkAllAdsLoaded("ironSource_Interstitial Success")
                                    // Show the ad
                                    IronSource.showInterstitial(adUnitId)
                                }

                                override fun onAdLoadFailed(error: IronSourceError) {
                                    interstitialAdLoaded = true // Consider it as loaded to avoid blocking
                                    checkAllAdsLoaded("IronSource ad load failed: ${error.errorMessage}")
                                    Log.e("AdManager", "IronSource ad load failed: ${error.errorMessage}")
                                }

                                override fun onAdOpened(p0: AdInfo?) {
                                    val bundle = Bundle()
                                    bundle.putString("ad_unit_id", adUnitId)
                                    analyticsManager.logEvent("ironsource_ad_opened", bundle)
                                }

                                override fun onAdShowSucceeded(p0: AdInfo?) {
                                    val bundle = Bundle()
                                    bundle.putString("ad_unit_id", adUnitId)
                                    analyticsManager.logEvent("ironsource_ad_show_succeeded", bundle)
                                }

                                override fun onAdShowFailed(p0: IronSourceError?, p1: AdInfo?) {
                                    Log.e("AdManager", "IronSource ad show failed: ${p0?.errorMessage}")
                                }

                                override fun onAdClicked(p0: AdInfo?) {
                                    val bundle = Bundle()
                                    bundle.putString("ad_unit_id", adUnitId)
                                    analyticsManager.logEvent("ironsource_ad_clicked", bundle)
                                }

                                override fun onAdClosed(p0: AdInfo?) {
                                    val bundle = Bundle()
                                    bundle.putString("ad_unit_id", adUnitId)
                                    analyticsManager.logEvent("ironsource_ad_closed", bundle)
                                }
                            })
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("AdManager", "Failed to load interstitial ad from $adNetwork: ${e.message}")
            }
        }
    }

    private fun checkAllAdsLoaded(message: String) {
        if (bannerAdLoaded || interstitialAdLoaded) {
            onAdLoadComplete(message)
        }
    }

    fun showInterstitialAd() {
        interstitialAd?.show(context as Activity)
    }
}



