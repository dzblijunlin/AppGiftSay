package com.naruto.a_presentsay.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.activity.GiftInfoActivity;
import com.naruto.a_presentsay.adapter.GiftReviewAdapter;
import com.naruto.a_presentsay.bean.GiftReviewBean;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;

import java.net.URL;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GiftReviewInfoFragment extends BaseFragment {
    private ListView lv;
    private GiftReviewBean bean;
    @Override
    protected int setlayout() {
        return R.layout.fragment_gift_review_info;
    }

    @Override
    void initView(View view) {
        lv = bindView(R.id.gift_review_lv);
    }

    @Override
    void initData() {
        getReviewData();
    }

    private void getReviewData() {
        String id = GiftInfoActivity.id();

        String url = UrlTools.GIFT_REVIEW_HEAD + id + UrlTools.GIFT_REVIEW_TAIL;
        NetHelper.MyRequest(url, GiftReviewBean.class, new NetListener<GiftReviewBean>() {
            @Override
            public void successListener(GiftReviewBean response) {
//                GiftReviewAdapter adapter = new GiftReviewAdapter(mContext);
//                bean = response;
//                adapter.setBean(bean);
//                lv.setAdapter(adapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }

}
