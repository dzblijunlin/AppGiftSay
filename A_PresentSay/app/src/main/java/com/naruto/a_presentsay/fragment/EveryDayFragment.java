package com.naruto.a_presentsay.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.view.CommonHeader;
import com.google.gson.Gson;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.adapter.GiftDayAdapter;
import com.naruto.a_presentsay.adapter.GiftNewAdapter;
import com.naruto.a_presentsay.bean.GiftDayBean;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/25.
 */
// 榜单-每日推荐
public class EveryDayFragment extends BaseFragment{
    private RecyclerView rv;
//    private LRecyclerView rv;
//    private LRecyclerViewAdapter lRecyclerViewAdapter;

    @Override
    protected int setlayout() {
        return R.layout.fragment_every_day;
    }

    @Override
    void initView(View view) {
        rv = bindView(R.id.day_rv);
//        rv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
    }

    @Override
    void initData() {
        getContent();

    }

    // fragment复用
    public static EveryDayFragment newInstance(String url){
        Bundle args = new Bundle();
        args.putString("giftkey",url);
        EveryDayFragment fragment = new EveryDayFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private void getContent() {
        String url = getArguments().getString("giftkey").toString();
        NetHelper.MyRequest(url, GiftDayBean.class, new NetListener<GiftDayBean>() {
            @Override
            public void successListener(GiftDayBean response) {
                final GiftDayAdapter adapter = new GiftDayAdapter(mContext);
//                lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
                adapter.setData(response);
                rv.setAdapter(adapter);
//                rv.setAdapter(lRecyclerViewAdapter);


                final GridLayoutManager manager = new GridLayoutManager(mContext,2);
                manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (adapter.isHeadView(position) ? manager.getSpanCount() : 1);
                    }
                });
                rv.setLayoutManager(manager);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

    }


}
