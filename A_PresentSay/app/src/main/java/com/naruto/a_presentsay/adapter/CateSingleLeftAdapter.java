package com.naruto.a_presentsay.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.CateSingleBean;

import java.util.List;

/**
 * Created by dllo on 16/11/30.
 */

public class CateSingleLeftAdapter extends BaseAdapter{
    private List<CateSingleBean.DataBean.CategoriesBean> data;
    private Context context;
    private int selectIndex;

    public CateSingleLeftAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<CateSingleBean.DataBean.CategoriesBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return data!=null&&data.size()>0?data.size():0;
    }

    @Override
    public Object getItem(int i) {
        return data!=null?data.get(i):null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyLeftViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_single_left,viewGroup,false);
            holder = new MyLeftViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (MyLeftViewHolder) view.getTag();
        }
        holder.tv.setText(data.get(i).getName());
        // 设置光标以及字体的颜色
        if (i == selectIndex){
            holder.tv.setTextColor(Color.argb(255,255,52,77));
            holder.mView.setBackgroundColor(Color.argb(255,255,45,71));

        }else {
            holder.tv.setBackgroundColor(Color.WHITE);
            holder.tv.setTextColor(Color.argb(255,52,52,52));
            holder.mView.setBackgroundColor(Color.argb(255,246,246,247));
        }


        return view;
    }
    public  void setIndex(int index){
        selectIndex = index;
    }

    class MyLeftViewHolder{
        private TextView tv;
        private View mView;
        public MyLeftViewHolder(View view){
            tv = (TextView) view.findViewById(R.id.single_left_tv);
            mView = (View) view.findViewById(R.id.single_left_view);
        }
    }
}
