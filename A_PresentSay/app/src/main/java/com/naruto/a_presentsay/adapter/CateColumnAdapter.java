package com.naruto.a_presentsay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.CateColumnBean;
import com.naruto.a_presentsay.tool.MyColumnClick;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/11/29.
 */

public class CateColumnAdapter extends RecyclerView.Adapter<CateColumnAdapter.MyColumnViewHolder> {
    private List<CateColumnBean.DataBean.ColumnsBean> data;
    private Context context;
    private MyColumnClick myColumnClick;

    public void setMyColumnClick(MyColumnClick myColumnClick) {
        this.myColumnClick = myColumnClick;


    }

    public CateColumnAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CateColumnBean.DataBean.ColumnsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyColumnViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_strategy, parent, false);
        MyColumnViewHolder holder = new MyColumnViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyColumnViewHolder holder, final int position) {
        Log.e("test","onBindViewHolder:"+myColumnClick);
        Picasso.with(context).load(data.get(position).getBanner_image_url()).into(holder.iv);
        holder.titleTv.setText(data.get(position).getTitle());
        holder.authorTv.setText(data.get(position).getAuthor());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("test","my:"+myColumnClick);
                myColumnClick.myColumnListener(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data != null && data.size() > 0 ? data.size() : 0;
    }

    class MyColumnViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView titleTv, authorTv;

        public MyColumnViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.strategy_one_item_iv);
            titleTv = (TextView) itemView.findViewById(R.id.strategy_one_title_tv);
            authorTv = (TextView) itemView.findViewById(R.id.strategy_one_author_tv);
        }
    }
}
