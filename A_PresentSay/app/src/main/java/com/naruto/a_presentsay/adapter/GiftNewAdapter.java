package com.naruto.a_presentsay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.GiftDayBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/12/2.
 */

public class GiftNewAdapter extends RecyclerView.Adapter<GiftNewAdapter.MyNewViewHolder>{
    private GiftDayBean data;
    private Context context;

    public GiftNewAdapter(Context context) {
        this.context = context;
    }

    public void setData(GiftDayBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyNewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_every_day,parent,false);
        MyNewViewHolder holder = new MyNewViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyNewViewHolder holder, int position) {
        Picasso.with(context).load(data.getData().getItems().get(position).getCover_image_url()).into(holder.iv);
        holder.shortTv.setText(data.getData().getItems().get(position).getShort_description());
        holder.nameTv.setText(data.getData().getItems().get(position).getName());
        holder.priceTv.setText(data.getData().getItems().get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return data!=null&&data.getData().getItems().size()>0?data.getData().getItems().size():0;
    }

    class MyNewViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView shortTv,nameTv,priceTv;
        public MyNewViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.item_every_iv);
            shortTv = (TextView) itemView.findViewById(R.id.item_every_title_tv);
            nameTv = (TextView) itemView.findViewById(R.id.item_every_content_tv);
            priceTv = (TextView) itemView.findViewById(R.id.item_every_price_tv);
        }
    }
}
