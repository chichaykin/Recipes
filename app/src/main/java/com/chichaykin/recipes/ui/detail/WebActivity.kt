package com.chichaykin.recipes.ui.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View.GONE
import android.webkit.*
import android.widget.Toast
import com.chichaykin.recipes.R
import kotlinx.android.synthetic.main.activity_web.*


class WebActivity : AppCompatActivity() {

    private lateinit var titleText: CharSequence

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val url = intent.getStringExtra(URL)
        titleText = intent.getCharSequenceExtra(TITLE)
        title = getString(R.string.loading)

        webView.settings.javaScriptEnabled = true
        val activity = this
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                Log.d("Progress", "Value: " + progress)
                determinateBar.progress = progress

                if(progress == 100) {
                    title = titleText
                    determinateBar.visibility = GONE
                }
            }
        }
        webView.webViewClient = object : WebViewClient() {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onReceivedError(view:WebView, request: WebResourceRequest, error: WebResourceError) {
                Toast.makeText(activity, "Oh no! " + error.description, Toast.LENGTH_SHORT).show()
            }

            @Suppress("OverridingDeprecatedMember")
            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show()
            }
        }
        webView.loadUrl(url)
    }

    companion object {
        private const val URL = "url"
        private const val TITLE = "title"

        fun runIntent(context: Context, url: String, title: CharSequence) {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra(URL, url)
            intent.putExtra(TITLE, title)
            context.startActivity(intent)
        }
    }
}
