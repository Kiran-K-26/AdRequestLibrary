package com.example.adrequestlibrary

import android.app.Application
import com.ironsource.mediationsdk.IronSource
import com.google.firebase.FirebaseApp;
import com.google.android.gms.ads.MobileAds
import com.google.firebase.analytics.FirebaseAnalytics

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase
        FirebaseApp.initializeApp(this);
        IronSource.init(this, "1f2de9c4d")
        MobileAds.initialize(this) {}

        // Initialize Firebase Analytics
        val firebaseAnalytics = FirebaseAnalytics.getInstance(this)
    }
}