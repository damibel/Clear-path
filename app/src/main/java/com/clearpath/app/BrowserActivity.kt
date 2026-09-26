package com.clearpath.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class BrowserActivity : AppCompatActivity() {

    private val blockedDomains = setOf(
        "example-restricted-domain.invalid"
    )

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)

        val address = findViewById<EditText>(R.id.address)
        val webView = findViewById<WebView>(R.id.webView)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                val host = request.url.host?.lowercase(Locale.US) ?: return true
                if (isBlocked(host)) {
                    Toast.makeText(this@BrowserActivity, "This site is restricted by ClearPath.", Toast.LENGTH_SHORT).show()
                    return true
                }
                return false
            }
        }

        findViewById<Button>(R.id.go).setOnClickListener {
            var input = address.text.toString().trim()
            if (input.isBlank()) return@setOnClickListener
            if (!input.startsWith("http://") && !input.startsWith("https://")) {
                input = "https://www.google.com/search?q=" + java.net.URLEncoder.encode(input, "UTF-8")
            }
            webView.loadUrl(input)
        }
    }

    private fun isBlocked(host: String): Boolean =
        blockedDomains.any { host == it || host.endsWith(".$it") }
}
