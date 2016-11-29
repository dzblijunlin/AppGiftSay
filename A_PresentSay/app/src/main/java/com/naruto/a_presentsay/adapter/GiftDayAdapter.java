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

import java.util.List;

/**
 * Created by dllo on 16/11/25.
 */

public class GiftDayAdapter extends RecyclerView.Adapter{
    private GiftDayBean data;

    private Context context;
    // item 类型
    public static final int TYPE_HEAD = 0;
    public static final int TYPE_NORMAL = 1;
    private int headCount = 1;
    private LayoutInflater mLayoutInflater;

    public GiftDayAdapter(Context context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void setData(GiftDayBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    // 内容长度
    public int getContentItemCount(){
        return data != null ? data.getData().getItems().size():0;
    }


    // 判断当前Item是否是头布局
    public boolean isHeadView(int position){
        return headCount!=0&& position<headCount;
    }

    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (headCount!=0&&position<headCount){
            return TYPE_HEAD;
        }else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case TYPE_HEAD:
                View viewHead = LayoutInflater.from(context).inflate(R.layout.item_gift_top,parent,false);
                holder = new MyHeadViewHolder(viewHead);
                break;
            case TYPE_NORMAL:
                View view = LayoutInflater.from(context).inflate(R.layout.item_every_day,parent,false);
                holder = new MyDayViewHolder(view);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type =getItemViewType(position);
        switch (type){
            case TYPE_HEAD:
                MyHeadViewHolder headViewHolder = (MyHeadViewHolder) holder;
                Picasso.with(context).load(data.getData().getCover_image()).into(headViewHolder.iv);
                break;
            case TYPE_NORMAL:
                MyDayViewHolder dayViewHolder = (MyDayViewHolder) holder;
                Picasso.with(context).load(data.getData().getItems().get(position).getCover_image_url()).into(dayViewHolder.iv);
                dayViewHolder.shortTv.setText(data.getData().getItems().get(position).getShort_description());
                dayViewHolder.nameTv.setText(data.getData().getItems().get(position).getName());
                dayViewHolder.priceTv.setText(data.getData().getItems().get(position).getPrice());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data!=null&&data.getData().getItems().size()>0?data.getData().getItems().size():0;
    }

    class MyDayViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView shortTv,nameTv,priceTv;
        public MyDayViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.item_every_iv);
            shortTv = (TextView) itemView.findViewById(R.id.item_every_title_tv);
            nameTv = (TextView) itemView.findViewById(R.id.item_every_content_tv);
            priceTv = (TextView) itemView.findViewById(R.id.item_every_price_tv);
        }
    }
    class MyHeadViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        public MyHeadViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.gift_top_iv);
        }
    }
}
