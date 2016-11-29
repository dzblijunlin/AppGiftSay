package com.naruto.a_presentsay.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.adapter.GiftDayAdapter;
import com.naruto.a_presentsay.bean.GiftDayBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/25.
 */
// 榜单-每日推荐
public class EveryDayFragment extends BaseFragment{
    private RecyclerView rv;
    @Override
    protected int setlayout() {
        return R.layout.fragment_every_day;
    }

    @Override
    void initView(View view) {
        rv = bindView(R.id.day_rv);
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
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                GiftDayBean data = gson.fromJson(response,GiftDayBean.class);
                final GiftDayAdapter adapter = new GiftDayAdapter(mContext);
                adapter.setData(data);
                rv.setAdapter(adapter);
                final GridLayoutManager manager = new GridLayoutManager(mContext,2);
                manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (adapter.isHeadView(position)?manager.getSpanCount():1);
                    }
                });
                rv.setLayoutManager(manager);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }


}
