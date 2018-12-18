package com.example.joe.youtomp3;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;
    WebView mWebView = null;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btn = (Button) findViewById(R.id.button);
////        btn.setId(0);
////        btn.setOnClickListener(this);

        mWebView = (WebView)findViewById(R.id.WebView1);
        mWebView.setWebViewClient(mWebViewClient);
        mWebView.loadUrl("file:///android_asset/yutbApiTest.html");
//        mWebView.loadUrl("http://stackoverflow.com/questions/7966085/raw-folder-url-path");

        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setSaveFormData(true);
        mWebView.getSettings().setAllowContentAccess(true);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        mWebView.getSettings().setJavaScriptEnabled(true);//**
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                downloadByBrowser(url);
            }
        });

        mWebView.getSettings().setSupportZoom(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setClickable(true);
    }

    private void downloadByBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

    @Override
    public void onClick(View v) {

    }

//    public void onClick(View v) {
//
//        switch (v.getId()) {
//            case 0:
//                //flider(asset 's special use kind: "file:///android_asset/filename")
//                mWebView.loadUrl("file:///android_asset/internal_set.html");
//                break;
//        }
//    }
}
