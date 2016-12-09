package com.naruto.a_presentsay.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/12/7.
 */

public class GiftTabInfoAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> data;
    public GiftTabInfoAdapter(FragmentManager fm,ArrayList<Fragment> data) {
        super(fm);
        this.data = data;
    }

    public void setData(ArrayList<Fragment> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
