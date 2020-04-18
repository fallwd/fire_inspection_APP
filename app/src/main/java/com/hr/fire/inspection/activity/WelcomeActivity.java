package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.dao.DaoSession;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.sqlHelpers.BaseData;
import com.hr.fire.inspection.sqlHelpers.YearCheckData;
import com.hr.fire.inspection.utils.DBManager;
import com.hr.fire.inspection.utils.GreenDaoHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

//         初始化数据库,插入测试数据
        // 清空数据库
        DBManager cleanObj = new DBManager(this);
        cleanObj.deleSQL();
        // 初始化数据库
        BaseData baseData = new BaseData();
        baseData.initData();
        baseData.insertTestData();
        // 调用接口测试
//        List<CompanyInfo> companyList = ServiceFactory.getCompanyInfoService().getAll();
//        for(int i=0;i<companyList.size();i++){
//            Log.i("companyList",companyList.get(i).toString());
//        }
//        List<CheckType> checkTypeList = ServiceFactory.getYearCheckService().getCheckTypeAll();
//        for(int i=0;i<checkTypeList.size();i++){
//            Log.i("checkTypeList",checkTypeList.get(i).toString());
//        }

//        List<YearCheck> yearList = ServiceFactory.getYearCheckService().getCheckDataAll();
//        for(int i=0;i<yearList.size();i++){
//            Log.i("yearList",yearList.get(i).toString());
//        }
//
//        List<CheckType> checkTypeList = ServiceFactory.getYearCheckService().getSystemNameData();
//        for(int i=0;i<checkTypeList.size();i++){
//            Log.i("checkTypeList",checkTypeList.get(i).toString());
//            long systemId = checkTypeList.get(i).getId();
//            List<CheckType> checkTypeList2 = ServiceFactory.getYearCheckService().gettableNameData(systemId);
//            for(int j=0;j<checkTypeList2.size();j++){
//                Log.i("checkTypeList2",checkTypeList2.get(j).toString());
//                long tableId = checkTypeList2.get(j).getId();
////                Log.i("checkTypeList2",tableId+"");
//                List<YearCheck> checkList = ServiceFactory.getYearCheckService().getCheckDataEasy(tableId);
//                for(int y=0;y<checkList.size();y++){
//                    Log.i("checkList",checkList.get(y).toString());
//                }
//            }
//        }
        /////////////////////////////////////////////////////////////////////////////////////////////
//        // 测试查询接口
//        // companyInfoId 对应 辽东作业公司 SZ36-1 SZ36-1A--》3
//        long companyInfoId = 165;
//        // checkTypeId 对应 药剂瓶 2
//        long checkTypeId = 2;
//        // number 区号 SD002
//        String number = "";
//        // checkDate 检查日期
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date checkDate = null;
//        try {
////            checkDate = format.parse("2019-08-03 10:10");
//            checkDate = format.parse("2019-07-03 09:10");
////            checkDate = format.parse("2019-07-03 09:10");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Log.i("getItemDataEasy","" + checkDate);
//        List<ItemInfo> dataList = ServiceFactory.getYearCheckService().getItemDataEasy(companyInfoId, checkTypeId, number, checkDate);
//        for(int i=0;i<dataList.size();i++){
//            Log.i("getItemDataEasy",dataList.get(i).toString());
//        }
        ////////////////////////////////////////////////////////////////////////////////////////////

//        BaseData baseData = new BaseData();
//        baseData.initData();
//        // 调用接口测试
//        String companyName = "辽东作业公司";
//        String oilfieldName = "SZ36-1";
//        String platformName = "SZ36-1D";
//        String systemName = "高压二氧化碳系统灭火系统";
//        String tableName = "药剂瓶";
//        String number = "SD002";
//        List<ItemInfo> itemDataList = ServiceFactory.getYearCheckService().getItemData(companyName,oilfieldName,platformName,systemName,tableName, number);
//        // 更新接口测试
//        ItemInfo itemInfo = itemDataList.get(0);
//        itemInfo.setWeight("1111");
//        itemInfo.setGoodsWeight("2223");
//        ServiceFactory.getYearCheckService().update(itemInfo);
//        // 删除接口测试
//        ItemInfo itemInfo = itemDataList.get(0);
//        ServiceFactory.getYearCheckService().delete(itemInfo);


//        String tableName = "药剂瓶检查表";
//        List checkDataList = ServiceFactory.getYearCheckService().getCheckData(tableName);
//        Log.i("getCheckData",checkDataList.get(0).toString());

