package id.irfanirawansukirman.androidmodularwithsonarqube.dashboard.screen

import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun ShopScreen(padding: PaddingValues) {
  var visibility by remember { mutableStateOf(false) }
  var progress by remember { mutableFloatStateOf(0.0F) }
  var backEnabled by remember { mutableStateOf(false) }

  var webView: WebView? = null

  Column(horizontalAlignment = Alignment.CenterHorizontally) {
    if (visibility) {
      LinearProgressIndicator(
        color = Color(0xFFE30022),
        progress = progress,
        modifier = Modifier
          .height(4.dp)
          .fillMaxWidth()
      )
    }
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(bottom = padding.calculateBottomPadding()),
      contentAlignment = Alignment.Center
    ) {
      AndroidView(factory = {
        WebView(it).apply {
          layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
          )
          webViewClient = object : WebViewClient() {
            override fun onPageStarted(
              view: WebView, url: String,
              favicon: Bitmap?
            ) {
              visibility = true
              backEnabled = view.canGoBack()
            }

            override fun onPageFinished(
              view: WebView, url: String
            ) {
              visibility = false
            }
          }
          webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(
              view: WebView, newProgress: Int
            ) {
              progress = newProgress.toFloat()
            }
          }
          settings.javaScriptEnabled = true
          loadUrl("https://detik.com")
          webView = this
        }
      }, update = {
        webView = it
      })
    }
  }

  BackHandler(enabled = backEnabled) {
    webView?.goBack()
  }
}