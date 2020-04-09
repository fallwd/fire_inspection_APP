package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.sqlHelpers.BaseData;
import com.hr.fire.inspection.sqlHelpers.YearCheckData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
//        String companyName = "辽东作业公司";
//        String oilfieldName = "SZ36-1";
//        String platformName = "SZ36-1C";
//        String systemName = "高压二氧化碳系统灭火系统";
//        String tableName = "药剂瓶";
//        String number = "SD002";
//        List<ItemInfo> itemDataList = ServiceFactory.getYearCheckService().getItemData(companyName,oilfieldName,platformName,systemName,tableName, number);
//        String tableName = "药剂瓶检查表";
//        List checkDataList = ServiceFactory.getYearCheckService().getCheckData(tableName);
//        Log.i("getCheckData",checkDataList.get(0).toString());
//        platformName = "SZ36-1C";
//        ItemInfo itemObj = new ItemInfo();
//        String no = "YJP0002";
//        String volume = "9";
//        String weight = "3";
//        String goodsWeight = "8";
//        String prodFactory = "红日药业";
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date prodDate = null;
//        try {
//            prodDate = format.parse("2018-08-03");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Date observeDate = null;
//        try {
//            observeDate = format.parse("2020-04-09");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Date checkDate = null;
//        try {
//            checkDate = format.parse("2020-04-09");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        String isPass = "否";
//        String labelNo = "BQ0002";
//        String systemNumber = "SD002";
//        String protectArea = "主配电间";
//        String codePath = "/src/YJP0002.jpg";
//        itemObj.setNo(no);
//        itemObj.setVolume(volume);
//        itemObj.setWeight(weight);
//        itemObj.setGoodsWeight(goodsWeight);
//        itemObj.setProdFactory(prodFactory);
//        itemObj.setProdDate(prodDate);
//        itemObj.setObserveDate(observeDate);
//        itemObj.setCheckDate(checkDate);
//        itemObj.setIsPass(isPass);
//        itemObj.setLabelNo(labelNo);
//        itemObj.setLabelNo(labelNo);
//        itemObj.setSystemNumber(systemNumber);
//        itemObj.setProtectArea(protectArea);
//        itemObj.setCodePath(codePath);
//
//        ServiceFactory.getYearCheckService().insertItemData(itemObj,companyName,oilfieldName,platformName,systemName,tableName, number);
        // 插入检查结果数据测试
        // 有itemId
//        String companyName = "辽东作业公司";
//        String oilfieldName = "SZ36-1";
//        String platformName = "SZ36-1C";
//        String systemName = "高压二氧化碳系统灭火系统";
//        String itemTableName = "药剂瓶";
//        String number = "SD002";
//        List<ItemInfo> itemDataList = ServiceFactory.getYearCheckService().getItemData(companyName,oilfieldName,platformName,systemName,itemTableName, number);
//        long itemId = itemDataList.get(0).getId();
//        // 获取checkId
//        String checkTableName = "药剂瓶检查表";
//        List<YearCheck> checkDataList = ServiceFactory.getYearCheckService().getCheckData(checkTableName);
//        long checkId = checkDataList.get(0).getId();
//        YearCheckResult yearCheckResult = new YearCheckResult();
//        String isPass1 = "是";
//        String imageUrl1 = "imgs/xxx";
//        String description1 = "xxxxxxxxxxxxxxxxxxx";
//        yearCheckResult.setIsPass(isPass1);
//        yearCheckResult.setImageUrl(imageUrl1);
//        yearCheckResult.setDescription(description1);
//        ServiceFactory.getYearCheckService().insertCheckResultData(yearCheckResult,itemId,checkId,companyName, oilfieldName, platformName, systemName, itemTableName, checkTableName);

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
