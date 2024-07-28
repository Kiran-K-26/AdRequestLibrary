package com.example.mylibrary.config

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FirebaseConfigManager {
    private val remoteConfig = FirebaseRemoteConfig.getInstance()

    fun fetchAdUnitIds(onComplete: (Map<String, String>) -> Unit) {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val adUnitIdsJson = remoteConfig.getString("ad_unit_ids")
                    val adUnitIds = parseAdUnitIds(adUnitIdsJson)
                    onComplete(adUnitIds)
                }
            }
    }

    private fun parseAdUnitIds(json: String): Map<String, String> {
        val gson = Gson()
        return gson.fromJson(json, object : TypeToken<Map<String, String>>() {}.type)
    }
}