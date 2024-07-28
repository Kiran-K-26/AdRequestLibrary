package com.example.mylibrary.network

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.ads.AdView
import com.applovin.mediation.ads.MaxAdView
import com.example.mylibrary.util.AdRequestDistributor
import com.google.firebase.analytics.FirebaseAnalytics
import com.ironsource.mediationsdk.adunit.adapter.utility.AdInfo
import com.ironsource.mediationsdk.logger.IronSourceError
import com.ironsource.mediationsdk.sdk.LevelPlayInterstitialListener

class AdManager(
    private val context: Context,
    private val distributor: AdRequestDistributor,
    private val analyticsManager: FirebaseAnalytics
) {

    fun requestAd(adView: View, adUnitIds: Map<String, String>) {
        val adNetwork = distributor.getNextAdNetwork()
        val adUnitId = adUnitIds[adNetwork] ?: return

        try {
            when (adNetwork) {
                "AdMob" -> {
                    if (adView is AdView) {
                        AdMobManager(context).loadAd(adUnitId, adView)
                        val bundle = Bundle()
                        bundle.putString("ad_unit_id", adUnitId)
                        analyticsManager.logEvent("admob_ad_requested", bundle)
                    }
                }
                "ironSource" -> {
                    IronSourceManager(context).loadAd(adUnitId, object : LevelPlayInterstitialListener {

                        override fun onAdReady(p0: AdInfo?) {
                            // Handle ad ready
                            val bundle = Bundle()
                            bundle.putString("ad_unit_id", adUnitId)
                            analyticsManager.logEvent("ironSource_ad_ready", bundle)
                        }

                        override fun onAdLoadFailed(error: IronSourceError) {
                            Log.e("AdManager", "IronSource ad load failed: ${error.errorMessage}")
                        }

                        override fun onAdOpened(p0: AdInfo?) {
                            // Handle ad opened
                            val bundle = Bundle()
                            bundle.putString("ad_unit_id", adUnitId)
                            analyticsManager.logEvent("ironSource_ad_opened", bundle)
                        }

                        override fun onAdShowSucceeded(p0: AdInfo?) {
                            // Handle ad show succeeded
                            val bundle = Bundle()
                            bundle.putString("ad_unit_id", adUnitId)
                            analyticsManager.logEvent("ironSource_ad_show_succeeded", bundle)
                        }

                        override fun onAdShowFailed(p0: IronSourceError?, p1: AdInfo?) {
                            Log.e("AdManager", "IronSource ad show failed: ${p0?.errorMessage}")
                        }

                        override fun onAdClicked(p0: AdInfo?) {
                            // Handle ad clicked
                            val bundle = Bundle()
                            bundle.putString("ad_unit_id", adUnitId)
                            analyticsManager.logEvent("ironSource_ad_clicked", bundle)
                        }

                        override fun onAdClosed(p0: AdInfo?) {
                            // Handle ad closed
                            val bundle = Bundle()
                            bundle.putString("ad_unit_id", adUnitId)
                            analyticsManager.logEvent("ironSource_ad_closed", bundle)
                        }
                    })
                }
            }
        }catch (e: Exception) {
            Log.e("AdManager", "Failed to load ad from $adNetwork: ${e.message}")
        }
    }
}