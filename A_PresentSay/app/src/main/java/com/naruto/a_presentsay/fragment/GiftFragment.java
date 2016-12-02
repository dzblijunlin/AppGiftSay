package com.naruto.a_presentsay.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.adapter.GiftTabAdapter;
import com.naruto.a_presentsay.bean.GiftDayBean;
import com.naruto.a_presentsay.bean.GiftTabBean;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
// 榜单
public class GiftFragment extends BaseFragment {

    private TabLayout tab;
    private ViewPager vp;
    private ImageView titleIv;
    private ArrayList<Fragment> data;
    private GiftTabBean bean;





    @Override
    protected int setlayout() {
        return R.layout.fragment_gift;
    }

    @Override
    void initView(View view) {
        titleIv = bindView(R.id.gift_title_iv);
        vp = bindView(R.id.gift_vp);
        tab = bindView(R.id.gift_tab);
        data = new ArrayList<>();

    }

    @Override
    void initData() {
        getContent();

    }



    private void getContent() {
        String url = UrlTools.GIFT_TAB;
        NetHelper.MyRequest(url, GiftTabBean.class, new NetListener<GiftTabBean>() {
            @Override
            public void successListener(GiftTabBean response) {
                data.add(EveryDayFragment.newInstance(UrlTools.HEAD + "1" +UrlTools.TAIL));
                data.add(EveryDayFragment.newInstance(UrlTools.HEAD + "2" +UrlTools.TAIL));
                data.add(EveryDayFragment.newInstance(UrlTools.HEAD + "3" +UrlTools.TAIL));
                data.add(EveryDayFragment.newInstance(UrlTools.HEAD + "4" +UrlTools.TAIL));
                GiftTabAdapter adapter = new GiftTabAdapter(getChildFragmentManager(),data);
                vp.setAdapter(adapter);
                tab.setupWithViewPager(vp);
                tab.getTabAt(0).setText(response.getData().getRanks().get(0).getName());
                tab.getTabAt(1).setText(response.getData().getRanks().get(1).getName());
                tab.getTabAt(2).setText(response.getData().getRanks().get(2).getName());
                tab.getTabAt(3).setText(response.getData().getRanks().get(3).getName());
                tab.setTabTextColors(Color.argb(255,50,30,30),Color.argb(255,255,45,71));
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });



    }


}
