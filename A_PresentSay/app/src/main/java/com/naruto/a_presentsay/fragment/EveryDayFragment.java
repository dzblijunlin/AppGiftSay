package com.naruto.a_presentsay.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.activity.GiftInfoActivity;
import com.naruto.a_presentsay.adapter.GiftNewAdapter;
import com.naruto.a_presentsay.bean.GiftDayBean;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/11/25.
 */
// 榜单-每日推荐
public class EveryDayFragment extends BaseFragment{

    private LRecyclerView rv;
    private LRecyclerViewAdapter lRecyclerViewAdapter;

    @Override
    protected int setlayout() {
        return R.layout.fragment_every_day;
    }

    @Override
    void initView(View view) {
        rv = bindView(R.id.day_rv);
        rv.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
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
            public void successListener(final GiftDayBean response) {
                final GiftNewAdapter adapter = new GiftNewAdapter(mContext);
                lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
                adapter.setData(response);
                rv.setAdapter(lRecyclerViewAdapter);

                /**
                 * 头布局
                 */
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_gift_top,null);
                ImageView headView = (ImageView) view.findViewById(R.id.gift_top_iv);
                Picasso.with(mContext).load(response.getData().getCover_image()).into(headView);
                lRecyclerViewAdapter.addHeaderView(view);

                // 下拉刷新
                rv.setOnRefreshListener(new OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        rv.refreshComplete();

                    }
                });
                final StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,1);
                rv.setLayoutManager(manager);
                // 点击Recyclerview跳转到二级页面
                lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int i) {
                        Intent intent = new Intent(mContext, GiftInfoActivity.class);
                        String id = response.getData().getItems().get(i).getId() + "";
                        intent.putExtra("dayId",id);
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
