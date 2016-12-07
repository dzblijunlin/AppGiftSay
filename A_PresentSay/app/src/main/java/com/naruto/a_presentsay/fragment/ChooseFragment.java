package com.naruto.a_presentsay.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.activity.HomeBannerInfoActivity;
import com.naruto.a_presentsay.activity.HomeChooseInfoActivity;
import com.naruto.a_presentsay.activity.HomeGridInfoActivity;
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
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
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
    private HomePictureBean homePictureBean;
    private HomeGrideBean homeGrideBean;
    private HomeChooseBean homeChooseBean;

    @Override
    protected int setlayout() {
        return R.layout.fragment_choose;
    }

    @Override
    void initView(View view) {
        lv = bindView(R.id.choose_lv);
        headView = LayoutInflater.from(mContext).inflate(R.layout.item_home_head, null);
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
        NetHelper.MyRequest(grideUrl, HomeGrideBean.class, new NetListener<HomeGrideBean>() {
            @Override
            public void successListener(HomeGrideBean response) {
                List<HomeGrideBean.DataBean.SecondaryBannersBean> grideData = response.getData().getSecondary_banners();
                grideAdapter.setData(grideData);
                gv.setAdapter(grideAdapter);
                homeGrideBean = response;
                gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(mContext, HomeGridInfoActivity.class);
                        String gridId = homeGrideBean.getData().getSecondary_banners().get(i).getTarget_url() + "";
                        Log.d("aaaaaa", gridId);
                        intent.putExtra("gridKey",gridId);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

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
                    homePictureBean = response;

                }
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
                banner.setImageLoader(new GlideImageLoader());
                banner.setImages(pics);
                banner.setBannerAnimation(Transformer.DepthPage);
                banner.isAutoPlay(true);
                banner.setDelayTime(2000);
                banner.setIndicatorGravity(BannerConfig.CENTER);

                // 轮播图的点击事件
                banner.setOnBannerClickListener(new OnBannerClickListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        // 跳转到轮播图详情页
                        Intent intent = new Intent(mContext, HomeBannerInfoActivity.class);
                        String bannerId = homePictureBean.getData().getBanners().get(position - 1).getTarget_id() + "";
                        intent.putExtra("bannerKey", bannerId);
                        startActivity(intent);
                    }
                });

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
                homeChooseBean = response;
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(mContext, HomeChooseInfoActivity.class);
                        String lvId = homeChooseBean.getData().getItems().get(i - 1).getId()+"";
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
