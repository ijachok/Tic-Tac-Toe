package com.ijachok.tictactoe

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class MyWebVew : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        if (view != null && request != null) {
            view.loadUrl(request.url.toString())
        }
        return true
    }
}