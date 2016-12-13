package com.naruto.a_presentsay.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.bean.HomeTabBean;
import com.naruto.a_presentsay.tool.MyPopClick;

import java.util.List;

/**
 * Created by dllo on 16/12/2.
 */

public class HomePopAdapter extends RecyclerView.Adapter<HomePopAdapter.MyPopViewHolder>{
    private List<HomeTabBean.DataBean.ChannelsBean> datas;
    private Context context;
    private int selectIndex;
    private MyPopClick mClick;

    public HomePopAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<HomeTabBean.DataBean.ChannelsBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
        notifyDataSetChanged();
    }

    public void setmClick(MyPopClick mClick) {
        this.mClick = mClick;
    }

    @Override
    public MyPopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pop_window,parent,false);
        MyPopViewHolder holder = new MyPopViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyPopViewHolder holder, final int position) {
        holder.tv.setText(datas.get(position).getName());
//        if (position == selectIndex){
//            holder.tv.setTextColor(Color.RED);
//            holder.views.setBackgroundColor(Color.RED);
//        }else {
//            holder.tv.setTextColor(Color.BLACK);
//            holder.views.setBackgroundColor(Color.parseColor("#dfdfdfdf"));
//        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = holder.getAdapterPosition();
                HomeTabBean.DataBean.ChannelsBean bean = datas.get(position);
                mClick.myPopListener(pos,bean);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datas != null && datas.size() > 0 ? datas.size() : 0;
    }

    class MyPopViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;
        private View views;
        public MyPopViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.pop_window_tv);
            views = (View) itemView.findViewById(R.id.pop_window_view);
        }
    }
}
