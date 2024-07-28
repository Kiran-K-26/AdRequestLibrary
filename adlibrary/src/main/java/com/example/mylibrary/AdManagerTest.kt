//package com.example.mylibrary
//
//import android.content.Context
//import android.os.Bundle
//import android.view.View
//import com.example.mylibrary.network.AdManager
//import com.example.mylibrary.util.AdRequestDistributor
//import com.google.android.gms.common.util.CollectionUtils.mapOf
//import com.google.firebase.analytics.FirebaseAnalytics
//import org.junit.Before
//import org.junit.Test
//import org.mockito.Mockito.*
//
//class AdManagerTest {
//    private lateinit var context: Context
//    private lateinit var adManager: AdManager
//    private lateinit var distributor: AdRequestDistributor
//    private lateinit var analyticsManager: FirebaseAnalytics
//
//    @Before
//    fun setUp() {
//        context = mock(Context::class)
//        distributor = mock(AdRequestDistributor::class.java)
//        analyticsManager = mock(FirebaseAnalytics::class.java)
//        adManager = AdManager(context, distributor, analyticsManager)
//    }
//
//    @Test
//    fun testRequestAd() {
//        val adView = mock(View::class.java)
//        val adUnitIds = mapOf("AdMob" to "admob_unit_id", "ironSource" to "ironsource_unit_id")
//
//        `when`(distributor.getNextAdNetwork()).thenReturn("AdMob")
//
//        adManager.requestAd(adView, adUnitIds)
//
//        verify(distributor).getNextAdNetwork()
//        verify(analyticsManager).logEvent(eq("admob_ad_requested"), any(Bundle::class.java))
//    }
//}