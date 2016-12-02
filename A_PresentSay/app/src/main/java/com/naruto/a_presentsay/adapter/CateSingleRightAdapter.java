package com.naruto.a_presentsay.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.CateSingleBean;
import com.naruto.a_presentsay.view.MyGridView;

import java.util.List;

/**
 * Created by dllo on 16/11/30.
 */

public class CateSingleRightAdapter extends BaseAdapter{
    List<CateSingleBean.DataBean.CategoriesBean> datas;
    private Context context;
    private int selectIndex;
    private List<CateSingleBean.DataBean.CategoriesBean.SubcategoriesBean> list;

    public CateSingleRightAdapter(Context context) {
        this.context = context;

    }

    public void setDatas(List<CateSingleBean.DataBean.CategoriesBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
        notifyDataSetChanged();
    }

    public void setList(List<CateSingleBean.DataBean.CategoriesBean.SubcategoriesBean> list) {
        this.list = list;
        notifyDataSetChanged();
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
        MyRightViewHolder holder = null;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_single_right,viewGroup,false);
            holder = new MyRightViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (MyRightViewHolder) view.getTag();
        }
        CateSingleBean.DataBean.CategoriesBean bean = datas.get(i);
        if (bean.getName().equals("热门分类")){
            holder.tv.setText("");
            holder.mView.setBackgroundColor(Color.WHITE);
        }else {
            holder.tv.setText(bean.getName());
            holder.mView.setBackgroundColor(Color.argb(255,235,235,235));
        }
        list = bean.getSubcategories();
        holder.adapter.setDatas(list);
        holder.gv.setAdapter(holder.adapter);



        return view;
    }
    public  void setIndex(int index){
        selectIndex = index;
    }

    class MyRightViewHolder{
        private TextView tv;
        private GridView gv;
        private View mView;
        SingleGridAdapter adapter;
        public MyRightViewHolder(View view){
            tv = (TextView) view.findViewById(R.id.single_right_title_tv);
            gv = (MyGridView) view.findViewById(R.id.single_right_gv);
            mView = (View) view.findViewById(R.id.single_right_view);
            adapter = new SingleGridAdapter(context);
        }
    }
}
