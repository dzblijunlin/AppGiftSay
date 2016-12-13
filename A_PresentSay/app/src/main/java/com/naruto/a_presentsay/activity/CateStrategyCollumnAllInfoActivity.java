package com.naruto.a_presentsay.activity;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.adapter.CateCollumnAllInfoAdapter;
import com.naruto.a_presentsay.bean.CateStrategyCollumnInfoBean;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;

import java.util.List;

public class CateStrategyCollumnAllInfoActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private CateCollumnAllInfoAdapter adapter;
    private TextView titleTv;


    @Override
    int setlayout() {
        return R.layout.activity_cate_strategy_collumn_all_info;
    }

    @Override
    void initView() {
        recyclerView = bindView(R.id.startegy_search_all_rv);
        titleTv = bindView(R.id.class_gride_title_tv);
    }

    @Override
    void initData() {
        String url = UrlTools.CATE_STRATEGY_INFO;
        NetHelper.MyRequest(url, CateStrategyCollumnInfoBean.class, new NetListener<CateStrategyCollumnInfoBean>() {
            @Override
            public void successListener(CateStrategyCollumnInfoBean response) {
                List<CateStrategyCollumnInfoBean.DataBean.ColumnsBean> data = response.getData().getColumns();
                adapter = new CateCollumnAllInfoAdapter(getBaseContext());
                adapter.setData(data);
                recyclerView.setAdapter(adapter);
                LinearLayoutManager manager = new LinearLayoutManager(CateStrategyCollumnAllInfoActivity.this,LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(manager);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
