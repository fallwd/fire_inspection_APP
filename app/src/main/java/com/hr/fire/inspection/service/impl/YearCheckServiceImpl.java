package com.hr.fire.inspection.service.impl;

import android.util.Log;

import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.CompanyInfoDao;
import com.hr.fire.inspection.dao.ItemInfoDao;
import com.hr.fire.inspection.dao.YearCheckDao;
import com.hr.fire.inspection.dao.YearCheckResultDao;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.service.YearCheckService;

import org.greenrobot.greendao.query.Join;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class YearCheckServiceImpl extends BaseServiceImpl<Object> implements YearCheckService {

    @Override
    public List<YearCheck> getCheckDataAll() {
        QueryBuilder<YearCheck> queryBuilder = daoSession.queryBuilder(YearCheck.class);
        List<YearCheck> dataList = queryBuilder.list();
        return dataList;
    }

    @Override
    public List<CheckType> getCheckTypeAll() {
        QueryBuilder<CheckType>  queryBuilder = daoSession.queryBuilder(CheckType.class);
        List<CheckType> dataList = queryBuilder.list();
        return dataList;
    }

    @Override
    public List<CheckType> getSystemNameData() {
        QueryBuilder<CheckType> queryBuilder = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.ParentId.eq(0),
                        CheckTypeDao.Properties.Type.eq(1)
                        );
        List<CheckType> dataList = queryBuilder.list();
        return dataList;
    }

    @Override
    public List<CheckType> gettableNameData(long checkTypeId) {
        QueryBuilder<CheckType> queryBuilder = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.ParentId.eq(checkTypeId),
                        CheckTypeDao.Properties.Type.eq(1)
                );
        List<CheckType> dataList = queryBuilder.list();
        return dataList;
    }

    @Override
    public List getHistoryList(long companyId,long systemId) {
        // checkTypeId companyInfoId CHECK_TYPE.PARENT_ID=%s AND
        QueryBuilder<ItemInfo> queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(new WhereCondition.StringCondition(
                        String.format("COMPANY_INFO_ID=%s GROUP BY CHECK_DATE", companyId)));
        Join checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
                where(CheckTypeDao.Properties.ParentId.eq(systemId));
//        Log.i("getHistoryList:::",""+queryBuilder.toString());
        List<ItemInfo> dataList = queryBuilder.list();
        ArrayList resultList = new ArrayList();
        Log.i("getHistoryList:::","查询完成");

        for(int i=0;i<dataList.size();i++){
            ItemInfo ret = dataList.get(i);
            String systemName = ret.getCheckType().getParent().getName();
            String companyName = ret.getCompanyInfo().getCompanyName();
            String oilfieldName = ret.getCompanyInfo().getOilfieldName();
            String platformName = ret.getCompanyInfo().getPlatformName();
            Date checkDate = ret.getCheckDate();
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
            String checkDateStr;
            if(checkDate!=null){
                checkDateStr = formatter.format(checkDate);
            }
            else {
                checkDateStr = "noDate";
            }
            String comboData = companyName + "_" + oilfieldName + "_" + platformName + "_" + systemName + "_" + checkDateStr;
//            Log.i("getHistoryList:::",comboData);

            HashMap obj = new HashMap();
            obj.put("ret",comboData);
            obj.put("companyInfoId",companyId);
            obj.put("systemId",systemId);
            obj.put("checkDate",checkDate);

            resultList.add(obj);
        }
        return resultList;
    }

    @Override
    public List<ItemInfo> getItemData(String companyName, String oilfieldName, String platformName, String systemName, String tableName, String number) {
//    public List<ItemInfo> getItemData(long companyInfoId, long checkTypeId, String number, Date checkDate) {

        // 根据公司名，油田名，平台名,联表查询
        // 旧
        QueryBuilder<ItemInfo> queryBuilder;
        if(number != null && number!=""){
            queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                    where(ItemInfoDao.Properties.SystemNumber.eq(number));
        }
        else {
            queryBuilder = daoSession.queryBuilder(ItemInfo.class);
        }

        Join checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class)
                .where(CheckTypeDao.Properties.Name.eq(tableName));
        Join checkTypeParentJoin = queryBuilder.join(checkTypeJoin, CheckTypeDao.Properties.ParentId, CheckType.class,CheckTypeDao.Properties.Id)
                .where(CheckTypeDao.Properties.Name.eq(systemName));
        Join companyJoin = queryBuilder.join(ItemInfoDao.Properties.CompanyInfoId, CompanyInfo.class)
                .where(
                        CompanyInfoDao.Properties.CompanyName.eq(companyName),
                        CompanyInfoDao.Properties.OilfieldName.eq(oilfieldName),
                        CompanyInfoDao.Properties.PlatformName.eq(platformName)
                );
        // 新
