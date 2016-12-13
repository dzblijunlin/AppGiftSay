package com.naruto.a_presentsay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.CateStrategyGvBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/12/10.
 */

public class CateStrategyGvInfoAdapter extends BaseAdapter{
    private CateStrategyGvBean data;
    private Context context;

    public CateStrategyGvInfoAdapter(Context context) {
        this.context = context;
    }

    public void setData(CateStrategyGvBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.getData().getItems().size();
    }

    @Override
    public Object getItem(int i) {
        return data.getData().getItems().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyStrategyGvLvViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_cate_strategy_gv_lv,viewGroup,false);
            holder = new MyStrategyGvLvViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (MyStrategyGvLvViewHolder) view.getTag();
        }
        Picasso.with(context).load(data.getData().getItems().get(i).getAuthor().getAvatar_url()).into(holder.headIv);
        Picasso.with(context).load(data.getData().getItems().get(i).getCover_image_url()).into(holder.bigIv);
        holder.nameTv.setText(data.getData().getItems().get(i).getAuthor().getNickname());
        holder.unnameTv.setText(data.getData().getItems().get(i).getAuthor().getIntroduction());
        holder.titleTv.setText(data.getData().getItems().get(i).getTitle());
        holder.inTv.setText(data.getData().getItems().get(i).getIntroduction());
        holder.uninTv.setText(data.getData().getItems().get(i).getColumn().getTitle());
        holder.numTv.setText(data.getData().getItems().get(i).getLikes_count() + "");





        return view;
    }
    class MyStrategyGvLvViewHolder {
        private ImageView headIv, bigIv;
        private TextView nameTv, unnameTv, titleTv, inTv, uninTv, numTv;
        public MyStrategyGvLvViewHolder(View view){
            headIv = (ImageView) view.findViewById(R.id.strategy_gv_head_iv);
            bigIv = (ImageView) view.findViewById(R.id.strategy_gv_photo_iv);
            nameTv = (TextView) view.findViewById(R.id.strategy_gv_nickname_tv);
            unnameTv = (TextView) view.findViewById(R.id.strategy_gv_introduce_tv);
            titleTv = (TextView) view.findViewById(R.id.strategy_gv_title_tv);
            inTv = (TextView) view.findViewById(R.id.strategy_gv_introduction_tv);
            uninTv = (TextView) view.findViewById(R.id.strategy_gv_small_title_tv);
            numTv = (TextView) view.findViewById(R.id.strategy_gv_num_tv);

        }
    }
}
