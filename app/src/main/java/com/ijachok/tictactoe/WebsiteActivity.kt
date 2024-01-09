package com.ijachok.tictactoe

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.ijachok.tictactoe.databinding.ActivityWebsiteBinding


class WebsiteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebsiteBinding
    private val url = "https://fex.net/"
    lateinit var upload: ValueCallback<Array<Uri>>

    @SuppressLint("WebViewApiAvailability", "SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebsiteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val settings: WebSettings = binding.webview.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true
        settings.builtInZoomControls = true
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        binding.webview.scrollBarStyle = View.SCROLLBARS_OUTSIDE_OVERLAY

        binding.webview.loadUrl(url)
        binding.webview.webViewClient = MyWebVew()
        binding.webview.webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView: WebView?,
                filePathCallback: ValueCallback<Array<Uri>>?,
                fileChooserParams: FileChooserParams?
            ): Boolean {
                super.onShowFileChooser(webView, filePathCallback, fileChooserParams)
                val intent = fileChooserParams?.createIntent()
                if (filePathCallback != null) {
                    upload = filePathCallback
                }
                if (intent != null) {
                    startActivityForResult(intent, 101)
                }
                return true
            }
        }

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            upload.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, data))

        }

    }
}