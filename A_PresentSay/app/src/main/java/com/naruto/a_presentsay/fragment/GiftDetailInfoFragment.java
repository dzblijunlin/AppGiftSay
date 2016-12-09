package com.naruto.a_presentsay.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.android.volley.VolleyError;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.activity.GiftInfoActivity;
import com.naruto.a_presentsay.bean.GiftOneBean;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class GiftDetailInfoFragment extends BaseFragment {
    private WebView webView;


    @Override
    protected int setlayout() {
        return R.layout.fragment_gift_detail_info;
    }

    @Override
    void initView(View view) {
        webView = bindView(R.id.gift_detail_web);
    }

    @Override
    void initData() {
        getWeb();
    }

    private void getWeb() {
        String id = GiftInfoActivity.id();
        Log.d("aaa", id);
        final String url = UrlTools.GIFT_ONE_HEAD + id;
        NetHelper.MyRequest(url, GiftOneBean.class, new NetListener<GiftOneBean>() {
            @Override
            public void successListener(GiftOneBean response) {
                webView.loadUrl(response.getData().getUrl());
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

}
