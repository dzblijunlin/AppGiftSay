package com.naruto.a_presentsay.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.activity.CateSingleInfoActivity;
import com.naruto.a_presentsay.adapter.CateSingleLeftAdapter;
import com.naruto.a_presentsay.adapter.CateSingleRightAdapter;
import com.naruto.a_presentsay.bean.CateSingleBean;
import com.naruto.a_presentsay.tool.UrlTools;
import com.naruto.a_presentsay.view.MyGridView;
import com.naruto.a_presentsay.volley.NetHelper;
import com.naruto.a_presentsay.volley.NetListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
// 分类-单品
public class SingleFragment extends BaseFragment {
    private int selectIndex = 0;
    private ListView leftLv,rightLv;
    private CateSingleLeftAdapter leftAdapter;
    private CateSingleRightAdapter rightAdapter;
    private List<CateSingleBean.DataBean.CategoriesBean> leftDatas;// 左边的listview
    private List<CateSingleBean.DataBean.CategoriesBean> rightDatas;// 右边的listview
    private List<CateSingleBean.DataBean.CategoriesBean.SubcategoriesBean> gridDatas;
    private MyGridView gv;
    @Override
    protected int setlayout() {
        return R.layout.fragment_single;
    }

    @Override
    void initView(View view) {
        leftLv = bindView(R.id.single_left_lv);
        rightLv = bindView(R.id.single_right_lv);

    }

    @Override
    void initData() {
        getContent();
        onItemClick();

    }

    private void getGvData() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_single_right,null);
        gv = (MyGridView) view.findViewById(R.id.single_right_gv);
        Log.d("SingleFragment", "abcd");
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mContext, CateSingleInfoActivity.class);

                startActivity(intent);
            }
        });
    }

    private void onItemClick() {
        leftLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectIndex = i;
                leftAdapter.setSelectIndex(i);
                leftAdapter.notifyDataSetChanged();
                // 当点击某个item的时候让这个item自动滑动到listview的顶部
                // 如果点击的是最后一个就不能到达顶部了
                leftLv.smoothScrollToPositionFromTop(i,0);
                rightAdapter.setIndex(i);
                rightLv.setSelection(i);

            }
        });
        /**
         * 记录listview的状态
         *
         * scrollState = SCROLL_STATE_TOUCH_SCROLL(1) 正在滚动
         * scrollState = SCROLL_STATE_FLING(2) 手指做了抛的动作（手指离开屏幕前，用力滑了一下）
         * scrollState = SCROLL_STATE_IDLE(0) 停止滚动
         */
        rightLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int scrollState;
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                this.scrollState = i;
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                // 当用户已经停止滑动了，就不再执行下面的操作
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    return;
                }
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    return;
                }
                //把下标传过去，然后刷新adapter
                leftAdapter.setIndex(i);
                leftLv.smoothScrollToPositionFromTop(i,0);
                leftLv.setSelection(i);
                leftAdapter.notifyDataSetChanged();

            }
        });
    }

    private void getContent() {
        String singleUrl = UrlTools.SINGLE;
        NetHelper.MyRequest(singleUrl, CateSingleBean.class, new NetListener<CateSingleBean>() {
            @Override
            public void successListener(CateSingleBean response) {
                leftDatas = response.getData().getCategories();
                rightDatas = response.getData().getCategories();
                leftAdapter = new CateSingleLeftAdapter(mContext);
                leftAdapter.setData(leftDatas);
                leftLv.setAdapter(leftAdapter);
                gridDatas = new ArrayList<>();
                rightAdapter = new CateSingleRightAdapter(mContext);
                rightAdapter.setDatas(rightDatas);
                rightAdapter.setList(gridDatas);
                rightLv.setAdapter(rightAdapter);


            }

            @Override
            public void errorListener(VolleyError error) {

            }
        });
    }
}
