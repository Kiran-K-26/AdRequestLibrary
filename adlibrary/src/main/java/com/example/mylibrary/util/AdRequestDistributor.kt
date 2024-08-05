//package com.example.mylibrary.util
//
//import com.google.android.gms.common.util.CollectionUtils.listOf
//
//class AdRequestDistributor {
//
//    private var adNetworkIndex = 0
//    private val adNetworks = listOf("AdMob_Banner", "ironSource_Banner") // Updated list
//    private val adNetworksInterstitial = listOf("AdMob_Interstitial", "ironSource_Interstitial")
//
//    fun getNextAdNetwork(): String {
//        val selectedNetwork = adNetworks[adNetworkIndex]
//        adNetworkIndex = (adNetworkIndex + 1) % adNetworks.size
//        return selectedNetwork
//    }
//
//    fun getNextAdInterstitialNetwork(): String {
//        val selectedNetwork = adNetworksInterstitial[adNetworkIndex]
//        adNetworkIndex = (adNetworkIndex + 1) % adNetworks.size
//        return selectedNetwork
//    }
//}


package com.example.mylibrary.util

import android.util.Log
import com.google.android.gms.common.util.CollectionUtils.listOf
import java.util.ArrayList

class AdRequestDistributor {

    private var adNetworkIndex = 0
    private val adNetworks = listOf("AdMob", "AdMob")
    private val adNetworksInterstitial = listOf("AdMob_Interstitial", "AdMob_Interstitial")

    fun getAdNetworksForBatch(batchSize: Int): List<String> {
        val batch = ArrayList<String>()
        for (i in 0.rangeTo(batchSize - 1)) {
            batch.add(adNetworks[adNetworkIndex % adNetworks.size])
            adNetworkIndex++
        }
        return batch
    }

    fun getInterstitialAdNetworksForBatch(batchSize: Int): List<String> {
        val batch = ArrayList<String>()
        for (i in 0.rangeTo(batchSize - 1)) {
            batch.add(adNetworksInterstitial[adNetworkIndex % adNetworksInterstitial.size])
            adNetworkIndex++
        }
        return batch
    }

}
