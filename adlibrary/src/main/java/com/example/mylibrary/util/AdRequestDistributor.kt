package com.example.mylibrary.util

import com.google.android.gms.common.util.CollectionUtils.listOf

class AdRequestDistributor {

    private var adNetworkIndex = 0
    private val adNetworks = listOf("AdMob", "ironSource") // Updated list

    fun getNextAdNetwork(): String {
        val selectedNetwork = adNetworks[adNetworkIndex]
        adNetworkIndex = (adNetworkIndex + 1) % adNetworks.size
        return selectedNetwork
    }
}