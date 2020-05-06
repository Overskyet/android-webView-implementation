package com.overskyet.webviewimplementation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R.id.web_view_main_activity);
        mWebView.loadUrl("https://en.m.wikipedia.org/wiki/Wiki");
        setWebViewSettings();
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                if ("en.m.wikipedia.org".equals(webResourceRequest.getUrl().getHost()) ||
                        "ru.m.wikipedia.org".equals(webResourceRequest.getUrl().getHost()) ||
                        "ru.wikipedia.org".equals(webResourceRequest.getUrl().getHost()) ||
                        "en.wikipedia.org".equals(webResourceRequest.getUrl().getHost())) return false;

                Intent intent = new Intent(Intent.ACTION_VIEW, webResourceRequest.getUrl());
                startActivity(intent);
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setWebViewSettings() {
        WebSettings myWebSettings = mWebView.getSettings();
        myWebSettings.setBuiltInZoomControls(true);
        myWebSettings.setDisplayZoomControls(false);
    }
}
