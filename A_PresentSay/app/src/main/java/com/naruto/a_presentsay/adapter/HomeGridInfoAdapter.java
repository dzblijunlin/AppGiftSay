package com.naruto.a_presentsay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.HomeBannerBean;
import com.naruto.a_presentsay.bean.HomeGridInfoBean;
import com.squareup.picasso.Picasso;

/**
 * Created by dllo on 16/12/5.
 */

// 首页六宫格二级页面适配器
public class HomeGridInfoAdapter extends BaseAdapter{
    private HomeGridInfoBean data;
    private Context context;

    public HomeGridInfoAdapter(Context context) {
        this.context = context;
    }

    public void setData(HomeGridInfoBean data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data!=null?data.getData().getPosts().size():0;
    }

    @Override
    public Object getItem(int i) {
        return data!=null?data.getData().getPosts().get(i):null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyGridViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_home_grid_second,viewGroup,false);
            holder = new MyGridViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (MyGridViewHolder) view.getTag();
        }
        Picasso.with(context).load(data.getData().getPosts().get(i).getAuthor().getAvatar_url()).into(holder.headIv);
        Picasso.with(context).load(data.getData().getPosts().get(i).getCover_image_url()).into(holder.bigIv);
        holder.nameTv.setText(data.getData().getPosts().get(i).getAuthor().getNickname());
        holder.unnameTv.setText(data.getData().getPosts().get(i).getAuthor().getIntroduction());
        holder.titleTv.setText(data.getData().getPosts().get(i).getTitle());
        holder.inTv.setText(data.getData().getPosts().get(i).getIntroduction());
        holder.uninTv.setText(data.getData().getPosts().get(i).getColumn().getTitle());
        holder.numTv.setText(data.getData().getPosts().get(i).getLikes_count() + "");
        return view;
    }
    class MyGridViewHolder {
        private ImageView headIv, bigIv;
        private TextView nameTv, unnameTv, titleTv, inTv, uninTv, numTv;
        public MyGridViewHolder(View view){
            headIv = (ImageView) view.findViewById(R.id.grid_head_iv);
            bigIv = (ImageView) view.findViewById(R.id.grid_photo_iv);
            nameTv = (TextView) view.findViewById(R.id.grid_nickname_tv);
            unnameTv = (TextView) view.findViewById(R.id.grid_introduce_tv);
            titleTv = (TextView) view.findViewById(R.id.grid_title_tv);
            inTv = (TextView) view.findViewById(R.id.grid_introduction_tv);
            uninTv = (TextView) view.findViewById(R.id.grid_small_title_tv);
            numTv = (TextView) view.findViewById(R.id.grid_num_tv);
        }
    }
}
