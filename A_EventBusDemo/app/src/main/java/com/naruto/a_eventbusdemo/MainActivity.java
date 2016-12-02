package com.naruto.a_eventbusdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity{
    private EditText et;
    private Button btn;

    /**
     * EventBus使用操作手则
     * 1.把想要传的值写进一个实体类中
     * 2.在传值的类中对EventBus进行初始化,并且调用post方法进行传值
     * 3.在目标类(接收值的类中)
     * (1)注册EventBus---如果是在Activity中注册,需要写在onCreate生命周期中||如果是在Fragment中注册,需要写在onAttach生命周期中
     * (2)注销EventBus---如果是在Activity中注销,需要写在onDestroy生命周期中||如果是在Fragment中注销,需要写在onDetach生命周期中
     * (3)写一个方法用来接收值,方法上面加入注解,标明线程(一般动用Main)
     * <p>
     * EventBus大忌
     * 目标类必须已经存在
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
//        et = (EditText)findViewById(R.id.main_et);
//        btn = (Button) findViewById(R.id.main_btn);
//        btn.setOnClickListener(this);
//        // 注册EventBus
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        // 解除注册
//        EventBus.getDefault().unregister(this);
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)// 注解:代表运行在主线程之中
//    public void getData(Bean bean){
//        et.setText(bean.getData());
//    }
//    @Override
//    public void onClick(View view) {
//        Intent intent = new Intent(this,SecondActivity.class);
//        startActivity(intent);
//    }
    }
}
