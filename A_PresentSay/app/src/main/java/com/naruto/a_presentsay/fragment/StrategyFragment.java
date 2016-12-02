package com.naruto.a_presentsay.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.adapter.CateColumnAdapter;
import com.naruto.a_presentsay.adapter.CateFootAdapter;
import com.naruto.a_presentsay.adapter.CateFootNewAdapter;
import com.naruto.a_presentsay.adapter.CateFootStyleAdapter;
import com.naruto.a_presentsay.bean.CateColumnBean;
import com.naruto.a_presentsay.bean.CateFootBean;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
// 分类-攻略
public class StrategyFragment extends BaseFragment {
    private RecyclerView rv;
    private List<CateColumnBean.DataBean.ColumnsBean> data;
    private GridView typeGv,styleGv,newGv;
    private List<CateFootBean.DataBean.ChannelGroupsBean> grideTypeData,grideStyleData,grideNewData;
    private CateFootAdapter typeAdapter;
    private CateFootStyleAdapter styleAdapter;
    private CateFootNewAdapter newAdapter;

    @Override
    protected int setlayout() {
        return R.layout.fragment_strategy;
    }

    @Override
    void initView(View view) {
        rv = bindView(R.id.strategy_rv);
        data = new ArrayList<>();
        typeGv = bindView(R.id.strategy_two_type_gv);
        styleGv = bindView(R.id.startegy_three_style_gv);
        newGv = bindView(R.id.startegy_four_object_gv);
        grideTypeData = new ArrayList<>();
        grideStyleData = new ArrayList<>();
        grideNewData = new ArrayList<>();

    }

    @Override
    void initData() {
        getOneContent();
        getTypeContent();
    }

    private void getTypeContent() {
        String typeUrl = UrlTools.STRATEGY_FOOT;
        NetHelper.MyRequest(typeUrl, CateFootBean.class, new NetListener<CateFootBean>() {
            @Override
            public void successListener(CateFootBean response) {
                grideTypeData = response.getData().getChannel_groups();
                grideStyleData = response.getData().getChannel_groups();
                grideNewData = response.getData().getChannel_groups();
                Log.d("xxxx", "grideTypeData:" + grideTypeData);
                typeAdapter = new CateFootAdapter(mContext);
                styleAdapter = new CateFootStyleAdapter(mContext);
                newAdapter = new CateFootNewAdapter(mContext);
                typeAdapter.setData(grideTypeData);
                styleAdapter.setData(grideStyleData);
                newAdapter.setData(grideNewData);
                typeGv.setAdapter(typeAdapter);
                styleGv.setAdapter(styleAdapter);
                newGv.setAdapter(newAdapter);

            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

    private void getOneContent() {
        String columnUrl = UrlTools.COLUMN;
        NetHelper.MyRequest(columnUrl, CateColumnBean.class, new NetListener<CateColumnBean>() {
            @Override
            public void successListener(CateColumnBean response) {
                data = response.getData().getColumns();
                Log.d("xxx", "data:" + data);
                CateColumnAdapter adapter = new CateColumnAdapter(mContext);
                adapter.setData(data);
                rv.setAdapter(adapter);
                GridLayoutManager manager = new GridLayoutManager(mContext,3, LinearLayoutManager.HORIZONTAL,false);
                rv.setLayoutManager(manager);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

    }


}
