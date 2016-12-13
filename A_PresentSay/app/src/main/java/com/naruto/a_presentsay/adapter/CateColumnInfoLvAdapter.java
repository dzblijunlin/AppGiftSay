package com.naruto.a_presentsay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.CateColumnInfoBean;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dllo on 16/12/10.
 */

public class CateColumnInfoLvAdapter extends BaseAdapter{
    private CateColumnInfoBean data;
    private Context context;

    public CateColumnInfoLvAdapter(Context context) {
        this.context = context;
    }

    public void setData(CateColumnInfoBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.getData().getPosts().size();
    }

    @Override
    public Object getItem(int i) {
        return data.getData().getPosts().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyColumnLvViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_column_lv,viewGroup,false);
            holder = new MyColumnLvViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (MyColumnLvViewHolder) view.getTag();
        }
        Picasso.with(context).load(data.getData().getPosts().get(i).getCover_image_url()).into(holder.iv);
        holder.titleTv.setText(data.getData().getPosts().get(i).getTitle());
        holder.nameTv.setText(data.getData().getPosts().get(i).getAuthor().getNickname());
        holder.likeTv.setText(data.getData().getPosts().get(i).getLikes_count() + "");
        String time = data.getData().getPosts().get(i).getCreated_at() + "";
        // 整理时间格式
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        Date date = new Date(Long.valueOf(time));
        String newTime = simpleDateFormat.format(date);
        holder.timeTv.setText(newTime);

        return view;
    }
    class MyColumnLvViewHolder {
        private ImageView iv;
        private TextView titleTv,timeTv,nameTv,likeTv;

        public MyColumnLvViewHolder (View view){
            iv = (ImageView) view.findViewById(R.id.iv_category_strategy_head_next);
            titleTv = (TextView) view.findViewById(R.id.tv_category_strategy_head_next_title);
            timeTv = (TextView) view.findViewById(R.id.tv_category_strategy_head_next_created_at);
            nameTv = (TextView) view.findViewById(R.id.tv_category_strategy_head_next_author_nick_name);
            likeTv = (TextView) view.findViewById(R.id.tv_category_strategy_head_next_likes_count);
        }
    }
}
