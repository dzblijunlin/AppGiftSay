package com.naruto.a_presentsay.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.adapter.CateTabAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
// 分类页
public class CategoryFragment extends BaseFragment {
    private ViewPager vp;
    private TabLayout tab;
    private ArrayList<Fragment> data;
    @Override
    protected int setlayout() {
        return R.layout.fragment_category;
    }

    @Override
    void initView(View view) {
        vp = bindView(R.id.cat_vp);
        tab = bindView(R.id.cat_tab);
    }

    @Override
    void initData() {
        getContent();
    }

    private void getContent() {
        data = new ArrayList<>();
        data.add(new StrategyFragment());
        data.add(new SingleFragment());
        CateTabAdapter adapter = new CateTabAdapter(getChildFragmentManager(),data);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setText("攻略");
        tab.getTabAt(1).setText("单品");
        tab.setTabTextColors(Color.argb(255,50,30,30),Color.argb(255,255,45,71));

    }

}
