package com.naruto.a_presentsay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.CateSingleBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dllo on 16/11/30.
 */

public class SingleGridAdapter extends BaseAdapter{
    private List<CateSingleBean.DataBean.CategoriesBean.SubcategoriesBean> datas;
    private Context context;
    private int selectIndex;

    public SingleGridAdapter(Context context) {
        this.context = context;
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }

    public void setDatas(List<CateSingleBean.DataBean.CategoriesBean.SubcategoriesBean> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas!=null&&datas.size()>0?datas.size():0;
    }

    @Override
    public Object getItem(int i) {
        return datas!=null?datas.get(i):null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyInViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_single_right_gv,viewGroup,false);
            holder = new MyInViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (MyInViewHolder) view.getTag();
        }
        holder.tv.setText(datas.get(i).getName());
        Picasso.with(context).load(datas.get(i).getIcon_url()).into(holder.iv);
        return view;
    }
    class MyInViewHolder{
        private ImageView iv;
        private TextView tv;
        public MyInViewHolder(View view){
            iv = (ImageView) view.findViewById(R.id.single_right_gv_iv);
            tv = (TextView) view.findViewById(R.id.single_right_gv_tv);
        }
    }
}