//        QueryBuilder<ItemInfo> queryBuilder = daoSession.queryBuilder(ItemInfo.class).where(
//                ItemInfoDao.Properties.SystemNumber.eq(number),
//                ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
//                ItemInfoDao.Properties.CheckTypeId.eq(checkTypeId),
//                ItemInfoDao.Properties.CheckDate.eq(checkDate)
//        );

                if(number != null && number!=""){
            queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                    where(ItemInfoDao.Properties.SystemNumber.eq(number));
        }
        else {
            queryBuilder = daoSession.queryBuilder(ItemInfo.class);
        }

        List<ItemInfo> dataList = queryBuilder.list();
//        List<ItemInfo> dataList =  queryBuilder.build().list();
        Log.i("info","查询完成01-------------------------------------------");
        for(int i = 0; i < dataList.size(); i++){
            ItemInfo result = dataList.get(i);
            Log.i("result", result.toString());
            Log.i("result", result.getCompanyInfo().toString());
            Log.i("result", result.getCheckType().toString());
            Log.i("result", result.getCheckResultList().toString());
            Log.i("result:getCheckDate", ""+ result.getCheckDate());
        }
        Log.i("info","查询完成01-------------------------------------------");

        return dataList;
    }

    @Override
    public List<ItemInfo> getItemDataEasy(long companyInfoId, long checkTypeId, String number, Date checkDate) {

        // 根据公司名，油田名，平台名,联表查询
        // 旧
//        QueryBuilder<ItemInfo> queryBuilder;
//        if(number != null && number!=""){
//            queryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                    where(ItemInfoDao.Properties.SystemNumber.eq(number));
//        }
//        else {
//            queryBuilder = daoSession.queryBuilder(ItemInfo.class);
//        }

//        Join checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class)
//                .where(CheckTypeDao.Properties.Name.eq(tableName));
//        Join checkTypeParentJoin = queryBuilder.join(checkTypeJoin, CheckTypeDao.Properties.ParentId, CheckType.class,CheckTypeDao.Properties.Id)
//                .where(CheckTypeDao.Properties.Name.eq(systemName));
//        Join companyJoin = queryBuilder.join(ItemInfoDao.Properties.CompanyInfoId, CompanyInfo.class)
//                .where(
//                        CompanyInfoDao.Properties.CompanyName.eq(companyName),
//                        CompanyInfoDao.Properties.OilfieldName.eq(oilfieldName),
//                        CompanyInfoDao.Properties.PlatformName.eq(platformName)
//                );
        // 新
        QueryBuilder<ItemInfo> queryBuilder = daoSession.queryBuilder(ItemInfo.class).where(
                ItemInfoDao.Properties.SystemNumber.eq(number),
                ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
                ItemInfoDao.Properties.CheckTypeId.eq(checkTypeId),
                ItemInfoDao.Properties.CheckDate.eq(checkDate)
        );

//                if(number != null && number!=""){
//            queryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                    where(ItemInfoDao.Properties.SystemNumber.eq(number));
//        }
//        else {
//            queryBuilder = daoSession.queryBuilder(ItemInfo.class);
//        }

        List<ItemInfo> dataList = queryBuilder.list();
//        List<ItemInfo> dataList =  queryBuilder.build().list();
        Log.i("info","查询完成01-------------------------------------------");
        for(int i = 0; i < dataList.size(); i++){
            ItemInfo result = dataList.get(i);
            Log.i("result", result.toString());
            Log.i("result", result.getCompanyInfo().toString());
            Log.i("result", result.getCheckType().toString());
            Log.i("result", result.getCheckResultList().toString());
            Log.i("result:getCheckDate", ""+ result.getCheckDate());
        }
        Log.i("info","查询完成01-------------------------------------------");

        return dataList;
    }

    @Override
    public List<YearCheckResult> getCheckResultData(long itemId,String companyName, String oilfieldName, String platformName, String systemName, String tableName, String number) {
//    public List<YearCheckResult> getCheckResultData(long itemId, long companyInfoId, long checkTypeId, String number, Date checkDate) {
        QueryBuilder<YearCheckResult> queryBuilder;
        List<YearCheckResult> dataList;
        if(itemId!=0) {
            queryBuilder = daoSession.queryBuilder(YearCheckResult.class);
            Join itemInfoJoin = queryBuilder.join(YearCheckResultDao.Properties.ItemInfoId, ItemInfo.class).
                    where(ItemInfoDao.Properties.Id.eq(itemId));

            dataList = queryBuilder.list();
            Log.i("info", "查询完成02-------------------------------------------");
            for (int i = 0; i < dataList.size(); i++) {
                YearCheckResult result = dataList.get(i);
                Log.i("result", result.toString());
                Log.i("result", result.getYearCheck().toString());
            }
            Log.i("info", "查询完成02-------------------------------------------");

        }
        else {

            if(number != null && number!=""){
                queryBuilder = daoSession.queryBuilder(YearCheckResult.class).
                        where(YearCheckResultDao.Properties.SystemNumber.eq(number));
            }
            else {
                queryBuilder = daoSession.queryBuilder(YearCheckResult.class);
            }

            Join checkTypeJoin = queryBuilder.join(YearCheckResultDao.Properties.CheckTypeId, CheckType.class)
                    .where(CheckTypeDao.Properties.Name.eq(tableName));
            Join checkTypeParentJoin = queryBuilder.join(checkTypeJoin, CheckTypeDao.Properties.ParentId, CheckType.class,CheckTypeDao.Properties.Id)
                    .where(CheckTypeDao.Properties.Name.eq(systemName));
            Join companyJoin = queryBuilder.join(YearCheckResultDao.Properties.CompanyInfoId, CompanyInfo.class)
                    .where(
                            CompanyInfoDao.Properties.CompanyName.eq(companyName),
                            CompanyInfoDao.Properties.OilfieldName.eq(oilfieldName),
                            CompanyInfoDao.Properties.PlatformName.eq(platformName)
                    );
//            queryBuilder = daoSession.queryBuilder(YearCheckResult.class).where(
//                    YearCheckResultDao.Properties.SystemNumber.eq(number),
//                    YearCheckResultDao.Properties.CompanyInfoId.eq(companyInfoId),
//                    YearCheckResultDao.Properties.CheckTypeId.eq(checkTypeId),
//                    YearCheckResultDao.Properties.CheckDate.eq(checkDate)
//            );
            dataList = queryBuilder.list();
            Log.i("info", "查询完成02-------------------------------------------");
            for (int i = 0; i < dataList.size(); i++) {
                YearCheckResult result = dataList.get(i);
                Log.i("result", result.toString());
                Log.i("result", result.getYearCheck().toString());
            }
            Log.i("info", "查询完成02-------------------------------------------");
        }
        return dataList;
    }

    @Override
    public List<YearCheckResult> getCheckResultDataEasy(long itemId, long companyInfoId, long checkTypeId, String number, Date checkDate) {
        QueryBuilder<YearCheckResult> queryBuilder;
        List<YearCheckResult> dataList;
        if(itemId!=0) {
            queryBuilder = daoSession.queryBuilder(YearCheckResult.class);
            Join itemInfoJoin = queryBuilder.join(YearCheckResultDao.Properties.ItemInfoId, ItemInfo.class).
                    where(ItemInfoDao.Properties.Id.eq(itemId));

            dataList = queryBuilder.list();
            Log.i("info", "查询完成02-------------------------------------------");
            for (int i = 0; i < dataList.size(); i++) {
                YearCheckResult result = dataList.get(i);
                Log.i("result", result.toString());
                Log.i("result", result.getYearCheck().toString());
            }
            Log.i("info", "查询完成02-------------------------------------------");

        }
        else {

//            if(number != null && number!=""){
//                queryBuilder = daoSession.queryBuilder(YearCheckResult.class).
//                        where(YearCheckResultDao.Properties.SystemNumber.eq(number));
//            }
//            else {
//                queryBuilder = daoSession.queryBuilder(YearCheckResult.class);
//            }
//
//            Join checkTypeJoin = queryBuilder.join(YearCheckResultDao.Properties.CheckTypeId, CheckType.class)
//                    .where(CheckTypeDao.Properties.Name.eq(tableName));
//            Join checkTypeParentJoin = queryBuilder.join(checkTypeJoin, CheckTypeDao.Properties.ParentId, CheckType.class,CheckTypeDao.Properties.Id)
//                    .where(CheckTypeDao.Properties.Name.eq(systemName));
//            Join companyJoin = queryBuilder.join(YearCheckResultDao.Properties.CompanyInfoId, CompanyInfo.class)
//                    .where(
//                            CompanyInfoDao.Properties.CompanyName.eq(companyName),
//                            CompanyInfoDao.Properties.OilfieldName.eq(oilfieldName),
//                            CompanyInfoDao.Properties.PlatformName.eq(platformName)
//                    );
            queryBuilder = daoSession.queryBuilder(YearCheckResult.class).where(
                    YearCheckResultDao.Properties.SystemNumber.eq(number),
                    YearCheckResultDao.Properties.CompanyInfoId.eq(companyInfoId),
                    YearCheckResultDao.Properties.CheckTypeId.eq(checkTypeId),
                    YearCheckResultDao.Properties.CheckDate.eq(checkDate)
            );
            dataList = queryBuilder.list();
            Log.i("info", "查询完成02-------------------------------------------");
            for (int i = 0; i < dataList.size(); i++) {
                YearCheckResult result = dataList.get(i);
                Log.i("result", result.toString());
                Log.i("result", result.getYearCheck().toString());
            }
            Log.i("info", "查询完成02-------------------------------------------");
        }
        return dataList;
    }

    @Override
    public List<YearCheck> getCheckData(String tableName) {
        QueryBuilder<YearCheck> yearCheckQB = daoSession.queryBuilder(YearCheck.class);
        Join yearCheckJoin = yearCheckQB.join(YearCheckDao.Properties.CheckTypeId, CheckType.class).
                where(CheckTypeDao.Properties.Name.eq(tableName));

//        QueryBuilder<YearCheck> yearCheckQB = daoSession.queryBuilder(YearCheck.class).
//                where(YearCheckDao.Properties.CheckTypeId.eq(checkTypeId));
        List<YearCheck> dataList = yearCheckQB.list();
        return dataList;
    }

    @Override
    public List<YearCheck> getCheckDataEasy(long checkTypeId) {
//        QueryBuilder<YearCheck> yearCheckQB = daoSession.queryBuilder(YearCheck.class);
//        Join yearCheckJoin = yearCheckQB.join(YearCheckDao.Properties.CheckTypeId, CheckType.class).
//                where(CheckTypeDao.Properties.Name.eq(tableName));

        QueryBuilder<YearCheck> yearCheckQB = daoSession.queryBuilder(YearCheck.class).
                where(YearCheckDao.Properties.CheckTypeId.eq(checkTypeId));
        List<YearCheck> dataList = yearCheckQB.list();
        return dataList;
    }


    @Override
    public long insertItemData(ItemInfo itemData, String companyName, String oilfieldName, String platformName, String systemName, String tableName, String number) {
//    public long insertItemData(ItemInfo itemData, long companyInfoId,  long checkTypeId, String number, Date checkDate) {

        // 先查询到companyinfo和checktype的对象
        QueryBuilder<CompanyInfo> companyInfoQB = daoSession.queryBuilder(CompanyInfo.class).
                where(
                        CompanyInfoDao.Properties.CompanyName.eq(companyName),
                        CompanyInfoDao.Properties.OilfieldName.eq(oilfieldName),
                        CompanyInfoDao.Properties.PlatformName.eq(platformName)
                );
        CompanyInfo companyInfoObj = companyInfoQB.list().get(0);
        QueryBuilder<CheckType> checkTypeQB = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq(tableName)
                );
        Join checkTypeJoin = checkTypeQB.join(CheckTypeDao.Properties.ParentId, CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq(systemName)
                );
        CheckType checkTypeObj = checkTypeQB.list().get(0);

        itemData.setCompanyInfoId(companyInfoObj.getId());
        itemData.setCheckTypeId(checkTypeObj.getId());

