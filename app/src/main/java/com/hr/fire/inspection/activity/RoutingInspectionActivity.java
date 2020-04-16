package com.hr.fire.inspection.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.adapter.RoutingInspectionAdapter;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.service.ServiceFactory;


import java.util.List;

public class RoutingInspectionActivity extends AppCompatActivity {
    private List<CheckType> systemNameData;
    private long platform_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routing_inspection);
        //上一个页面传入的平台ID
        platform_id = (long) getIntent().getLongExtra("Platform_ID", 0);
        initData();
    }

    Intent intent = new Intent();

    private void initData() {
        systemNameData = ServiceFactory.getYearCheckService().getSystemNameData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (systemNameData != null && systemNameData.size() != 0) {
            ListView main_list = findViewById(R.id.main_list);
            RoutingInspectionAdapter routingInspectionAdapter = new RoutingInspectionAdapter(this);
            routingInspectionAdapter.setData(systemNameData);
            main_list.setAdapter(routingInspectionAdapter);
            //点击跳转详情页
            main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Long sys_id = systemNameData.get(position).getId();
//                    String str_title = systemNameData.get(position).getName();
//                    //跳转详情
//                    intent.setClass(RoutingInspectionActivity.this, CarbonDioxideAcitivty.class);
//                    intent.putExtra("sys_id", sys_id);
//                    intent.putExtra("platform_id", platform_id);
//                    intent.putExtra("f_title", str_title);
//                    startActivity(intent);
//                    //这个时候,需要关闭当前页面,并且关闭之前所有选择的页面
//                    finish();
                }
            });
        }


        // 点击生成报告
        Button pr_but = findViewById(R.id.product_report_button);
        pr_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "敬请期待", Toast.LENGTH_LONG).show();
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