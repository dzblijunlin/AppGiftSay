package com.naruto.a_presentsay.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.activity.CateStrategyCollumnAllInfoActivity;
import com.naruto.a_presentsay.activity.CateStrategyGvActivity;
import com.naruto.a_presentsay.activity.CateStrategyNewGvInfoActivity;
import com.naruto.a_presentsay.activity.CateStrategyRvActivity;
import com.naruto.a_presentsay.activity.CateStrategyTypeGvInfoActivity;
import com.naruto.a_presentsay.adapter.CateColumnAdapter;
import com.naruto.a_presentsay.adapter.CateFootAdapter;
import com.naruto.a_presentsay.adapter.CateFootNewAdapter;
import com.naruto.a_presentsay.adapter.CateFootStyleAdapter;
import com.naruto.a_presentsay.bean.CateColumnBean;
import com.naruto.a_presentsay.bean.CateFootBean;
import com.naruto.a_presentsay.tool.MyColumnClick;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
// 分类-攻略
public class StrategyFragment extends BaseFragment implements MyColumnClick, View.OnClickListener {
    private RecyclerView rv;
    private List<CateColumnBean.DataBean.ColumnsBean> data;
    private GridView typeGv,styleGv,newGv;
    private List<CateFootBean.DataBean.ChannelGroupsBean> grideTypeData,grideStyleData,grideNewData;
    private CateFootAdapter typeAdapter;
    private CateFootStyleAdapter styleAdapter;
    private CateFootNewAdapter newAdapter;
    private ImageView goIv;
    private CateColumnAdapter columnAdapter;
    private String id;
    private TextView collumnAllTv,typeAllTv,newAllTv;
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
        goIv = bindView(R.id.strategy_one_back_iv);
        grideTypeData = new ArrayList<>();
        grideStyleData = new ArrayList<>();
        grideNewData = new ArrayList<>();
        columnAdapter = new CateColumnAdapter(mContext);
        typeAllTv = bindView(R.id.strategy_two_all_tv);
        newAllTv = bindView(R.id.startegy_four_all_tv);
        collumnAllTv = bindView(R.id.strategy_one_all_tv);

    }

    @Override
    void initData() {
        getOneContent();
        getTypeContent();
        getGoColumn();
        // 给栏目的Recyclerview设置点击事件,接口回调
        columnAdapter.setMyColumnClick(this);

        getTypeGv();
        getStyleGv();
        getNewGv();
        getAll();
    }
    // 查看全部的点击事件
    private void getAll() {
        collumnAllTv.setOnClickListener(this);
        typeAllTv.setOnClickListener(this);
        newAllTv.setOnClickListener(this);

    }

    // 第三个Gv点击进去详情页
    private void getNewGv() {
        newGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String newId = grideNewData.get(2).getChannels().get(i).getId() + "";
                Intent intent = new Intent(mContext, CateStrategyGvActivity.class);
                intent.putExtra("gvId",newId);
                startActivity(intent);
            }
        });
    }
    // 第二个Gv点击进去详情页
    private void getStyleGv() {
        styleGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String styleId = grideStyleData.get(1).getChannels().get(i).getId() + "";
                Intent intent = new Intent(mContext, CateStrategyGvActivity.class);
                intent.putExtra("gvId",styleId);
                startActivity(intent);
            }
        });
    }
    // 第一个Gv点击进去详情页
    private void getTypeGv() {
        typeGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String gvId = grideTypeData.get(0).getChannels().get(i).getId()+ "";
                Intent intent = new Intent(mContext, CateStrategyGvActivity.class);
                intent.putExtra("gvId",gvId);
                startActivity(intent);
            }
        });
    }



    private void getGoColumn() {
        goIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
                rv.setAdapter(getColumnAdapter());
                GridLayoutManager manager = new GridLayoutManager(mContext,3, LinearLayoutManager.HORIZONTAL,false);
                rv.setLayoutManager(manager);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

    }

    private CateColumnAdapter getColumnAdapter(){
        CateColumnAdapter adapter = new CateColumnAdapter(mContext);
        adapter.setData(data);
        adapter.setMyColumnClick(this);
        return adapter;
    }

    // 发送id,上半部Recyclerview点击跳转
    @Override
    public void myColumnListener(int pos) {
        id = data.get(pos).getId() + "";
        Intent intent = new Intent(mContext, CateStrategyRvActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    // 查看全部的点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.strategy_one_all_tv:
                Intent intent = new Intent(mContext,CateStrategyCollumnAllInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.strategy_two_all_tv:
                Intent typeIntent = new Intent(mContext, CateStrategyTypeGvInfoActivity.class);
                startActivity(typeIntent);
                break;
            case R.id.startegy_four_all_tv:
                Intent newIntent = new Intent(mContext, CateStrategyNewGvInfoActivity.class);
                startActivity(newIntent);
                break;
        }

    }





}
