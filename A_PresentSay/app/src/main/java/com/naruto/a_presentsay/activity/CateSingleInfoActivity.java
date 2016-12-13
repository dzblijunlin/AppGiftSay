package com.naruto.a_presentsay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.android.volley.VolleyError;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.adapter.CateSingleInfoAdapter;
import com.naruto.a_presentsay.bean.CateSingleInfoBean;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;

public class CateSingleInfoActivity extends BaseActivity {
    private LRecyclerView lRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;

    @Override
    int setlayout() {
        return R.layout.activity_cate_single_info;
    }

    @Override
    void initView() {
        lRecyclerView = bindView(R.id.single_info_rv);
        lRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
    }

    @Override
    void initData() {
        getContent();
    }

    private void getContent() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("singleId");
        String url = UrlTools.CATE_SINGLE_HEAD + id + UrlTools.CATE_SINGLE_TAIL;
        NetHelper.MyRequest(url, CateSingleInfoBean.class, new NetListener<CateSingleInfoBean>() {
            @Override
            public void successListener(CateSingleInfoBean response) {
                CateSingleInfoAdapter adapter= new CateSingleInfoAdapter(getBaseContext());
                lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
                adapter.setData(response);
                lRecyclerView.setAdapter(lRecyclerViewAdapter);
                // 下拉刷新
                lRecyclerView.setOnRefreshListener(new OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        lRecyclerView.refreshComplete();
                    }
                });
                // 上拉加载
//                lRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
//                    @Override
//                    public void onLoadMore() {
//
//                    }
//                });
                StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,1);
                lRecyclerView.setLayoutManager(manager);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
