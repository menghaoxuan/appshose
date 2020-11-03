package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.myapplication.base.BaseActivity;
import com.orhanobut.logger.Logger;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;

    @BindView(R.id.webView)
    WebView mWebView;

    String url;
    String title;
    boolean isShare;

    public static void start(Context context, String url, String title, boolean isNeedShare) {
        Intent starter = new Intent(context, WebActivity.class);
        starter.putExtra("url", url);
        starter.putExtra("title", title);
        starter.putExtra("isShare", isNeedShare);
        context.startActivity(starter);
    }



    @Override
    protected void setContent() {
        super.setContent();
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        initData();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void initData() {
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        isShare = intent.getBooleanExtra("isShare", false);
        tvTitle.setText(title);
        if (isShare) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText("分享");
        } else {
            tvRight.setVisibility(View.GONE);
        }
        initWebview();
    }

    @Override
    protected void initListener() {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void initWebview() {

        WebSettings settings = mWebView.getSettings();
        settings.setSavePassword(false);
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setUseWideViewPort(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (null != mWebView) {
                    mWebView.loadUrl("javascript:isApp(2)");
                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();  // 接受所有网站的证书
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            //方法中的onProgressChanged就代表了其速度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
//                //首先我们的进度条是隐藏的
//                pbLoading.setVisibility(View.VISIBLE);
//                //把网页加载的进度传给我们的进度条
//                pbLoading.setProgress(newProgress);
//                if (newProgress == 100) {
//                    //加载完毕让进度条消失
//                    pbLoading.setVisibility(View.GONE);
//                }
                super.onProgressChanged(view, newProgress);
            }
        });

        Logger.e("--------------url---" + url);
        mWebView.loadUrl(url);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //处理webview多级后退问题
            if (mWebView.canGoBack()) {
                mWebView.goBack();// 返回上一页面
            } else {
                onBackPressed();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }




//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
//    }
}
