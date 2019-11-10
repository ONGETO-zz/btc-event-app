package com.example.event;

import androidx.appcompat.app.AppCompatActivity;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.os.Bundle;


public class StWebView extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st_web_view);

        webview = (WebView) findViewById(R.id.webview);

        // Enable Javascript to run in WebView
        webview.getSettings().setJavaScriptEnabled(true);

        // Allow Zoom in/out controls
        webview.getSettings().setBuiltInZoomControls(true);

        // Zoom out the best fit your screen
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);

        // Load URL
        webview.loadUrl("http://www.twitter.com");

        // Show the progress bar
        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                setProgress(progress * 100);
            }
        });

        // Call private class InsideWebViewClient
        webview.setWebViewClient(new InsideWebViewClient());

    }

    private class InsideWebViewClient extends WebViewClient {
        @Override
        // Force links to be opened inside WebView and not in Default Browser
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;

        }

    }

}


