package com.clearpath.app

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.protectNow).setOnClickListener {
            ProtectionPrefs(this).setProtectionUntil(System.currentTimeMillis() + 30 * 60 * 1000L)
            Toast.makeText(this, "Protection strengthened for 30 minutes.", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.openBrowser).setOnClickListener {
            startActivity(Intent(this, BrowserActivity::class.java))
        }

        findViewById<Button>(R.id.accessibilitySettings).setOnClickListener {
            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        }

        findViewById<Switch>(R.id.appProtection).setOnCheckedChangeListener { _, enabled ->
            ProtectionPrefs(this).appProtectionEnabled = enabled
        }
    }
}
