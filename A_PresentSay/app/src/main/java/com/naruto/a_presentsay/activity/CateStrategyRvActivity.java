package com.naruto.a_presentsay.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.adapter.CateColumnInfoLvAdapter;
import com.naruto.a_presentsay.bean.CateColumnInfoBean;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;
import com.squareup.picasso.Picasso;

public class CateStrategyRvActivity extends BaseActivity {
    private ImageView headIv;
    private TextView headTitleTv,headLikeTv,introTv;
    private Button headButton;
    private ListView lv;
    private String id;
    private String url;

    @Override
    int setlayout() {
        return R.layout.activity_cate_strategy_rv;

    }

    @Override
    void initView() {
        headIv = bindView(R.id.iv_category_strategy_head_head_next);
        headTitleTv = bindView(R.id.tv_category_strategy_head_head_next_title);
        headLikeTv = bindView(R.id.tv_category_strategy_head_head_next_likes_count);
        introTv = bindView(R.id.tv_category_strategy_head_head_next_description);
        headButton = bindView(R.id.btn_category_strategy_head_head_next_attention);
        lv = bindView(R.id.lv_category_strategy_next);
    }

    @Override
    void initData() {
        getTopData();
        // getLvData();
    }
    // 获取下面的Listview
    private void getLvData() {

    }

    private void getTopData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        url = UrlTools.CATE_COLUMN_HEAD + id + UrlTools.CATE_COLUMN_TAIL;
        NetHelper.MyRequest(url, CateColumnInfoBean.class, new NetListener<CateColumnInfoBean>() {
            @Override
            public void successListener(CateColumnInfoBean response) {
                Picasso.with(getBaseContext()).load(response.getData().getCover_image_url()).into(headIv);
                headTitleTv.setText(response.getData().getTitle());
                headLikeTv.setText(response.getData().getLikes_count() + "人喜欢");
                introTv.setText(response.getData().getDescription());

                // listview
                CateColumnInfoLvAdapter adapter = new CateColumnInfoLvAdapter(getBaseContext());
                adapter.setData(response);
                lv.setAdapter(adapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
