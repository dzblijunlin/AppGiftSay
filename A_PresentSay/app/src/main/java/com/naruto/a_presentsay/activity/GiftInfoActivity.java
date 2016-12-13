package com.naruto.a_presentsay.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.android.volley.VolleyError;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.adapter.GiftTabInfoAdapter;
import com.naruto.a_presentsay.bean.GiftOneBean;
import com.naruto.a_presentsay.fragment.GiftDetailInfoFragment;
import com.naruto.a_presentsay.fragment.GiftOneInfoFragment;
import com.naruto.a_presentsay.fragment.GiftReviewInfoFragment;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/7.
 */

public class GiftInfoActivity extends BaseActivity{
    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<Fragment> data;
    public static String mId;
    @Override
    int setlayout() {
        return R.layout.activity_gift_info;
    }

    @Override
    void initView() {
        tab = bindView(R.id.gift_info_tab);
        vp = bindView(R.id.gift_info_vp);
    }

    @Override
    void initData() {
        data = new ArrayList<>();
        getGiftData();
        getTabData();
    }

    private void getTabData() {
        // 预加载两页
        vp.setOffscreenPageLimit(2);
        data.add(new GiftOneInfoFragment());
        data.add(new GiftDetailInfoFragment());
        data.add(new GiftReviewInfoFragment());
        GiftTabInfoAdapter adapter = new GiftTabInfoAdapter(getSupportFragmentManager(),data);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("单品");
        tab.getTabAt(1).setText("详情");
        tab.getTabAt(2).setText("评论");
    }

    private void getGiftData() {
        Intent intent = getIntent();
        mId = intent.getStringExtra("dayId");

    }
    public static String id(){
        return mId;
    }

}
