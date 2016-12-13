package com.naruto.a_presentsay.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.activity.GiftInfoActivity;
import com.naruto.a_presentsay.adapter.GiftOneBottomRvAdapter;
import com.naruto.a_presentsay.adapter.GiftOneRvAdapter;
import com.naruto.a_presentsay.bean.GiftOneBean;
import com.naruto.a_presentsay.bean.GiftOneRvBean;
import com.naruto.a_presentsay.tool.GlideImageLoader;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;

// 榜单页二级界面
public class GiftOneInfoFragment extends BaseFragment {

    private ImageView bigIv,heartIv;
    private TextView titleTv,nameTv,priceTv,likeTv,descriptionTv;
    private RecyclerView horRv,verRv;
    private GiftOneBean bean;
    private GiftOneRvBean giftOneRvBean;
    private Banner banner;
    private ArrayList<String> pics = new ArrayList<>();
    @Override
    protected int setlayout() {
        return R.layout.fragment_gift_one_info;
    }

    @Override
    void initView(View view) {
        // bigIv = bindView(R.id.gift_info_iv);
        heartIv = bindView(R.id.gift_info_heart_iv);
        titleTv = bindView(R.id.gift_info_title_tv);
        nameTv = bindView(R.id.gift_info_name_tv);
        priceTv = bindView(R.id.gift_info_price_tv);
        likeTv = bindView(R.id.gift_info_like_tv);
        descriptionTv = bindView(R.id.gift_info_descroption_tv);
        horRv = bindView(R.id.gift_info_hor_rv);
        verRv = bindView(R.id.gift_info_ver_rv);
        banner = bindView(R.id.gift_info_banner);
    }

    @Override
    void initData() {
        getOneData();
    }

    private void getOneData() {
        String id = GiftInfoActivity.id();
        Log.d("GiftOneInfoFragment", id);
        String url = UrlTools.GIFT_ONE_HEAD + id;
        String rvUrl =UrlTools.GIFT_RV_HEAD + id + UrlTools.GIFT_RV_TAIL;
        // 解析单品页上半部
        NetHelper.MyRequest(url, GiftOneBean.class, new NetListener<GiftOneBean>() {
            @Override
            public void successListener(GiftOneBean response) {
                bean = new GiftOneBean();
                bean = response;
                for (int i = 0; i < bean.getData().getImage_urls().size(); i++) {
                    pics.add(bean.getData().getImage_urls().get(i));
                }
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
                banner.setImageLoader(new GlideImageLoader());
                banner.setImages(pics);
                banner.setBannerAnimation(Transformer.DepthPage);
                banner.isAutoPlay(true);
                banner.setDelayTime(20000000);
                banner.setIndicatorGravity(BannerConfig.CENTER);
                banner.start();
                titleTv.setText(bean.getData().getShort_description());
                nameTv.setText(bean.getData().getName());
                priceTv.setText(bean.getData().getPrice());
                likeTv.setText(bean.getData().getLikes_count() + "");
                descriptionTv.setText(bean.getData().getDescription());
                // Picasso.with(mContext).load(bean.getData().getImage_urls().get(0)).into(bigIv);

            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
        // 解析单品页横向RecyclerView
        NetHelper.MyRequest(rvUrl, GiftOneRvBean.class, new NetListener<GiftOneRvBean>() {
            @Override
            public void successListener(GiftOneRvBean response) {
                GiftOneRvAdapter adapter = new GiftOneRvAdapter(mContext);
                adapter.setBean(response);
                horRv.setAdapter(adapter);
                LinearLayoutManager manager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);
                horRv.setLayoutManager(manager);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
        // 解析单品页纵向RecyclerView
        NetHelper.MyRequest(rvUrl, GiftOneRvBean.class, new NetListener<GiftOneRvBean>() {
            @Override
            public void successListener(GiftOneRvBean response) {
                GiftOneBottomRvAdapter bottomRvAdapter = new GiftOneBottomRvAdapter(mContext);
                bottomRvAdapter.setBean(response);
                verRv.setAdapter(bottomRvAdapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,2);
                verRv.setLayoutManager(gridLayoutManager);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

}
