package com.example.mylibrary.network

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class AnalyticsManager(context: Context) {
    private val firebaseAnalytics = FirebaseAnalytics.getInstance(context)

    fun logEvent(eventName: String, params: Bundle) {
        firebaseAnalytics.logEvent(eventName, params)
    }
}