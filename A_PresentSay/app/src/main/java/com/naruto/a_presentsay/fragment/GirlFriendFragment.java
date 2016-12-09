package com.naruto.a_presentsay.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.activity.HomeChooseInfoActivity;
import com.naruto.a_presentsay.adapter.HomeGirlAdapter;
import com.naruto.a_presentsay.bean.HomeChooseBean;
import com.naruto.a_presentsay.bean.HomeGirlBean;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
// 主页_送女票
public class GirlFriendFragment extends BaseFragment {
    private ListView lv;
    private List<HomeGirlBean.DataBean.ItemsBean> data;
    private HomeChooseBean homeChooseBean;


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

        String url = getArguments().getString("key").toString();
        NetHelper.MyRequest(url, HomeGirlBean.class, new NetListener<HomeGirlBean>() {
            @Override
            public void successListener(final HomeGirlBean response) {
                data = response.getData().getItems();
                HomeGirlAdapter homeGirlAdapter = new HomeGirlAdapter(mContext);
                homeGirlAdapter.setData(data);
                lv.setAdapter(homeGirlAdapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(mContext,HomeChooseInfoActivity.class);
                        String lvId = response.getData().getItems().get(i).getId() + "";
                        intent.putExtra("lvKey",lvId);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

    }

}
