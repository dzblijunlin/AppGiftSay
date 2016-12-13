package com.naruto.a_presentsay.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.adapter.CateStrategyTypeAllAdapter;
import com.naruto.a_presentsay.bean.CateStrategyTypeAllBean;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;

import java.util.List;

/**
 * 攻略页面->品类->查看全部
 */
public class CateStrategyNewGvInfoActivity extends BaseActivity {
    private RecyclerView rv;
    private CateStrategyTypeAllAdapter adapter;
    private TextView titleTv;

    @Override
    int setlayout() {
        return R.layout.activity_cate_strategy_new_gv_info;
    }

    @Override
    void initView() {
        rv = bindView(R.id.strategy_class_grid_rv);
        titleTv = bindView(R.id.class_gride_all_title_tv);
        adapter = new CateStrategyTypeAllAdapter(this);
    }

    @Override
    void initData() {
        String url = UrlTools.STRATEGY_FOOT;
        NetHelper.MyRequest(url, CateStrategyTypeAllBean.class, new NetListener<CateStrategyTypeAllBean>() {
            @Override
            public void successListener(CateStrategyTypeAllBean response) {
                List<CateStrategyTypeAllBean.DataBean.ChannelGroupsBean.ChannelsBean> data = response.getData().getChannel_groups().get(2).getChannels();
                adapter.setData(data);
                rv.setAdapter(adapter);
                GridLayoutManager manager = new GridLayoutManager(CateStrategyNewGvInfoActivity.this,2);
                rv.setLayoutManager(manager);
                titleTv.setText(response.getData().getChannel_groups().get(2).getName());
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
