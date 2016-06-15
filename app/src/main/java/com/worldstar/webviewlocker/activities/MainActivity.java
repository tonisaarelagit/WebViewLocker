package com.worldstar.webviewlocker.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.worldstar.webviewlocker.R;
import com.worldstar.webviewlocker.services.LockService;

public class MainActivity extends AppCompatActivity {

    // WebView url constant.
    final String WEB_URL = "http://www.msn.com";

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup Action Bar.
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setIcon(R.mipmap.ic_launcher);

            String intentAction = getIntent().getAction();
            if (intentAction == null) {
                getSupportActionBar().show();
            } else {
                getSupportActionBar().hide();
            }
        }

        mWebView = (WebView) findViewById(R.id.webView);

        // Start background service.
        startService(new Intent(this, LockService.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadWebViewContent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_close:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadWebViewContent() {
        if (mWebView != null) {
            mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            mWebView.loadUrl(WEB_URL);
        }
    }
}
