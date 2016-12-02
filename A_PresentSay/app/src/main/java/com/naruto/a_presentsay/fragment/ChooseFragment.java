package com.naruto.a_presentsay.fragment;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.adapter.HomeChooseAdapter;
import com.naruto.a_presentsay.adapter.HomeGrideAdapter;
import com.naruto.a_presentsay.bean.HomeChooseBean;
import com.naruto.a_presentsay.bean.HomeGrideBean;
import com.naruto.a_presentsay.bean.HomePictureBean;
import com.naruto.a_presentsay.tool.GlideImageLoader;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
// 主页-精选
public class ChooseFragment extends BaseFragment {
    private ListView lv;
    private List<HomeChooseBean.DataBean.ItemsBean> data;
    private View headView;
    private ArrayList<String> pics = new ArrayList<>();
    private Banner banner;
    private GridView gv;
    private HomeGrideAdapter grideAdapter;

    @Override
    protected int setlayout() {
        return R.layout.fragment_choose;
    }

    @Override
    void initView(View view) {
        lv = bindView(R.id.choose_lv);
        headView = LayoutInflater.from(mContext).inflate(R.layout.item_home_head,null);
        gv = (GridView) headView.findViewById(R.id.home_head_gv);
        banner = (Banner) headView.findViewById(R.id.home_head_banner);
    }

    @Override
    void initData() {
        data = new ArrayList<>();
        grideAdapter = new HomeGrideAdapter(mContext);
        getContent();
        getHead();
        getGride();
    }
    // 获取主页的六宫格
    private void getGride() {
        String grideUrl = UrlTools.GRIDE;
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(grideUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                HomeGrideBean grideBean = gson.fromJson(response,HomeGrideBean.class);
                List<HomeGrideBean.DataBean.SecondaryBannersBean> grideData = grideBean.getData().getSecondary_banners();
                grideAdapter.setData(grideData);
                gv.setAdapter(grideAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
// 获取头布局的轮播图
    private void getHead() {
        lv.addHeaderView(headView);
        String imgUrl = UrlTools.PICTURE;
        NetHelper.MyRequest(imgUrl, HomePictureBean.class, new NetListener<HomePictureBean>() {
            @Override
            public void successListener(HomePictureBean response) {
                for (int i = 0; i < response.getData().getBanners().size(); i++) {
                    pics.add(response.getData().getBanners().get(i).getImage_url());
                }
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
                banner.setImageLoader(new GlideImageLoader());
                banner.setImages(pics);
                banner.setBannerAnimation(Transformer.DepthPage);
                banner.isAutoPlay(true);
                banner.setDelayTime(2000);
                banner.setIndicatorGravity(BannerConfig.CENTER);
                banner.start();
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

    }
// 获取并解析精选内容
    private void getContent() {
        String url = UrlTools.CHOOSE;
        NetHelper.MyRequest(url, HomeChooseBean.class, new NetListener<HomeChooseBean>() {
            @Override
            public void successListener(HomeChooseBean response) {
                data = response.getData().getItems();
                HomeChooseAdapter homeChooseAdapter = new HomeChooseAdapter(mContext);
                homeChooseAdapter.setData(data);
                lv.setAdapter(homeChooseAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

    }
}
