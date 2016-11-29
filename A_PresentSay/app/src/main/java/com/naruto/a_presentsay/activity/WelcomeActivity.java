package com.naruto.a_presentsay.activity;

import android.content.Intent;
import android.os.CountDownTimer;

import android.view.View;
import android.widget.ImageView;

import com.naruto.a_presentsay.R;

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv;
    private CountDownTimer countDownTimer;
    @Override
    int setlayout() {
        return R.layout.activity_welcome;
    }

    @Override
    void initView() {
        iv = bindView(R.id.welcome_iv);
        iv.setOnClickListener(this);
    }

    @Override
    void initData() {
        setCountDownTimer();
        countDownTimer.start();
    }
    // 启动倒计时
    private void setCountDownTimer(){
        countDownTimer = new CountDownTimer(5000,1000) {
            int a = 5;
            @Override
            public void onTick(long l) {
                --a;
                if (a == 1){
                    onFinish();
                }
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                countDownTimer.cancel();
                startActivity(intent);
                finish();
            }
        };
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        countDownTimer.cancel();
        finish();
    }
}
