package com.naruto.a_presentsay.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.naruto.a_presentsay.R;
import com.naruto.a_presentsay.fragment.CategoryFragment;
import com.naruto.a_presentsay.fragment.GiftFragment;
import com.naruto.a_presentsay.fragment.HomeFragment;
import com.naruto.a_presentsay.fragment.ProfileFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button homeBtn,giftBtn,categoryBtn,profileBtn;

    @Override
    int setlayout() {
        return R.layout.activity_main;
    }

    @Override
    void initView() {
        homeBtn = bindView(R.id.main_home_btn);
        giftBtn = bindView(R.id.main_gift_btn);
        categoryBtn = bindView(R.id.main_category_btn);
        profileBtn = bindView(R.id.main_profile_btn);
    }

    @Override
    void initData() {
        homeBtn.setOnClickListener(this);
        giftBtn.setOnClickListener(this);
        categoryBtn.setOnClickListener(this);
        profileBtn.setOnClickListener(this);
        replaceFragment(new HomeFragment());

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_home_btn:
                replaceFragment(new HomeFragment());
                break;
            case R.id.main_gift_btn:
                replaceFragment(new GiftFragment());
                break;
            case R.id.main_category_btn:
                replaceFragment(new CategoryFragment());
                break;
            case R.id.main_profile_btn:
                replaceFragment(new ProfileFragment());
                break;
        }
    }
    // 替换占位布局
    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_replace,fragment);
        transaction.commit();
    }
}
