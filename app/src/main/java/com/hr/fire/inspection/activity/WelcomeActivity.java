package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.sqlHelpers.BaseData;
import com.hr.fire.inspection.sqlHelpers.YearCheckData;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

//         初始化数据库,插入测试数据
//        BaseData baseData = new BaseData();
//        baseData.initData();
        // 调用接口测试
        String companyName = "辽东作业公司";
        String oilfieldName = "SZ36-1";
        String platformName = "SZ36-1B";
        String systemName = "高压二氧化碳系统灭火系统";
        String tableName = "药剂瓶";
        String number = "SD001";
        List itemDataList = ServiceFactory.getYearCheckService().getItemData(companyName,oilfieldName,platformName,systemName,tableName, number);
//        for(int i = 0; i < itemDataList.size(); i++) {
//            Object object = itemDataList.get(i);
//            long id = ((ItemInfo) object).getId();
////            List checkResultList = ServiceFactory.getYearCheckService().getCheckResultData(id);
//        }

        Toast.makeText(WelcomeActivity.this,"系统将在2秒后为您自动跳转到首页",Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 执行在主线程
                // 启动主页面
                startActivity(new Intent(WelcomeActivity.this, NavigationActivity.class));
                //关闭当前页面
                finish();
            }
        },2000);

    }
}
