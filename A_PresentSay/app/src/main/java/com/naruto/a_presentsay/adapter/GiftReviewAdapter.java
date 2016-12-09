package com.naruto.a_presentsay.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.GiftReviewBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/12/9.
 */

public class GiftReviewAdapter extends BaseAdapter{
    private GiftReviewBean bean;
    private Context context;

    public GiftReviewAdapter(Context context) {
        this.context = context;
    }

    public void setBean(GiftReviewBean bean) {
        this.bean = bean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return bean.getData().getComments().size();
    }

    @Override
    public Object getItem(int i) {
        return bean.getData().getComments().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyReviewViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_gift_review,viewGroup,false);
            holder = new MyReviewViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (MyReviewViewHolder) view.getTag();
        }
        Picasso.with(context).load(bean.getData().getComments().get(i).getUser().getAvatar_url()).into(holder.iv);
        holder.nameTv.setText(bean.getData().getComments().get(i).getUser().getNickname());
        holder.contentTv.setText(bean.getData().getComments().get(i).getContent());
        return view;
    }
    class MyReviewViewHolder {
        private ImageView iv;
        private TextView nameTv,contentTv;
        public MyReviewViewHolder(View view){
            iv = (ImageView) view.findViewById(R.id.gift_review_iv);
            nameTv = (TextView) view.findViewById(R.id.gift_review_name_tv);
            contentTv = (TextView) view.findViewById(R.id.gift_review_content_tv);
        }
    }
}
