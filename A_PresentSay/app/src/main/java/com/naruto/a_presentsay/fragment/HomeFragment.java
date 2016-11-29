package com.naruto.a_presentsay.fragment;


import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.adapter.HomeTabAdapter;
import com.naruto.a_presentsay.bean.HomeTabBean;
import com.naruto.a_presentsay.tool.UrlTools;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
// 主页
public class HomeFragment extends BaseFragment {


    private TabLayout tab;
    private ViewPager vp;
    private TextView seekTv;
    private ImageView clockIv;
    private ArrayList<Fragment> data;
    private HomeTabBean bean;

    @Override
    protected int setlayout() {
        return R.layout.fragment_home;
    }

    @Override
    void initView(View view) {
        tab = bindView(R.id.home_tab);
        vp = bindView(R.id.home_vp);
        seekTv = bindView(R.id.home_seek_tv);
        clockIv = bindView(R.id.home_clock_iv);
        data = new ArrayList<>();
    }

    @Override
    void initData() {

        getContent();



    }

    private void getContent() {
        String url = UrlTools.TITLE;
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                bean = gson.fromJson(response,HomeTabBean.class);
                Log.d("xxx", bean.getData().getCandidates().get(0).getName());
                // fragment复用设置
                data.add(new ChooseFragment());
                data.add( GirlFriendFragment.newInstance(UrlTools.HOME_HEAD+"10"+UrlTools.HOME_TAIL));
                data.add( GirlFriendFragment.newInstance(UrlTools.HOME_HEAD+"129"+UrlTools.HOME_TAIL));
                data.add( GirlFriendFragment.newInstance(UrlTools.HOME_HEAD+"125"+UrlTools.HOME_TAIL));
                data.add( GirlFriendFragment.newInstance(UrlTools.HOME_HEAD+"26"+UrlTools.HOME_TAIL));
                data.add( GirlFriendFragment.newInstance(UrlTools.HOME_HEAD+"6"+UrlTools.HOME_TAIL));
                data.add( GirlFriendFragment.newInstance(UrlTools.HOME_HEAD+"17"+UrlTools.HOME_TAIL));
                data.add( GirlFriendFragment.newInstance(UrlTools.HOME_HEAD+"127"+UrlTools.HOME_TAIL));
                data.add( GirlFriendFragment.newInstance(UrlTools.HOME_HEAD+"14"+UrlTools.HOME_TAIL));
                data.add( GirlFriendFragment.newInstance(UrlTools.HOME_HEAD+"126"+UrlTools.HOME_TAIL));
                data.add( GirlFriendFragment.newInstance(UrlTools.HOME_HEAD+"28"+UrlTools.HOME_TAIL));
                data.add( GirlFriendFragment.newInstance(UrlTools.HOME_HEAD+"121"+UrlTools.HOME_TAIL));
                data.add( GirlFriendFragment.newInstance(UrlTools.HOME_HEAD+"11"+UrlTools.HOME_TAIL));
                data.add( GirlFriendFragment.newInstance(UrlTools.HOME_HEAD+"124"+UrlTools.HOME_TAIL));


                HomeTabAdapter adapter = new HomeTabAdapter(getChildFragmentManager(),data);
                vp.setAdapter(adapter);
                // 设置tablayout
                tab.setupWithViewPager(vp);
                tab.getTabAt(0).setText(bean.getData().getChannels().get(0).getName());
                tab.getTabAt(1).setText(bean.getData().getChannels().get(1).getName());
                tab.getTabAt(2).setText(bean.getData().getChannels().get(2).getName());
                tab.getTabAt(3).setText(bean.getData().getChannels().get(3).getName());
                tab.getTabAt(4).setText(bean.getData().getChannels().get(4).getName());
                tab.getTabAt(5).setText(bean.getData().getChannels().get(5).getName());
                tab.getTabAt(6).setText(bean.getData().getChannels().get(6).getName());
                tab.getTabAt(7).setText(bean.getData().getChannels().get(7).getName());
                tab.getTabAt(8).setText(bean.getData().getChannels().get(8).getName());
                tab.getTabAt(9).setText(bean.getData().getChannels().get(9).getName());
                tab.getTabAt(10).setText(bean.getData().getChannels().get(10).getName());
                tab.getTabAt(11).setText(bean.getData().getChannels().get(11).getName());
                tab.getTabAt(12).setText(bean.getData().getChannels().get(12).getName());
                tab.getTabAt(13).setText(bean.getData().getChannels().get(13).getName());


                // 自定义tab字体颜色
                tab.setTabTextColors(Color.argb(255,50,30,30),Color.argb(255,255,45,71));


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }


}
