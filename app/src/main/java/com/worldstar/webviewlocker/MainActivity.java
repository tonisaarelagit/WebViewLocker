package com.worldstar.webviewlocker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    // WebView url constant.
    final String WEB_URL = "http://www.msn.com";

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) findViewById(R.id.webView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadWebViewContent();
    }

    private void loadWebViewContent() {
        if (mWebView != null) {
            mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            mWebView.loadUrl(WEB_URL);
        }
    }
}
