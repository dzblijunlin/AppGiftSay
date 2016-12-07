package com.naruto.a_presentsay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.volley.NetHelper;

// 首页精选listview二级界面
public class HomeChooseInfoActivity extends BaseActivity {
    private WebView webView;
    private WebViewClient webViewClient;
    private WebSettings webSettings;

    @Override
    int setlayout() {
        return R.layout.activity_home_choose_info;
    }

    @Override
    void initView() {
        webView = bindView(R.id.home_lv_wv);
    }

    @Override
    void initData() {
        getChooseData();
    }

    private void getChooseData() {
        Intent intent = getIntent();
        String lvId = intent.getStringExtra("lvKey");
        String url = UrlTools.HOME_LISTVIEW_HEAD + lvId + UrlTools.HOME_LISTVIEW_TAIL;
        webView.loadUrl(url);
    }
}
