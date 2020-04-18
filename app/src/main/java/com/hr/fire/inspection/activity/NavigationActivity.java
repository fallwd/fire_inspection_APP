package com.hr.fire.inspection.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.hr.fire.inspection.R;

import java.util.ArrayList;
import java.util.List;


public class NavigationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        if (Build.VERSION.SDK_INT >= 23) {//6.0才用动态权限
            initPermission();
        }

        LinearLayout btn1 = (LinearLayout) findViewById(R.id.Navigation_Btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, ChooseCompanyActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout btn2 = (LinearLayout) findViewById(R.id.Navigation_Btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, RoutingInspectionActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout btn3 = (LinearLayout) findViewById(R.id.Navigation_Btn3);


        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        LinearLayout btn4 = (LinearLayout) findViewById(R.id.Navigation_Btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, RulesActivity.class);
                startActivity(intent);
            }
        });
    }


    //申请两个权限，录音和文件读写
    //1、首先声明一个数组permissions，将需要的权限都放在里面
    String[] permissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SYSTEM_ALERT_WINDOW,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};


    //权限判断和申请
    private void initPermission() {

    }
}
