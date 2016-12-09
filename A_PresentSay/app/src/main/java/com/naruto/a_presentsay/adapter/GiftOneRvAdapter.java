package com.naruto.a_presentsay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.GiftOneRvBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/12/8.
 */

public class GiftOneRvAdapter extends RecyclerView.Adapter<GiftOneRvAdapter.GiftOneRvViewHolder>{
    private GiftOneRvBean bean;
    private Context context;

    public GiftOneRvAdapter(Context context) {
        this.context = context;
    }

    public void setBean(GiftOneRvBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public GiftOneRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gift_one_rv_top,parent,false);
        GiftOneRvViewHolder holder = new GiftOneRvViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(GiftOneRvViewHolder holder, int position) {
        Picasso.with(context).load(bean.getData().getRecommend_posts().get(position).getCover_image_url()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return bean!=null?bean.getData().getRecommend_posts().size():0;
    }

    class GiftOneRvViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv ;
        public GiftOneRvViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.gift_one_rv_iv);
        }
    }
}
