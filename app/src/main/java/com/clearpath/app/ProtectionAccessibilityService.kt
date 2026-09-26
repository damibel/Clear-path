package com.clearpath.app

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent

class ProtectionAccessibilityService : AccessibilityService() {

    private val blockedPackages = setOf(
        "com.example.restrictedapp"
    )

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (!ProtectionPrefs(this).appProtectionEnabled) return
        val pkg = event?.packageName?.toString() ?: return

        if (pkg in blockedPackages) {
            val intent = Intent(this, BlockedActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
            startActivity(intent)
        }
    }

    override fun onInterrupt() = Unit
}
