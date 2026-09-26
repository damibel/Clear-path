package com.clearpath.app

import android.content.Context

class ProtectionPrefs(context: Context) {
    private val prefs = context.getSharedPreferences("clearpath", Context.MODE_PRIVATE)

    var appProtectionEnabled: Boolean
        get() = prefs.getBoolean("app_protection", true)
        set(value) { prefs.edit().putBoolean("app_protection", value).apply() }

    fun setProtectionUntil(time: Long) {
        prefs.edit().putLong("protection_until", time).apply()
    }

    fun isProtectedNow(): Boolean =
        System.currentTimeMillis() < prefs.getLong("protection_until", 0L)
}
