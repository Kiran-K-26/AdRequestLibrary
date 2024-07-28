package com.example.mylibrary

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import org.junit.Before
import org.junit.Test

class FirebaseRemoteConfigTest {
    private lateinit var firebaseRemoteConfig: FirebaseRemoteConfig

    @Before
    fun setUp() {
        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        firebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
    }

    @Test
    fun testFetchConfig() {
        firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val adUnitIds = firebaseRemoteConfig.getString("ad_unit_ids")
                // Verify the ad unit IDs are correctly fetched
            }
        }
    }
}