//        itemData.setCompanyInfoId(companyInfoId);
//        itemData.setCheckTypeId(checkTypeId);
        itemData.setSystemNumber(number);
//        itemData.setCheckDate(checkDate);
        daoSession.insert(itemData);

        Log.i("insertItemData", "插入设备信息数据完成-------------------------------------------");

        return 0;
    }

    @Override
//    public long insertItemData(ItemInfo itemData, String companyName, String oilfieldName, String platformName, String systemName, String tableName, String number) {
    public long insertItemDataEasy(ItemInfo itemData, long companyInfoId,  long checkTypeId, String number, Date checkDate) {

        // 先查询到companyinfo和checktype的对象
//        QueryBuilder<CompanyInfo> companyInfoQB = daoSession.queryBuilder(CompanyInfo.class).
//                where(
//                        CompanyInfoDao.Properties.CompanyName.eq(companyName),
//                        CompanyInfoDao.Properties.OilfieldName.eq(oilfieldName),
//                        CompanyInfoDao.Properties.PlatformName.eq(platformName)
//                );
//        CompanyInfo companyInfoObj = companyInfoQB.list().get(0);
//        QueryBuilder<CheckType> checkTypeQB = daoSession.queryBuilder(CheckType.class).
//                where(
//                        CheckTypeDao.Properties.Name.eq(tableName)
//                );
//        Join checkTypeJoin = checkTypeQB.join(CheckTypeDao.Properties.ParentId, CheckType.class).
//                where(
//                        CheckTypeDao.Properties.Name.eq(systemName)
//                );
//        CheckType checkTypeObj = checkTypeQB.list().get(0);
//
//        itemData.setCompanyInfoId(companyInfoObj.getId());
//        itemData.setCheckTypeId(checkTypeObj.getId());

        itemData.setCompanyInfoId(companyInfoId);
        itemData.setCheckTypeId(checkTypeId);
        itemData.setSystemNumber(number);
        itemData.setCheckDate(checkDate);
        daoSession.insert(itemData);

        Log.i("insertItemData", "插入设备信息数据完成-------------------------------------------");

        return 0;
    }

    @Override
    public long insertCheckResultData(YearCheckResult checkResultData, long itemId,long checkId,String companyName, String oilfieldName, String platformName, String systemName, String itemTableName, String checkTableName) {
//    public long insertCheckResultData(YearCheckResult checkResultData, long itemId,long yearCheckId,long companyInfoId, long checkTypeId, String number, Date checkDate) {

        QueryBuilder<CompanyInfo> companyInfoQB = daoSession.queryBuilder(CompanyInfo.class).
                where(
                        CompanyInfoDao.Properties.CompanyName.eq(companyName),
                        CompanyInfoDao.Properties.OilfieldName.eq(oilfieldName),
                        CompanyInfoDao.Properties.PlatformName.eq(platformName)
                );
        CompanyInfo companyInfoObj = companyInfoQB.list().get(0);


        QueryBuilder<CheckType> checkTypeQB;
        if(itemId!=0) {
            // 获取设备对象
            QueryBuilder<ItemInfo> itemInfoQB = daoSession.queryBuilder(ItemInfo.class).
                    where(ItemInfoDao.Properties.Id.eq(itemId));
            ItemInfo itemInfoObj = itemInfoQB.list().get(0);
//            checkResultData.setItemInfo(itemInfoObj);
            checkResultData.setItemInfoId(itemInfoObj.getId());

            checkTypeQB = daoSession.queryBuilder(CheckType.class).
                    where(
                            CheckTypeDao.Properties.Name.eq(checkTableName)
                    );
            Join checkTypeItemJoin = checkTypeQB.join(CheckTypeDao.Properties.ParentId, CheckType.class).
                    where(
                            CheckTypeDao.Properties.Name.eq(itemTableName)
                    );
            Join checkTypesysJoin = checkTypeQB.join(checkTypeItemJoin,CheckTypeDao.Properties.ParentId,CheckType.class,CheckTypeDao.Properties.Id).
                    where(
                            CheckTypeDao.Properties.Name.eq(systemName)
                    );
        }
        else {
            checkTypeQB = daoSession.queryBuilder(CheckType.class).
                    where(
                            CheckTypeDao.Properties.Name.eq(checkTableName)
                    );
            Join checkTypesysJoin = checkTypeQB.join(CheckTypeDao.Properties.ParentId, CheckType.class).
                    where(
                            CheckTypeDao.Properties.Name.eq(systemName)
                    );
        }

        CheckType checkTypeObj = checkTypeQB.list().get(0);
//        checkResultData.setCompanyInfo(companyInfoObj);
        checkResultData.setCompanyInfoId(companyInfoObj.getId());
//        checkResultData.setCheckType(checkTypeObj);
        checkResultData.setCheckTypeId(checkTypeObj.getId());
        // 获取检查表对象
        QueryBuilder<YearCheck> yearCheckQB = daoSession.queryBuilder(YearCheck.class).
                where(YearCheckDao.Properties.Id.eq(checkId));
        YearCheck yearCheckObj = yearCheckQB.list().get(0);
//        checkResultData.setYearCheck(yearCheckObj);
        checkResultData.setYearCheckId(yearCheckObj.getId());
        checkResultData.setTargetId(itemId);
        Log.i("isnert rrrrr",checkResultData.toString());
        daoSession.insert(checkResultData);

//        List<YearCheck> checkDataList = this.getCheckData(tableName);
//        for (int i = 0;i < checkDataList.size();i++){
//            YearCheck checkDataObj = checkDataList.get(i);
//            checkResultData.setYearCheck(checkDataObj);
//            daoSession.insert(checkResultData);
//        }

//        checkResultData.setItemInfoId(itemId);
//        checkResultData.setTargetId(itemId);
//        checkResultData.setYearCheckId(yearCheckId);
//        checkResultData.setSystemNumber(number);
//        checkResultData.setCheckDate(checkDate);
//        checkResultData.setCompanyInfoId(companyInfoId);
//        checkResultData.setCheckTypeId(checkTypeId);

        Log.i("insertCheckResultData","插入检查结果数据完成------------------------------");
        return 0;
    }

    @Override
