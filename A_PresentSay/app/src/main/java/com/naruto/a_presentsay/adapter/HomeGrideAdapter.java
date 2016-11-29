package com.naruto.a_presentsay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.HomeGrideBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/24.
 */
// 主页头布局之六宫格适配器
public class HomeGrideAdapter extends BaseAdapter{
    private List<HomeGrideBean.DataBean.SecondaryBannersBean> data;
    private Context context;

    public HomeGrideAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<HomeGrideBean.DataBean.SecondaryBannersBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data!=null&&data.size()>0?data.size():0;
    }

    @Override
    public Object getItem(int i) {
        return data != null?data.get(i):null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyGrideViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_home_gride,viewGroup,false);
            holder = new MyGrideViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (MyGrideViewHolder) view.getTag();
        }
        Picasso.with(context).load(data.get(i).getImage_url()).into(holder.iv);

        return view;
    }
    class MyGrideViewHolder{
        private ImageView iv;
        public MyGrideViewHolder(View view){
            iv = (ImageView) view.findViewById(R.id.item_gride_iv);
        }
    }
}
