package com.naruto.a_presentsay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.adapter.HomeGridInfoAdapter;
import com.naruto.a_presentsay.bean.HomeGridInfoBean;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;

public class HomeGridInfoActivity extends BaseActivity {
    private ListView lv;
    private HomeGridInfoBean bean;

    @Override
    int setlayout() {
        return R.layout.activity_home_grid_info;
    }

    @Override
    void initView() {
        lv = bindView(R.id.home_grid_lv);
    }

    @Override
    void initData() {
        getGridData();
    }

    private void getGridData() {
        // 接收gridId
        Intent intent = getIntent();
        String gridId = intent.getStringExtra("gridKey");
        gridId = gridId.substring(gridId.length()-3, gridId.length());


        // 拼接网址
       // String url = UrlTools.HOME_GRID_HEAD + gridId + UrlTools.HOME_GRID_TAIL;
        String url = "http://api.liwushuo.com/v2/collections/" + gridId + "/posts?limit=20&offset=0";

        NetHelper.MyRequest(url, HomeGridInfoBean.class, new NetListener<HomeGridInfoBean>() {
            @Override
            public void successListener(HomeGridInfoBean response) {
                HomeGridInfoAdapter adapter = new HomeGridInfoAdapter(HomeGridInfoActivity.this);
                adapter.setData(response);
                lv.setAdapter(adapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
