package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toast.makeText(WelcomeActivity.this,"系统将在2秒后为您自动跳转到首页",Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //执行在主线程
                //启动主页面
                startActivity(new Intent(WelcomeActivity.this, NavigationActivity.class));
                //关闭当前页面
                finish();
            }
        },2000);
    }
}
