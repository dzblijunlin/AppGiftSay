package com.naruto.a_presentsay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.CateStrategyTypeAllBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/12/13.
 */

public class CateStrategyTypeAllAdapter extends RecyclerView.Adapter<CateStrategyTypeAllAdapter.MyTypeAllViewHolder>{
    private List<CateStrategyTypeAllBean.DataBean.ChannelGroupsBean.ChannelsBean> data;
    private Context context;

    public CateStrategyTypeAllAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CateStrategyTypeAllBean.DataBean.ChannelGroupsBean.ChannelsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyTypeAllViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_strategy_type,parent,false);
        MyTypeAllViewHolder holder = new MyTypeAllViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyTypeAllViewHolder holder, int position) {
        Picasso.with(context).load(data.get(position).getCover_image_url()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    class MyTypeAllViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        public MyTypeAllViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.strategy_type_gride_img);
        }
    }
}
