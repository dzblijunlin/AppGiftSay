package com.naruto.a_presentsay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.adapter.HomeBannerAdapter;
import com.naruto.a_presentsay.bean.HomeBannerBean;
import com.naruto.a_presentsay.bean.HomePictureBean;
import com.naruto.a_presentsay.bean.HomePictureDetailBean;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;

import java.util.List;

public class HomeBannerInfoActivity extends BaseActivity {


    private HomeBannerBean bean;
    private ListView lv;




    @Override
    int setlayout() {
        return R.layout.activity_home_banner_info;
    }

    @Override
    void initView() {
        lv = bindView(R.id.home_banner_lv);
    }

    @Override
    void initData() {
        getBannerData();
    }

    private void getBannerData() {

        Intent intent = getIntent();
        String bannerId = intent.getStringExtra("bannerKey");
        // 拼接网址
        String url = UrlTools.BANNER_HEAD + bannerId + UrlTools.BANNER_TAIL;
        NetHelper.MyRequest(url, HomeBannerBean.class, new NetListener<HomeBannerBean>() {
            @Override
            public void successListener(HomeBannerBean response) {
                bean = response;
                HomeBannerAdapter adapter = new HomeBannerAdapter(HomeBannerInfoActivity.this);
                adapter.setData(bean);
                lv.setAdapter(adapter);

            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
