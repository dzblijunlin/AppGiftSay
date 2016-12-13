package com.naruto.a_presentsay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.adapter.CateStrategyGvInfoAdapter;
import com.naruto.a_presentsay.bean.CateStrategyGvBean;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;

public class CateStrategyGvActivity extends BaseActivity {
    private ListView lv;

    @Override
    int setlayout() {
        return R.layout.activity_cate_strategy_gv;
    }

    @Override
    void initView() {
        lv = bindView(R.id.strategy_gv_lv);
    }

    @Override
    void initData() {
        getContentData();
    }

    private void getContentData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("gvId");
        String url = UrlTools.CATE_BOTTOM_HEAD + id + UrlTools.CATE_BOTTOM_TAIL;
        NetHelper.MyRequest(url, CateStrategyGvBean.class, new NetListener<CateStrategyGvBean>() {
            @Override
            public void successListener(CateStrategyGvBean response) {
                CateStrategyGvInfoAdapter adapter = new CateStrategyGvInfoAdapter(getBaseContext());
                adapter.setData(response);
                lv.setAdapter(adapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
