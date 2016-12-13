package com.naruto.a_presentsay.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.HomeChooseBean;
import com.naruto.a_presentsay.bean.HomeGirlBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/11/24.
 */
// 主页精选适配器
public class HomeChooseAdapter extends BaseAdapter {
    private List<HomeChooseBean.DataBean.ItemsBean> data;
    private Context context;

    public HomeChooseAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<HomeChooseBean.DataBean.ItemsBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        Log.d("aaa", "data.size():" + data.size());
        return data != null && data.size() > 0 ? data.size() : 0;

    }

    @Override
    public Object getItem(int i) {
        return data != null ? data.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyChooseViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_choose, viewGroup, false);
            holder = new MyChooseViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (MyChooseViewHolder) view.getTag();
        }

            Picasso.with(context).load(data.get(i).getAuthor().getAvatar_url()).into(holder.headIv);

        Picasso.with(context).load(data.get(i).getCover_image_url()).into(holder.bigIv);

        holder.nameTv.setText(data.get(i).getAuthor().getNickname());
        holder.unnameTv.setText(data.get(i).getAuthor().getIntroduction());
        holder.titleTv.setText(data.get(i).getTitle());
        holder.inTv.setText(data.get(i).getIntroduction());
        if (data.get(i).getContent_type() != 1 ) {
            holder.uninTv.setText(data.get(i).getColumn().getTitle());
        }
        // holder.numTv.setText(data.get(i).getLikes_count() + "");

        return view;
    }

    class MyChooseViewHolder {
        private ImageView headIv, bigIv;
        private TextView nameTv, unnameTv, titleTv, inTv, uninTv, numTv;

        public MyChooseViewHolder(View view) {
            headIv = (ImageView) view.findViewById(R.id.choose_head_iv);
            bigIv = (ImageView) view.findViewById(R.id.choose_photo_iv);
            nameTv = (TextView) view.findViewById(R.id.choose_nickname_tv);
            unnameTv = (TextView) view.findViewById(R.id.choose_introduce_tv);
            titleTv = (TextView) view.findViewById(R.id.choose_title_tv);
            inTv = (TextView) view.findViewById(R.id.choose_introduction_tv);
            uninTv = (TextView) view.findViewById(R.id.choose_small_title_tv);
            numTv = (TextView) view.findViewById(R.id.choose_num_tv);
        }
    }
}
