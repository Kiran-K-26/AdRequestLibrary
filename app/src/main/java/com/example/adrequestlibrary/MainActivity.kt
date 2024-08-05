//package com.example.adrequestlibrary
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.widget.FrameLayout
//import androidx.appcompat.app.AppCompatActivity
//import com.example.mylibrary.config.FirebaseConfigManager
//import com.example.mylibrary.network.AdManager
//import com.example.mylibrary.util.AdRequestDistributor
//import com.google.firebase.analytics.FirebaseAnalytics
//
//class MainActivity : AppCompatActivity() {
//    @SuppressLint("MissingInflatedId")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val adViewContainer = findViewById<FrameLayout>(R.id.adViewContainer)
//
//        val distributor = AdRequestDistributor()
//        val analyticsManager = FirebaseAnalytics.getInstance(this)
//        val adManager = AdManager(this, distributor, analyticsManager)
//
//        val configManager = FirebaseConfigManager()
//        configManager.fetchAdUnitIds { adUnitIds ->
//            adManager.requestAd(adViewContainer, adUnitIds)
//        }
//    }
//}

//package com.example.adrequestlibrary
//
//import android.os.Bundle
//import android.widget.Button
//import android.widget.FrameLayout
//import androidx.appcompat.app.AppCompatActivity
//import com.example.mylibrary.config.FirebaseConfigManager
//import com.example.mylibrary.network.AdManager
//import com.example.mylibrary.util.AdRequestDistributor
//import com.google.firebase.analytics.FirebaseAnalytics
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var adManager: AdManager
//    private lateinit var adUnitIds: Map<String, String>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val adViewContainer = findViewById<FrameLayout>(R.id.adViewContainer)
//        val loadBannerAdButton = findViewById<Button>(R.id.loadBannerAdButton)
//        val loadInterstitialAdButton = findViewById<Button>(R.id.loadInterstitialAdButton)
//
//        val distributor = AdRequestDistributor()
//        val analyticsManager = FirebaseAnalytics.getInstance(this)
//        adManager = AdManager(this, distributor, analyticsManager)
//
//        val configManager = FirebaseConfigManager()
//        configManager.fetchAdUnitIds { adUnitIds ->
//            this.adUnitIds = adUnitIds
//        }
//
//        loadBannerAdButton.setOnClickListener {
//            adManager.requestBannerAd(adViewContainer, adUnitIds)
//        }
//
//        loadInterstitialAdButton.setOnClickListener {
//            adManager.requestInterstitialAd(adUnitIds)
//        }
//    }
//}

//package com.example.adrequestlibrary
//
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.FrameLayout
//import androidx.appcompat.app.AppCompatActivity
//import com.example.mylibrary.config.FirebaseConfigManager
//import com.example.mylibrary.network.AdManager
//import com.example.mylibrary.util.AdRequestDistributor
//import com.google.firebase.analytics.FirebaseAnalytics
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var adManager: AdManager
//    private lateinit var adUnitIds: Map<String, String>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//
//
//        val adViewContainer = findViewById<FrameLayout>(R.id.adViewContainer)
//        val loadBannerAdButton = findViewById<Button>(R.id.loadBannerAdButton)
//        val loadInterstitialAdButton = findViewById<Button>(R.id.loadInterstitialAdButton)
//
//        val distributor = AdRequestDistributor()
//        val analyticsManager = FirebaseAnalytics.getInstance(this)
//        adManager = AdManager(this, distributor, analyticsManager)
//
//        val configManager = FirebaseConfigManager()
//        configManager.fetchAdUnitIds { adUnitIds ->
//            this.adUnitIds = adUnitIds
//
//        }
//
//
//
//        loadBannerAdButton.setOnClickListener {
//            Log.e("Adunits", "onCreate: "+adUnitIds )
//            adManager.requestBannerAd(adViewContainer, adUnitIds)
//        }
//
//        loadInterstitialAdButton.setOnClickListener {
//            adManager.requestInterstitialAd(adUnitIds)
//        }
//    }
//}

//package com.example.adrequestlibrary
//
//import android.os.Bundle
//import android.util.Log
//import android.widget.Button
//import android.widget.FrameLayout
//import androidx.appcompat.app.AppCompatActivity
//import com.example.mylibrary.config.FirebaseConfigManager
//import com.example.mylibrary.network.AdManager
//import com.example.mylibrary.util.AdRequestDistributor
//import com.google.firebase.analytics.FirebaseAnalytics
//
//class MainActivity : AppCompatActivity() {
//    private lateinit var adManager: AdManager
//    private lateinit var adUnitIds: Map<String, String>
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val adViewContainer = findViewById<FrameLayout>(R.id.adViewContainer)
//        val loadBannerAdButton = findViewById<Button>(R.id.loadBannerAdButton)
//        val loadInterstitialAdButton = findViewById<Button>(R.id.loadInterstitialAdButton)
//
//        val distributor = AdRequestDistributor()
//        val analyticsManager = FirebaseAnalytics.getInstance(this)
//        adManager = AdManager(this, distributor, analyticsManager)
//
//        val configManager = FirebaseConfigManager()
//        configManager.fetchAdUnitIds { adUnitIds ->
//            this.adUnitIds = adUnitIds
//        }
//
//        loadBannerAdButton.setOnClickListener {
////            Log.e("Adunits", "onCreate: $adUnitIds")
//            adManager.requestBannerAd(adViewContainer, adUnitIds)
//        }
//
//        loadInterstitialAdButton.setOnClickListener {
//            adManager.requestInterstitialAd(adUnitIds)
//        }
//    }
//}


package com.example.adrequestlibrary

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mylibrary.config.FirebaseConfigManager
import com.example.mylibrary.network.AdManager
import com.example.mylibrary.util.AdRequestDistributor
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {
    private lateinit var adManager: AdManager
    private lateinit var adUnitIds: Map<String, String>
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adViewContainer = findViewById<FrameLayout>(R.id.adViewContainer)
        val loadBannerAdButton = findViewById<Button>(R.id.loadBannerAdButton)
        val loadInterstitialAdButton = findViewById<Button>(R.id.loadInterstitialAdButton)
        progressBar = findViewById(R.id.progressBar)

        val distributor = AdRequestDistributor()
        val analyticsManager = FirebaseAnalytics.getInstance(this)
        adManager = AdManager(this, distributor, analyticsManager) {
            // Hide progress bar when ads are loaded
            runOnUiThread {
                progressBar.visibility = View.GONE
                Toast.makeText(this,it, Toast.LENGTH_SHORT).show()
            }
        }

        val configManager = FirebaseConfigManager()
        configManager.fetchAdUnitIds { adUnitIds ->
            this.adUnitIds = adUnitIds
        }

        loadBannerAdButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE // Show progress bar
            adManager.requestBannerAds(adViewContainer, adUnitIds, 3, 2)
        }

        loadInterstitialAdButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE // Show progress bar
            adManager.requestInterstitialAds(adUnitIds, 3, 2)
        }
    }
}



