package com.naruto.a_presentsay.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.adapter.HomeGirlAdapter;
import com.naruto.a_presentsay.bean.HomeGirlBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
// 主页_送女票
public class GirlFriendFragment extends BaseFragment {
    private ListView lv;
    private List<HomeGirlBean.DataBean.ItemsBean> data;


    @Override
    protected int setlayout() {
        return R.layout.fragment_girl_friend;
    }

    @Override
    void initView(View view) {
        lv = bindView(R.id.girl_lv);

    }

    @Override
    void initData() {
        data = new ArrayList<>();
        getContent();
    }
    // fragment复用
    public static GirlFriendFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("key",url);
        GirlFriendFragment fragment = new GirlFriendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void getContent() {
//        String url = UrlTools.GIRL;
        String url = getArguments().getString("key").toString();
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                HomeGirlBean homeGirlBean = gson.fromJson(response,HomeGirlBean.class);
                data = homeGirlBean.getData().getItems();
                HomeGirlAdapter homeGirlAdapter = new HomeGirlAdapter(mContext);
                homeGirlAdapter.setData(data);
                lv.setAdapter(homeGirlAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

}
