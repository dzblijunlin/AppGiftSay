package com.naruto.a_presentsay.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.CateFootBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/11/29.
 */

public class CateFootAdapter extends BaseAdapter{
    private List<CateFootBean.DataBean.ChannelGroupsBean> data;
    private Context context;

    public CateFootAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CateFootBean.DataBean.ChannelGroupsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data == null? 0:6;
    }

    @Override
    public Object getItem(int i) {
        return data!=null?data.get(i):null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyCateFootViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_strategy_foot,viewGroup,false);
            holder = new MyCateFootViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (MyCateFootViewHolder) view.getTag();
        }
        Picasso.with(context).load(data.get(0).getChannels().get(i).getCover_image_url()).into(holder.iv);


        return view;
    }
    class MyCateFootViewHolder {
        private ImageView iv;
        public MyCateFootViewHolder(View view){
            iv = (ImageView) view.findViewById(R.id.strategy_two_iv);
        }
    }
}