//        platformName = "SZ36-1C";
        // 插入设备数据测试
//        ItemInfo itemObj = new ItemInfo();
//        String no = "YJP0002";
//        String volume = "9";
//        String weight = "3";
//        String goodsWeight = "8";
//        String prodFactory = "红日药业";
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date prodDate = null;
//        try {
//            prodDate = format.parse("2018-08-03 22:30:01");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Date observeDate = null;
//        try {
//            observeDate = format.parse("2020-04-09 22:30:01");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Date checkDate = null;
//        try {
//            checkDate = format.parse("2020-04-09 22:30:01");
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
//        String companyName = "辽东作业公司";
//        String oilfieldName = "SZ36-1";
//        String platformName = "SZ36-1D";
//        String systemName = "高压二氧化碳系统灭火系统";
//        String tableName = "药剂瓶";
//        String number = "SD002";
//        Log.i("ItemInfo:::",itemObj.toString());
//        ServiceFactory.getYearCheckService().insertItemData(itemObj,companyName,oilfieldName,platformName,systemName,tableName, number);


        // 插入检查结果数据测试
        // 有itemId
//        String companyName = "辽东作业公司";
//        String oilfieldName = "SZ36-1";
//        String platformName = "SZ36-1D";
//        String systemName = "高压二氧化碳系统灭火系统";
//        String itemTableName = "药剂瓶";
//        String number = "SD002";
//        List<ItemInfo> itemDataList = ServiceFactory.getYearCheckService().getItemData(companyName,oilfieldName,platformName,systemName,itemTableName, number);
//        long itemId = itemDataList.get(0).getId();
//        // 获取checkId
//        String checkTableName = "药剂瓶检查表";
//        List<YearCheck> checkDataList = ServiceFactory.getYearCheckService().getCheckData(checkTableName);
//        long checkId = checkDataList.get(1).getId();
//        YearCheckResult yearCheckResult = new YearCheckResult();
//        String isPass1 = "是";
//        String imageUrl1 = "dddddddddddddddddd";
//        String description1 = "这是药剂瓶的检查表数据ddddddddddd";
//        yearCheckResult.setIsPass(isPass1);
//        yearCheckResult.setImageUrl(imageUrl1);
//        yearCheckResult.setDescription(description1);
//        yearCheckResult.setSystemNumber(number);
//        ServiceFactory.getYearCheckService().insertCheckResultData(yearCheckResult,itemId,checkId,companyName, oilfieldName, platformName, systemName, itemTableName, checkTableName);

        //无itemId
//        String companyName = "辽东作业公司";
//        String oilfieldName = "SZ36-1";
//        String platformName = "SZ36-1D";
//        String systemName = "高压二氧化碳系统灭火系统";
//        String itemTableName = "";
//        String number = "SD002";
////        List<ItemInfo> itemDataList = ServiceFactory.getYearCheckService().getItemData(companyName,oilfieldName,platformName,systemName,itemTableName, number);
//        long itemId = 0;
//        // 获取checkId
//        String checkTableName = "管线管件";
//        List<YearCheck> checkDataList = ServiceFactory.getYearCheckService().getCheckData(checkTableName);
//        long checkId = checkDataList.get(0).getId();
//        YearCheckResult yearCheckResult = new YearCheckResult();
//        String isPass1 = "否";
//        String imageUrl1 = "imgs/xxxwqeweddddddddddddd";
//        String description1 = "yyyyyyyyyyyyyyyyyyyyyyyydddddddddddddddddddddddddddd";
//        yearCheckResult.setIsPass(isPass1);
//        yearCheckResult.setImageUrl(imageUrl1);
//        yearCheckResult.setDescription(description1);
//        yearCheckResult.setSystemNumber(number);
//        ServiceFactory.getYearCheckService().insertCheckResultData(yearCheckResult,itemId,checkId,companyName, oilfieldName, platformName, systemName, itemTableName, checkTableName);

//        // 查询检测结果测试
//        // 无itemId
//        String companyName = "辽东作业公司";
//        String oilfieldName = "SZ36-1";
//        String platformName = "SZ36-1D";
//        String systemName = "高压二氧化碳系统灭火系统";
//        String tableName = "管线管件";
//        String number = "SD002";
//        long id = 0;
//        List<YearCheckResult> dataList = ServiceFactory.getYearCheckService().getCheckResultData(id,companyName, oilfieldName, platformName, systemName, tableName, number);

