package com.naruto.a_presentsay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.CateStrategyCollumnInfoBean;
import com.naruto.a_presentsay.tool.ScreenSizeUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/12/13.
 */

public class CateCollumnAllInfoAdapter extends RecyclerView.Adapter<CateCollumnAllInfoAdapter.MyCollumnAllViewHolder>{
    private List<CateStrategyCollumnInfoBean.DataBean.ColumnsBean> data;
    private Context context;

    public CateCollumnAllInfoAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CateStrategyCollumnInfoBean.DataBean.ColumnsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MyCollumnAllViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_strategy_collumn_info,parent,false);
        MyCollumnAllViewHolder holder = new MyCollumnAllViewHolder(view);
        return holder ;
    }

    @Override
    public void onBindViewHolder(MyCollumnAllViewHolder holder, int position) {

        Picasso.with(context).load(data.get(position).getBanner_image_url()).into(holder.allImg);
        holder.allTileTv.setText(data.get(position).getTitle());
        holder.allContentTv.setText(data.get(position).getSubtitle());
        float width = ScreenSizeUtil.getScreenWidth(context);
        float height = ScreenSizeUtil.getScreenHeight(context);
        ViewGroup.LayoutParams params = holder.allImg.getLayoutParams();
        params.width = (int) (width / 3);
        params.height = (int) (height/7);
        holder.allImg.setLayoutParams(params);
        holder.allAuthorTv.setText(data.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return data!=null?data.size():0;
    }

    class MyCollumnAllViewHolder extends RecyclerView.ViewHolder{
        private ImageView allImg;
        private TextView allTileTv,allContentTv,allAuthorTv;
        public MyCollumnAllViewHolder(View itemView) {
            super(itemView);
            allImg = (ImageView) itemView.findViewById(R.id.item_startegy_info_img);
            allTileTv = (TextView) itemView.findViewById(R.id.item_startegy_title_info_tv);
            allContentTv = (TextView) itemView.findViewById(R.id.item_startegy_contact_info_tv);
            allAuthorTv = (TextView) itemView.findViewById(R.id.item_startegy_author_info_tv);
        }
    }
}
