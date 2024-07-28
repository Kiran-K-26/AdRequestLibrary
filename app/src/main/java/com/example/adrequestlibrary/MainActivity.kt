package com.example.adrequestlibrary

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.mylibrary.config.FirebaseConfigManager
import com.example.mylibrary.network.AdManager
import com.example.mylibrary.util.AdRequestDistributor
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adViewContainer = findViewById<FrameLayout>(R.id.adViewContainer)

        val distributor = AdRequestDistributor()
        val analyticsManager = FirebaseAnalytics.getInstance(this)
        val adManager = AdManager(this, distributor, analyticsManager)

        val configManager = FirebaseConfigManager()
        configManager.fetchAdUnitIds { adUnitIds ->
            adManager.requestAd(adViewContainer, adUnitIds)
        }
    }
}