package com.naruto.a_presentsay.fragment;


import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.adapter.HomePopAdapter;
import com.naruto.a_presentsay.adapter.HomeTabAdapter;
import com.naruto.a_presentsay.bean.HomeTabBean;
import com.naruto.a_presentsay.tool.MyPopClick;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;

import java.util.ArrayList;
import java.util.List;

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
    private ImageView popDownIv;
    private RecyclerView popRv;
    private RelativeLayout titleRl;
    private HomePopAdapter popAdapter;
    private List<HomeTabBean.DataBean.ChannelsBean> list;

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
        popDownIv = bindView(R.id.home_down_pop_iv);
        titleRl = bindView(R.id.home_title_rl);
    }

    @Override
    void initData() {

        getContent();

    }

    private void getContent() {
        String url = UrlTools.TITLE;
        getPopWindow();
        NetHelper.MyRequest(url, HomeTabBean.class, new NetListener<HomeTabBean>() {

            @Override
            public void successListener(HomeTabBean response) {
                // fragment复用设置
                data.add(new ChooseFragment());
                data.add(GirlFriendFragment.newInstance(UrlTools.HOME_HEAD + "10" + UrlTools.HOME_TAIL));
                data.add(GirlFriendFragment.newInstance(UrlTools.HOME_HEAD + "129" + UrlTools.HOME_TAIL));
                data.add(GirlFriendFragment.newInstance(UrlTools.HOME_HEAD + "125" + UrlTools.HOME_TAIL));
                data.add(GirlFriendFragment.newInstance(UrlTools.HOME_HEAD + "26" + UrlTools.HOME_TAIL));
                data.add(GirlFriendFragment.newInstance(UrlTools.HOME_HEAD + "6" + UrlTools.HOME_TAIL));
                data.add(GirlFriendFragment.newInstance(UrlTools.HOME_HEAD + "17" + UrlTools.HOME_TAIL));
                data.add(GirlFriendFragment.newInstance(UrlTools.HOME_HEAD + "127" + UrlTools.HOME_TAIL));
                data.add(GirlFriendFragment.newInstance(UrlTools.HOME_HEAD + "14" + UrlTools.HOME_TAIL));
                data.add(GirlFriendFragment.newInstance(UrlTools.HOME_HEAD + "126" + UrlTools.HOME_TAIL));
                data.add(GirlFriendFragment.newInstance(UrlTools.HOME_HEAD + "28" + UrlTools.HOME_TAIL));
                data.add(GirlFriendFragment.newInstance(UrlTools.HOME_HEAD + "121" + UrlTools.HOME_TAIL));
                data.add(GirlFriendFragment.newInstance(UrlTools.HOME_HEAD + "11" + UrlTools.HOME_TAIL));
                data.add(GirlFriendFragment.newInstance(UrlTools.HOME_HEAD + "124" + UrlTools.HOME_TAIL));


                HomeTabAdapter adapter = new HomeTabAdapter(getChildFragmentManager(), data);
                vp.setAdapter(adapter);
                // 设置tablayout
                tab.setupWithViewPager(vp);
                tab.getTabAt(0).setText(response.getData().getChannels().get(0).getName());
                tab.getTabAt(1).setText(response.getData().getChannels().get(1).getName());
                tab.getTabAt(2).setText(response.getData().getChannels().get(2).getName());
                tab.getTabAt(3).setText(response.getData().getChannels().get(3).getName());
                tab.getTabAt(4).setText(response.getData().getChannels().get(4).getName());
                tab.getTabAt(5).setText(response.getData().getChannels().get(5).getName());
                tab.getTabAt(6).setText(response.getData().getChannels().get(6).getName());
                tab.getTabAt(7).setText(response.getData().getChannels().get(7).getName());
                tab.getTabAt(8).setText(response.getData().getChannels().get(8).getName());
                tab.getTabAt(9).setText(response.getData().getChannels().get(9).getName());
                tab.getTabAt(10).setText(response.getData().getChannels().get(10).getName());
                tab.getTabAt(11).setText(response.getData().getChannels().get(11).getName());
                tab.getTabAt(12).setText(response.getData().getChannels().get(12).getName());
                tab.getTabAt(13).setText(response.getData().getChannels().get(13).getName());


                // 自定义tab字体颜色
                tab.setTabTextColors(Color.argb(255, 50, 30, 30), Color.argb(255, 255, 45, 71));

            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });

    }

    private void getPopWindow() {
        popDownIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupWindow popupWindow = new PopupWindow(mContext);
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                View popView = LayoutInflater.from(mContext).inflate(R.layout.home_pop_window, null);
                popRv = (RecyclerView) popView.findViewById(R.id.pop_window_rv);
                ImageView popUpIv = (ImageView) popView.findViewById(R.id.pop_window_index_up);
                popupWindow.setContentView(popView);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                // 显示popupWindow指定显示的位置
                // 下面的代码代表了:在首页标题下面显示出来
                popupWindow.showAsDropDown(titleRl);
                // 点击消失
                popUpIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                    }
                });
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        popDownIv.setImageResource(R.mipmap.arrow_index_down);

                    }
                });
                popDownIv.setImageResource(R.mipmap.arrow_index_up);
                popAdapter = new HomePopAdapter(mContext);


                NetHelper.MyRequest(UrlTools.TITLE, HomeTabBean.class, new NetListener<HomeTabBean>() {

                    @Override
                    public void successListener(HomeTabBean response) {
                        list = response.getData().getChannels();
                        popAdapter.setDatas(list);
                        popRv.setAdapter(popAdapter);
                        GridLayoutManager manager = new GridLayoutManager(mContext, 4);
                        popRv.setLayoutManager(manager);

                    }

                    @Override
                    public void errorListener(VolleyError error) {

                    }
                });
                popAdapter.setmClick(new MyPopClick() {
                    @Override
                    public void myPopListener(int position, HomeTabBean.DataBean.ChannelsBean bean) {
                        vp.setCurrentItem(position);
                        popAdapter.setSelectIndex(position);
                        popupWindow.dismiss();
                    }
                });




            }
        });

    }


}
