package com.hr.fire.inspection.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Button;
import android.widget.TextView;

import com.deepoove.poi.XWPFTemplate;
import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.FireItemAdapter;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.SystemList;
import com.hr.fire.inspection.service.ServiceFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//年检中: 设备列表
public class FireActivity extends AppCompatActivity {
    private long platform_id;
    private String company_name;
    private String oil_name;
    private String Platform_name;
    private List<CheckType> systemNameData;
    private ArrayList<SystemList> listSys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire);
        //上一个页面传入的平台ID, 公司名，平台名
        platform_id = (long) getIntent().getLongExtra("Platform_ID", 0);
        Bundle b = getIntent().getExtras();
        // 获取Bundle的信息
        // 获得公司名称  油田名称
        oil_name = b.getString("oil_name");
        Platform_name = b.getString("Platform_name");
        company_name = b.getString("company_name");
        initData();
    }

    private void initData() {
        systemNameData = ServiceFactory.getYearCheckService().getSystemNameData();
    }

    Intent intent = new Intent();

    @Override
    protected void onStart() {
        super.onStart();
        //列表
        ListView main_list = findViewById(R.id.main_list);
        FireItemAdapter fireItemAdapter = new FireItemAdapter(this);
        fireItemAdapter.setData(systemNameData);
        main_list.setAdapter(fireItemAdapter);

        //点击跳转详情页
        main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Long sys_id = systemNameData.get(position).getId();
                String str_title = systemNameData.get(position).getName();
                intent.setClass(FireActivity.this, SystemTagProtectionAreaActivity.class);
                intent.putExtra("sys_id", sys_id);
                intent.putExtra("platform_id", platform_id);
                intent.putExtra("f_title", str_title);
                startActivity(intent);
                //这个时候,需要关闭当前页面,并且关闭之前所有选择的页面
                finish();
            }
        });

        // 点击跳转报告页
        Button pr_but = findViewById(R.id.product_report_button);
        pr_but.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                for (int i = 0; i < systemNameData.size(); i++) {
                    listSys.add(new SystemList(systemNameData.get(i).getId(), systemNameData.get(i).getName()));
                }
                // 点击跳转
                Intent intent = new Intent(FireActivity.this, FireReportActivity.class);
                intent.putExtra("list", (Serializable) listSys);
                intent.putExtra("company_name", company_name);
                intent.putExtra("oil_name", oil_name);
                intent.putExtra("Platform_name", Platform_name);
                intent.putExtra("platform_id", platform_id);
                // 页面传值
                startActivity(intent);
            }
        });

        // 点击返回上一页
        ImageButton imageButton = (ImageButton) findViewById(R.id.backHome);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}