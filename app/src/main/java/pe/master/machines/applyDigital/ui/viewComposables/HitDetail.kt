package pe.master.machines.applyDigital.ui.viewComposables

import android.graphics.Bitmap
import android.net.http.SslError
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import pe.master.machines.applyDigital.ui.customComposables.LoadingData
import java.lang.ref.WeakReference

@Composable
fun HitDetail(webUrl: String) {
    var isViewLoading by rememberSaveable { mutableStateOf(value = true) }
    var message by rememberSaveable { mutableStateOf(value = "") }
    var handlerReference: WeakReference<SslErrorHandler>? by remember { mutableStateOf(null) }
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    val webView = WebView(context)
                    // Configuraciones del WebView
                    webView.settings.javaScriptCanOpenWindowsAutomatically = true
                    webView.settings.javaScriptEnabled = true
                    webView.settings.builtInZoomControls = false
                    webView.settings.domStorageEnabled = false
                    webView.settings.setSupportZoom(false)
                    webView.webViewClient = object : WebViewClient() {
                        override fun onReceivedSslError(
                            view: WebView, handler: SslErrorHandler, error: SslError
                        ) {
                            message = when (error.primaryError) {
                                SslError.SSL_UNTRUSTED -> "The certificate authority is not trusted."
                                SslError.SSL_EXPIRED -> "The certificate has expired."
                                SslError.SSL_IDMISMATCH -> "The certificate Hostname mismatch."
                                SslError.SSL_NOTYETVALID -> "The certificate is not yet valid."
                                else -> "SSL Certificate error."
                            }
                            message += " Do you want to continue anyway?"
                            handlerReference = WeakReference(handler)
                        }

                        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                            super.onPageStarted(view, url, favicon)
                        }

                        override fun onPageFinished(view: WebView, url: String) {
                            super.onPageFinished(view, url)
                        }

                        override fun onLoadResource(view: WebView, url: String) {
                            super.onLoadResource(view, url)
                        }

                        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                            return super.shouldOverrideUrlLoading(view, url)
                        }
                    }
                    webView.setInitialScale(150)
                    webView.settings.textZoom = 150
                    webView.clearCache(true)
                    webView.webChromeClient = object : WebChromeClient() {
                        override fun onProgressChanged(view: WebView, progress: Int) {
                            isViewLoading = progress != 100
                        }
                    }
                    webView.loadUrl(webUrl)
                    webView
                }
            )
        }
    }
    if (isViewLoading) LoadingData(message = "Loading...")
}