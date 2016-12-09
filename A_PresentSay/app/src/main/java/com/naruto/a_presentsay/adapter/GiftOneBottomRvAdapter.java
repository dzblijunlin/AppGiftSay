package com.naruto.a_presentsay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.GiftOneRvBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/12/9.
 */

public class GiftOneBottomRvAdapter extends RecyclerView.Adapter<GiftOneBottomRvAdapter.MyOneBottomViewHolder>{
    private GiftOneRvBean bean;
    private Context context;

    public GiftOneBottomRvAdapter(Context context) {
        this.context = context;
    }

    public void setBean(GiftOneRvBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public MyOneBottomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gift_one_rv_bottom,parent,false);
        MyOneBottomViewHolder holder = new MyOneBottomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyOneBottomViewHolder holder, int position) {
        Picasso.with(context).load(bean.getData().getRecommend_items().get(position).getCover_image_url()).into(holder.bottomIv);
        holder.bottomNameTv.setText(bean.getData().getRecommend_items().get(position).getName());
        holder.bottomPriceTv.setText(bean.getData().getRecommend_items().get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return bean!=null?bean.getData().getRecommend_items().size():0;
    }

    class MyOneBottomViewHolder extends RecyclerView.ViewHolder{
        private ImageView bottomIv;
        private TextView bottomNameTv,bottomPriceTv;
        public MyOneBottomViewHolder(View itemView) {
            super(itemView);
            bottomIv = (ImageView) itemView.findViewById(R.id.gift_info_one_bottom_iv);
            bottomNameTv = (TextView) itemView.findViewById(R.id.gift_info_one_name_tv);
            bottomPriceTv = (TextView) itemView.findViewById(R.id.gift_info_one_bottom_price_tv);
        }
    }
}
