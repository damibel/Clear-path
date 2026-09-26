package com.clearpath.app

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class BlockedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blocked)
        findViewById<Button>(R.id.backButton).setOnClickListener { finish() }
    }
}
