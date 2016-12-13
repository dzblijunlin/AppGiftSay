package com.naruto.a_presentsay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.CateSingleInfoBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/12/12.
 */

public class CateSingleInfoAdapter extends RecyclerView.Adapter<CateSingleInfoAdapter.MySingleInfoViewHolder>{
    private CateSingleInfoBean data;
    private Context context;

    public CateSingleInfoAdapter(Context context) {
        this.context = context;
    }

    public void setData(CateSingleInfoBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MySingleInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_single_info,parent,false);
        MySingleInfoViewHolder holder = new MySingleInfoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MySingleInfoViewHolder holder, int position) {
        Picasso.with(context).load(data.getData().getItems().get(position).getCover_image_url()).into(holder.iv);
        holder.titleTv.setText(data.getData().getItems().get(position).getName());
        holder.contentTv.setText(data.getData().getItems().get(position).getDescription());
        holder.priceTv.setText(data.getData().getItems().get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return data.getData().getItems().size();
    }

    class MySingleInfoViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView titleTv,contentTv,priceTv;
        public MySingleInfoViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.item_single_iv);
            titleTv = (TextView) itemView.findViewById(R.id.item_single_title_tv);
            contentTv = (TextView) itemView.findViewById(R.id.item_single_content_tv);
            priceTv = (TextView) itemView.findViewById(R.id.item_single_price_tv);
        }
    }

}
