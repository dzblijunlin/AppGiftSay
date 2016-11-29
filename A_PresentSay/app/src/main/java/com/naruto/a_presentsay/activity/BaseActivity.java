package com.naruto.a_presentsay.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by dllo on 16/11/23.
 */

public abstract class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setlayout());
        initView();
        initData();
    }
    // 设置布局的
    abstract int setlayout();
    // 初始化组件
    abstract void initView();
    // 初始化数据的方法
    abstract void initData();

    public <T extends View> T bindView(int id){
        return (T)findViewById(id);
    }
}