//         有itemId
//        String companyName = "辽东作业公司";
//        String oilfieldName = "SZ36-1";
//        String platformName = "SZ36-1D";
//        String systemName = "高压二氧化碳系统灭火系统";
//        String tableName = "药剂瓶";
//        String number = "SD002";
//        List<ItemInfo> itemDataList = ServiceFactory.getYearCheckService().getItemData(companyName,oilfieldName,platformName,systemName,tableName, number);
//        long itemId = itemDataList.get(0).getId();
//        List<YearCheckResult> dataList = ServiceFactory.getYearCheckService().getCheckResultData(itemId,companyName, oilfieldName, platformName, systemName, tableName, number);

        // 测试公司平台信息查询接口
//        List<CompanyInfo> dataList = ServiceFactory.getCompanyInfoService().getAll();
//        for(int i = 0;i < dataList.size();i++){
//            CompanyInfo result = dataList.get(i);
//            Log.i("CompanyInfo",result.toString());
//        }

        //测试公司接口
//        List<CompanyInfo> dataList = ServiceFactory.getCompanyInfoService().getCompanyList();
//        ServiceFactory.getCompanyInfoService().getOilfieldList("T1");
//        dataList = ServiceFactory.getCompanyInfoService().getPlatformList("CFD11-1");

        // 测试公司增删改
        // 增
//        String companyName = "T1";
//        String oilfieldName = "T1-A";
//        String platformName = "T1-B-C";
//        ServiceFactory.getCompanyInfoService().addData(companyName,oilfieldName,platformName);
//        String oilfieldName = "ZZZ-1";
//        ServiceFactory.getCompanyInfoService().addOilfield(companyName, oilfieldName);
//        String platformName = "ZZZ-1-1";
//        ServiceFactory.getCompanyInfoService().addPlatform(companyName, oilfieldName,platformName);
        // 改
//        String oldName = "T1-B-C";
//        String newName = "T1-B-CC";
//        String type = "platform";
//        ServiceFactory.getCompanyInfoService().rename(oldName,newName,type);
        // 删
//        String name = "T1";
//        String type = "company";
//        ServiceFactory.getCompanyInfoService().deleteData(name,type);
//
//        List<CompanyInfo> dataList = ServiceFactory.getCompanyInfoService().getAll();
//
//                for(int i = 0;i < dataList.size();i++){
//            CompanyInfo result = dataList.get(i);
//            Log.i("CompanyInfo",result.toString());
//        }

        // 测试获取系统列表接口
//        List<CheckType> dataList = ServiceFactory.getYearCheckService().getSystemNameData();
//        for(int i=0;i<dataList.size();i++){
//            Log.i("getSystemData",dataList.get(i).getName());
//            Log.i("getSystemData",dataList.get(i).getId().toString());
//        }
//        List<CheckType> dataList = ServiceFactory.getYearCheckService().gettableNameData(1);
//        List<CheckType> dataList = ServiceFactory.getYearCheckService().gettableNameData(2);
//        for(int i=0;i<dataList.size();i++){
//            Log.i("gettableNameData",dataList.get(i).getName());
//            Log.i("gettableNameData",dataList.get(i).getId().toString());
//        }

//         测试历史数据接口
//        List<HashMap> dataList = ServiceFactory.getYearCheckService().getHistoryList(165,1);
//        for(int i=0;i<dataList.size();i++){
//            Log.i("getHistoryList:::",""+dataList.get(i));
//            HashMap has = dataList.get(i);
//            Log.i("getHistoryList:::",""+ has.get("ret"));
//        }

//        // 测试导出数据接口

//        List<HashMap> allList = ServiceFactory.getYearCheckService().getOutputList();
//        Log.i("getOutputList",""+allList);
////
        // companyInfoId 对应 辽东作业公司 SZ36-1 SZ36-1A--》3
//        long companyInfoId = 3;
//        // checkDate 检查日期
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date checkDate = null;
//        try {
//            checkDate = format.parse("2019-08-03 10:10");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        List<HashMap> retList = ServiceFactory.getYearCheckService().getOutputItemData(companyInfoId,checkDate);
//        for(int i=0;i<retList.size();i++){
//            Log.i("retList:::",""+retList.get(i));
//
//        }


        Toast.makeText(WelcomeActivity.this, "系统将在2秒后为您自动跳转到首页", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 执行在主线程
                // 启动主页面
                startActivity(new Intent(WelcomeActivity.this, NavigationActivity.class));
                //关闭当前页面
                finish();
            }
        }, 2000);
    }
}