//    public long insertCheckResultData(YearCheckResult checkResultData, long itemId,long checkId,String companyName, String oilfieldName, String platformName, String systemName, String itemTableName, String checkTableName) {
    public long insertCheckResultDataEasy(YearCheckResult checkResultData, long itemId,long yearCheckId,long companyInfoId, long checkTypeId, String number, Date checkDate) {


        checkResultData.setItemInfoId(itemId);
        checkResultData.setTargetId(itemId);
        checkResultData.setYearCheckId(yearCheckId);
        checkResultData.setSystemNumber(number);
        checkResultData.setCheckDate(checkDate);
        checkResultData.setCompanyInfoId(companyInfoId);
        checkResultData.setCheckTypeId(checkTypeId);
        daoSession.insert(checkResultData);

        Log.i("insertCheckResultData","插入检查结果数据完成------------------------------");
        return 0;
    }

    @Override
    public List getOutputList() {
        QueryBuilder<ItemInfo> queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(new WhereCondition.StringCondition(
                        String.format("l GROUP BY COMPANY_INFO_ID,CHECK_DATE")));
        List<ItemInfo> dataList = queryBuilder.list();
        ArrayList resultList = new ArrayList();
        for(int i=0;i<dataList.size();i++){
            ItemInfo ret = dataList.get(i);
            String companyName = ret.getCompanyInfo().getCompanyName();
            String oilfieldName = ret.getCompanyInfo().getOilfieldName();
            String platformName = ret.getCompanyInfo().getPlatformName();
            Date checkDate = ret.getCheckDate();
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
            String checkDateStr;
            if(checkDate!=null){
                checkDateStr = formatter.format(checkDate);
                String comboData = companyName + "_" + oilfieldName + "_" + platformName + "_"  + checkDateStr;
//            Log.i("getHistoryList:::",comboData);

                HashMap obj = new HashMap();
                obj.put("ret",comboData);
                obj.put("companyInfoId",ret.getCompanyInfoId());
                obj.put("checkDate",checkDate);

                resultList.add(obj);
            }

        }
        return resultList;
    }

    @Override
    public List<ItemInfo> getOutputItemData() {
        return null;
    }
}
