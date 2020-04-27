package com.hr.fire.inspection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.service.ServiceFactory;
import com.hr.fire.inspection.sqlHelpers.BaseData;
import com.hr.fire.inspection.utils.DBManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

//         清空数据库
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

        // 测试插入检查结果
//        ServiceFactory.getYearCheckService().getCheckDataAll();
//        ServiceFactory.getYearCheckService().getCheckTypeAll();
//        // 高压二氧化碳灭火系统 id 1
//        // 管线管件 id 6
//        //
//        YearCheckResult y = new YearCheckResult();
//        y.setIsPass("是");

        ////////////////////////////////////////////////////////////////////////////////////////////


//        String tableName = "药剂瓶检查表";
//        List checkDataList = ServiceFactory.getYearCheckService().getCheckData(tableName);
//        Log.i("getCheckData",checkDataList.get(0).toString());


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
//        long companyInfoId = 165;
//        // checkDate 检查日期
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date checkDate = null;
//        try {
////            checkDate = format.parse("2019-08-03 10:10");
//            checkDate = format.parse("2019-07-03 09:10");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        List<HashMap> retList = ServiceFactory.getYearCheckService().getOutputItemData(companyInfoId,checkDate);
//        for(int i=0;i<retList.size();i++){
//            Log.i("retList:::",""+retList.get(i));
//
//        }

        ///////////////////测试巡检接口///////////////////////////////////////////////
//        List<CheckType> retList = ServiceFactory.getInspectionService().getSystemNameData();
//        for(int i=0;i<retList.size();i++){
//            Log.i("retList:::",""+retList.get(i));
//
//        }
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date checkDate = null;
//        try {
//            checkDate = format.parse("2019-08-03 10:10");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        long companyInfoId = 165;
//        // 灭火器
//        long systemId = 72;
//        List<InspectionResult> retList = ServiceFactory.getInspectionService().getInspectionData(companyInfoId, systemId, checkDate);
//        for(int i=0;i<retList.size();i++){
//            Log.i("retList:::","getInspectionData:::"+retList.get(i));
//
//        }

//        List<HashMap> retList = ServiceFactory.getInspectionService().getHistoryList(companyInfoId, systemId);
//        for(int i=0;i<retList.size();i++){
//            Log.i("retList:::","getInspectionData:::"+retList.get(i));
//
//        }


        ///////////////////测试巡检接口///////////////////////////////////////////////
        // 测试隐患库接口
//        String year = "2019";
//        List<HashMap> retData1 = ServiceFactory.getAnalysisService().getCompanyCountByYearCheck(year);
//        Log.i("retList:::","getCompanyCountByYearCheck:::"+retData1);
//        for(int i=0;i<retData1.size();i++){
//            Log.i("retList:::","getCompanyCountByYearCheck:::"+retData1.get(i));
//
//        }
//        Log.i("retList:::","////////////////////////////////////");
//        List<HashMap> retData2 = ServiceFactory.getAnalysisService().getOilfieldCountByYearCheck(year,"渤西作业公司");
//        Log.i("retList:::","getOilfieldCountByYearCheck:::"+retData2);
        //        for(int i=0;i<retData2.size();i++){
//            Log.i("retList:::","getOilfieldCountByYearCheck:::"+retData2.get(i));
//
//        }
//        Log.i("retList:::","////////////////////////////////////");
//        List<HashMap> retData3 = ServiceFactory.getAnalysisService().getPlatformCountByYearCheck(year,"渤西作业公司","BZ13-1");
//        Log.i("retList:::","getPlatformCountByYearCheck:::"+retData3);
        //        for(int i=0;i<retData3.size();i++){
//            Log.i("retList:::","getPlatformCountByYearCheck:::"+retData3.get(i));
//
//        }
//        Log.i("retList:::","////////////////////////////////////");
//        List<HashMap> retData4 = ServiceFactory.getAnalysisService().getSystemCountByYearCheck(year,"渤西作业公司","BZ13-1","BZ13-1");
//        Log.i("retList:::","getSystemCountByYearCheck:::"+retData4);
        //        for(int i=0;i<retData4.size();i++){
//            Log.i("retList:::","getSystemCountByYearCheck:::"+retData4.get(i));
//
//        }

//        List<HashMap> retDataA = ServiceFactory.getAnalysisService().getCompanyCountByInspection(year);
//        for(int i=0;i<retDataA.size();i++){
//            Log.i("retList:::","getSystemCountByYearCheck:::"+retDataA.get(i));
//
//        }
//        List<HashMap> retDataB = ServiceFactory.getAnalysisService().getOilfieldCountByInspection(year);
//        for(int i=0;i<retDataB.size();i++){
//            Log.i("retList:::","getSystemCountByYearCheck:::"+retDataB.get(i));
//
//        }
//        List<HashMap> retDataC = ServiceFactory.getAnalysisService().getPlatformCountByInspection(year);
//        for(int i=0;i<retDataC.size();i++){
//            Log.i("retList:::","getSystemCountByYearCheck:::"+retDataC.get(i));
//
//        }
//        List<HashMap> retDataD = ServiceFactory.getAnalysisService().getSystemCountByInspection(year);
//        for(int i=0;i<retDataD.size();i++){
//            Log.i("retList:::","getSystemCountByYearCheck:::"+retDataD.get(i));
//
//        }
        //////////////////////////银魂库预览接口///////////////////////////////////////////////
//        String year = "2019";
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date startDate = null;
//        try {
//            startDate = format.parse(year + "-01-01 00:00:00");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Date endDate = null;
//        try {
//            endDate = format.parse(year + "-12-31 00:00:00");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
////        List<HashMap> retData = ServiceFactory.getAnalysisService().getYearCheckView(3,0,startDate,endDate);
////        List<HashMap> retData = ServiceFactory.getAnalysisService().getYearCheckView(165,36,startDate,endDate);
//        List<HashMap> retData = ServiceFactory.getAnalysisService().getYearCheckView(0,0,null,null);
//        for(int i=0;i<retData.size();i++){
//            Log.i("retList:::","getYearCheckView:::"+retData.get(i));
//
//        }
//        long platformId = 165;
//        long systemId = 36;
//        String checkDate = "2019-07-03 09:10:00";
//        String systemNumber = "SD002";
//        String protectArea = "配电间asa";
//
//        List<YearCheckResult> retList = ServiceFactory.getAnalysisService().getYearCheckDetail(platformId,systemId,checkDate,systemNumber,protectArea);
//        for(int i=0;i<retList.size();i++){
//            Log.i("retList:::","getYearCheckDetail:::"+retList.get(i));
//
//        }
            // systemId 72
//                List<HashMap> retData = ServiceFactory.getAnalysisService().getInspectionView(0,0,startDate,endDate);
//        List<HashMap> retData = ServiceFactory.getAnalysisService().getInspectionView(165,72,startDate,endDate);
////        List<HashMap> retData = ServiceFactory.getAnalysisService().getInspectionView(0,0,null,null);
//        for(int i=0;i<retData.size();i++){
//            Log.i("retList:::","getInspectionView:::"+retData.get(i));
//
//        }

        //////////////////////////银魂库预览接口///////////////////////////////////////////////


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
