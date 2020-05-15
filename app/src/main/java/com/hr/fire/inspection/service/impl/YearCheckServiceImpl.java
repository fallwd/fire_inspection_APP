package com.hr.fire.inspection.service.impl;

import android.database.Cursor;
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

import java.text.ParseException;
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
        for(int i=0;i<dataList.size();i++){
            Log.i("getCheckDataAll",dataList.get(i).toString());
        }
        return dataList;
    }

    @Override
    public List<CheckType> getCheckTypeAll() {
        QueryBuilder<CheckType>  queryBuilder = daoSession.queryBuilder(CheckType.class);
        List<CheckType> dataList = queryBuilder.list();
        for(int i=0;i<dataList.size();i++){
            Log.i("getCheckTypeAll",dataList.get(i).toString());
        }
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
    public List<HashMap> getHistoryList(long companyId,long systemId) {
        // checkTypeId companyInfoId CHECK_TYPE.PARENT_ID=%s AND
        QueryBuilder<ItemInfo> queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(new WhereCondition.StringCondition(
//                        String.format("COMPANY_INFO_ID=%s GROUP BY CHECK_DATE", companyId)));
                        String.format("COMPANY_INFO_ID=%s GROUP BY CHECK_DATE,SYSTEM_NUMBER", companyId)));
//        Join checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
//                where(CheckTypeDao.Properties.ParentId.eq(systemId));

//        Log.i("getHistoryList:::",""+queryBuilder.toString());
        List<ItemInfo> dataList = queryBuilder.list();
        ArrayList<HashMap> resultList = new ArrayList();
        Log.i("getHistoryList:::","查询完成");

        for(int i=0;i<dataList.size();i++){
            ItemInfo ret = dataList.get(i);
//            Log.i("getHistoryList:::",ret.toString());
            long DBsystemId = ret.getCheckType().getParent().getId();
            String systemName = ret.getCheckType().getParent().getName();
            String companyName = ret.getCompanyInfo().getCompanyName();
            String oilfieldName = ret.getCompanyInfo().getOilfieldName();
            String platformName = ret.getCompanyInfo().getPlatformName();
            String systemNumber = ret.getSystemNumber();
            Date checkDate = ret.getCheckDate();
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String checkDateStr;
            if(checkDate!=null){
                checkDateStr = formatter.format(checkDate);
            }
            else {
                checkDateStr = "noDate";
            }
            String comboData;
            if(systemNumber !="" && systemNumber != null) {

                comboData = companyName + "_" + oilfieldName + "_" + platformName + "_" + systemName + "_" + systemNumber +"_" + checkDateStr;
//            Log.i("getHistoryList:::",comboData);
            }
            else{
                comboData = companyName + "_" + oilfieldName + "_" + platformName + "_" + systemName + "_" + checkDateStr;
            }
            if(systemId==DBsystemId){
                HashMap obj = new HashMap();
                obj.put("ret",comboData);
                obj.put("companyInfoId",companyId);
                obj.put("systemId",systemId);
                obj.put("checkDate",checkDate);
                obj.put("systemNumber",systemNumber);
                resultList.add(obj);
            }
        }
        return resultList;
    }

    @Override
    public void deleteHistoryData(long companyId, String number, long systemId, Date checkDate) {
        QueryBuilder<ItemInfo> queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CompanyInfoId.eq(companyId),
                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
                        ItemInfoDao.Properties.SystemNumber.eq(number)
                ).orderAsc(ItemInfoDao.Properties.CheckDate);
        Join checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
                where(CheckTypeDao.Properties.ParentId.eq(systemId));
        List<ItemInfo> dataList = queryBuilder.list();
        for(int i = 0;i < dataList.size();i++){
            ItemInfo item = dataList.get(i);
            daoSession.delete(item);
        }
        QueryBuilder<YearCheckResult> queryBuilder2 = daoSession.queryBuilder(YearCheckResult.class).
                where(
                        YearCheckResultDao.Properties.CompanyInfoId.eq(companyId),
                        YearCheckResultDao.Properties.CheckDate.eq(checkDate),
                        YearCheckResultDao.Properties.SystemNumber.eq(number)
                ).orderAsc(YearCheckResultDao.Properties.CheckDate);
        Join checkTypeJoin2 = queryBuilder2.join(YearCheckResultDao.Properties.CheckTypeId, CheckType.class).
                where(CheckTypeDao.Properties.ParentId.eq(systemId));
        List<YearCheckResult> resultDataList = queryBuilder2.list();
        for(int i = 0;i < resultDataList.size();i++){
            YearCheckResult result = resultDataList.get(i);
            daoSession.delete(result);
        }
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
        QueryBuilder<ItemInfo> queryBuilder;
//        if(number!=null && number!=""){
        if(number!=null){
            queryBuilder = daoSession.queryBuilder(ItemInfo.class).where(
                    ItemInfoDao.Properties.SystemNumber.eq(number),
                    ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
                    ItemInfoDao.Properties.CheckTypeId.eq(checkTypeId),
                    ItemInfoDao.Properties.CheckDate.eq(checkDate)
            );
        }
        else {
            queryBuilder = daoSession.queryBuilder(ItemInfo.class).where(
                    ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
                    ItemInfoDao.Properties.CheckTypeId.eq(checkTypeId),
                    ItemInfoDao.Properties.CheckDate.eq(checkDate)
            );
        }

        List<ItemInfo> dataList = queryBuilder.list();
//        List<ItemInfo> dataList =  queryBuilder.build().list();
//        Log.i("info","查询完成01-------------------------------------------");
//        for(int i = 0; i < dataList.size(); i++){
//            ItemInfo result = dataList.get(i);
//            Log.i("result", result.toString());
//            Log.i("result", result.getCompanyInfo().toString());
//            Log.i("result", result.getCheckType().toString());
//            Log.i("result", result.getCheckResultList().toString());
//            Log.i("result:getCheckDate", ""+ result.getCheckDate());
//        }
//        Log.i("info","查询完成01-------------------------------------------");

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
//            Log.i("info", "查询完成02-------------------------------------------");
//            for (int i = 0; i < dataList.size(); i++) {
//                YearCheckResult result = dataList.get(i);
//                Log.i("result", result.toString());
//                Log.i("result", result.getYearCheck().toString());
//            }
//            Log.i("info", "查询完成02-------------------------------------------");
        }
        else {
//            if(number != null && number!=""){
            if(number != null){
                queryBuilder = daoSession.queryBuilder(YearCheckResult.class).where(
                        YearCheckResultDao.Properties.SystemNumber.eq(number),
                        YearCheckResultDao.Properties.CompanyInfoId.eq(companyInfoId),
                        YearCheckResultDao.Properties.CheckTypeId.eq(checkTypeId),
                        YearCheckResultDao.Properties.CheckDate.eq(checkDate)
                );
            }
            else {
                queryBuilder = daoSession.queryBuilder(YearCheckResult.class).where(
                        YearCheckResultDao.Properties.CompanyInfoId.eq(companyInfoId),
                        YearCheckResultDao.Properties.CheckTypeId.eq(checkTypeId),
                        YearCheckResultDao.Properties.CheckDate.eq(checkDate)
                );
            }
            dataList = queryBuilder.list();
            Log.i("info", "查询完成02-------------------------------------------");
            for (int i = 0; i < dataList.size(); i++) {
                YearCheckResult result = dataList.get(i);
                Log.i("result", result.toString());
                Log.i(
                        "result", result.getYearCheck().toString());
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

//        Log.i("insertItemData", "插入设备信息数据完成-------------------------------------------");

        return 0;
    }

    @Override
//    public long insertItemData(ItemInfo itemData, String companyName, String oilfieldName, String platformName, String systemName, String tableName, String number) {
    public long insertItemDataEasy(ItemInfo itemData, long companyInfoId,  long checkTypeId, String number, Date checkDate) {

        itemData.setCompanyInfoId(companyInfoId);
        itemData.setCheckTypeId(checkTypeId);
        itemData.setSystemNumber(number);
        itemData.setCheckDate(checkDate);
        daoSession.insert(itemData);

//        Log.i("insertItemData", "插入设备信息数据完成-------------------------------------------");

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

//        Log.i("insertCheckResultData","插入检查结果数据完成------------------------------");
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

//        Log.i("insertCheckResultData","插入检查结果数据完成------------------------------");
        return 0;
    }

    @Override
    public List<HashMap> getOutputList() {
        /*
        * new WhereCondition.StringCondition("1 GROUP BY COMPANY_NAME" )
        * */
        String companyData;
        QueryBuilder<ItemInfo> queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(new WhereCondition.StringCondition(
//                        String.format("l GROUP BY COMPANY_INFO_ID,CHECK_DATE")));
                        "1 GROUP BY COMPANY_INFO_ID,CHECK_DATE"));
//                        "1 GROUP BY COMPANY_INFO_ID,DATE_FORMAT(CHECK_DATE,'%Y')"));
        List<ItemInfo> dataList = queryBuilder.list();
        ArrayList<HashMap> resultList = new ArrayList();
        HashMap<String,Date> keyList = new HashMap();
        for(int i=0;i<dataList.size();i++){
            ItemInfo ret = dataList.get(i);
            String companyName = ret.getCompanyInfo().getCompanyName();
            String oilfieldName = ret.getCompanyInfo().getOilfieldName();
            String platformName = ret.getCompanyInfo().getPlatformName();
            Date checkDate = ret.getCheckDate();
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
            SimpleDateFormat formatterYear = new SimpleDateFormat("yyyy");
            String checkDateStr;
            String checkDateStrYear;
            if(checkDate!=null){
                checkDateStr = formatter.format(checkDate);
                checkDateStrYear = formatterYear.format(checkDate);
//                String companyData = companyName + "_" + oilfieldName + "_" + platformName;
                companyData = companyName + "_" + oilfieldName + "_" + platformName;
                if(keyList.get(companyData)==null){
                    keyList.put(companyData, checkDate);
                }
                else {
                    Long time2 = keyList.get(companyData).getTime();
                    Long time1 = checkDate.getTime();
                    if(time1 < time2){
                        keyList.put(companyData, checkDate);
                    }
                }
//                Log.i("ttttttttt",""+keyList);
                String comboData = companyName + "_" + oilfieldName + "_" + platformName + "_"  + checkDateStr;
                HashMap obj = new HashMap();
                obj.put("ret",comboData);
                obj.put("companyInfoId",ret.getCompanyInfoId());
                obj.put("checkDate",checkDate);
                obj.put("company",companyData);
                obj.put("year",checkDateStrYear);

                resultList.add(obj);
            }

        }

        ArrayList cutResultList = new ArrayList();
//        Log.i("ttttttttt",""+keyList);
        for(int i=0;i<resultList.size();i++){
            HashMap tmp = resultList.get(i);
            companyData = String.valueOf(tmp.get("company"));
            String checkDateString = String.valueOf(tmp.get("checkDate"));
//            Log.i("ttttttttt","checkDateString"+checkDateString);
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//            Date checkDate = null;
//            try {
//                checkDate = format.parse(checkDateString);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            Log.i("ttttttttt","checkDate"+checkDate);
//            Log.i("ttttttttt","checkDateString2"+String.valueOf(keyList.get(companyData)));
            if(String.valueOf(keyList.get(companyData)).equals(checkDateString)){
                cutResultList.add(tmp);
            }

        }
        return cutResultList;
    }

    public HashMap getResultBySystem(long companyInfoId, Date startDate, Date endDate, String systemName, String tableName) {
        HashMap systemMap;
        QueryBuilder<CheckType> checkTypeQueryBuilder = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.Name.eq(systemName),
                        CheckTypeDao.Properties.Type.eq(1)
                );
        long systemId = checkTypeQueryBuilder.list().get(0).getId();
        // 获取表id
        QueryBuilder<CheckType> tableQueryBuilder = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.ParentId.eq(systemId),
                        CheckTypeDao.Properties.Type.eq(1),
                        CheckTypeDao.Properties.Name.eq(tableName)
                );
        long checkTypeId = tableQueryBuilder.list().get(0).getId();
        QueryBuilder<ItemInfo> queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
                        ItemInfoDao.Properties.CheckDate.ge(startDate),
                        ItemInfoDao.Properties.CheckDate.le(endDate),
                        ItemInfoDao.Properties.CheckTypeId.eq(checkTypeId)
                ).orderAsc(ItemInfoDao.Properties.CheckDate);
//        Join checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
//                        where(CheckTypeDao.Properties.ParentId.eq(systemId));
        List<ItemInfo> dataList = queryBuilder.list();
//        String weight;
//        if(dataList.size() > 0){
//            weight = dataList.get(0).getWeight();
//        }
//        else {
//            weight = "0";
//        }
        Date checkDate;
        if(dataList.size() > 0){
            checkDate = dataList.get(0).getCheckDate();
        }
        else {
            checkDate = null;
        }
        systemMap = new HashMap();
        systemMap.put("systemName",systemName);
        systemMap.put("tableName",tableName);
        systemMap.put("data",dataList);
        systemMap.put("checkDate",checkDate);
//        systemMap.put("count",dataList.size());
//        systemMap.put("weight",weight);
        // 获取区域和位号
//        String dateString = "2019-08-03 10:10";
        QueryBuilder<ItemInfo> secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.ge(startDate),
                        ItemInfoDao.Properties.CheckDate.le(endDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s AND CHECK_TYPE_ID=%s GROUP BY SYSTEM_NUMBER", companyInfoId,checkTypeId))
                );
        List<ItemInfo> secondDataList = secondQueryBuilder.list();
        String systemNumberCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","系统位号------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String systemNumber = ret.getSystemNumber();
                if(systemNumberCombo!=""){
                    systemNumberCombo = systemNumberCombo + "、" + systemNumber;
                }
                else {
                    systemNumberCombo = systemNumberCombo + systemNumber;
                }
            }
        }
        systemMap.put("systemNumber",systemNumberCombo);
        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.ge(startDate),
                        ItemInfoDao.Properties.CheckDate.le(endDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s AND CHECK_TYPE_ID=%s GROUP BY PROTECT_AREA", companyInfoId,checkTypeId))
                );
        secondDataList = secondQueryBuilder.list();
        String protectAreaCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","保护区域------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String protectArea = ret.getProtectArea();
                if(protectAreaCombo!=""){
                    protectAreaCombo = protectAreaCombo + "、" + protectArea;
                }
                else {
                    protectAreaCombo = protectAreaCombo + protectArea;
                }
            }
        }
        systemMap.put("protectArea",protectAreaCombo);
        Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT COUNT(%s) AS C,%s FROM %s WHERE COMPANY_INFO_ID=%s AND CHECK_TYPE_ID=%s AND CAST(CHECK_DATE AS INT)>%s AND CAST(CHECK_DATE AS INT)<%s GROUP BY VOLUME",

                ItemInfoDao.Properties.Volume.columnName,
                ItemInfoDao.Properties.Volume.columnName,
                ItemInfoDao.TABLENAME,
                companyInfoId,
                checkTypeId,
                startDate.getTime(),
                endDate.getTime()
        ), new String []{});
        String count = "";
        while (cursor.moveToNext()) {
            String c = cursor.getString(cursor.getColumnIndex("C"));
            String volume = cursor.getString(cursor.getColumnIndex("VOLUME"));
            if(count!=""){
                count = count + "," + c + "瓶x" + volume + "L";
            }
            else {
                count = count + c + "瓶x" + volume + "L";
            }
        }
        systemMap.put("count",count);

        return systemMap;

    }

    @Override
//    public List<HashMap> getOutputItemData(long companyInfoId, String year) {
    public List<HashMap> getOutputItemData(long companyInfoId, Date checkDate) {
        // Join checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
        //                where(CheckTypeDao.Properties.ParentId.eq(systemId));
//        String year = "2020";
        String year = "2019";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = null;
        try {
            startDate = format.parse(year + "-01-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date endDate = null;
        try {
            endDate = format.parse(year + "-12-31 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        HashMap systemMap;
        ArrayList<HashMap> retList = new ArrayList();
        String systemName = "高压二氧化碳灭火系统";
        String tableName = "药剂瓶";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "高压二氧化碳灭火系统";
        tableName = "氮气瓶";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "七氟丙烷灭火系统";
        tableName = "七氟丙烷钢瓶";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "七氟丙烷灭火系统";
        tableName = "氮气驱动瓶";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "灭火器";
        tableName = "灭火器";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "火灾自动报警系统";
        tableName = "感烟探测器";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "火灾自动报警系统";
        tableName = "感温探测器";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "火灾自动报警系统";
        tableName = "火焰探测器";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "火灾自动报警系统";
        tableName = "手动报警按钮";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "火灾自动报警系统";
        tableName = "可燃气体探测器";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "火灾自动报警系统";
        tableName = "氢气探测器";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "火灾自动报警系统";
        tableName = "硫化氢探测器";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "火灾自动报警系统";
        tableName = "CO探测器";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "火灾自动报警系统";
        tableName = "火灾报警控制器";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "厨房设备灭火装置";
        tableName = "药剂瓶";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "厨房设备灭火装置";
        tableName = "驱动瓶";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        // 专门处理
        systemName = "海水雨淋灭火系统";
        tableName = "";
        //        // 获取对应id
        QueryBuilder<CheckType> checkTypeQueryBuilder = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq(systemName));
        long systemId = checkTypeQueryBuilder.list().get(0).getId();

        QueryBuilder<YearCheckResult> queryBuilder = daoSession.queryBuilder(YearCheckResult.class).
                where(
                        YearCheckResultDao.Properties.CompanyInfoId.eq(companyInfoId),
                        YearCheckResultDao.Properties.CheckDate.ge(startDate),
                        YearCheckResultDao.Properties.CheckDate.le(endDate)
                );
        Join checkTypeJoin = queryBuilder.join(YearCheckResultDao.Properties.CheckTypeId, CheckType.class).
                        where(CheckTypeDao.Properties.ParentId.eq(systemId));
        List<YearCheckResult> dataList = queryBuilder.list();
        systemMap = new HashMap();
        systemMap.put("systemName",systemName);
        systemMap.put("tableName",tableName);
        systemMap.put("data",new ArrayList());
        systemMap.put("count",0);
        systemMap.put("weight","0");
        // 获取区域和位号
//        String dateString = "2019-08-03 10:10";
        QueryBuilder<YearCheckResult> secondQueryBuilder = daoSession.queryBuilder(YearCheckResult.class).
                where(
                        YearCheckResultDao.Properties.CheckDate.ge(startDate),
                        YearCheckResultDao.Properties.CheckDate.le(endDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY SYSTEM_NUMBER", companyInfoId))
                );
        List<YearCheckResult> secondDataList = secondQueryBuilder.list();
        String systemNumberCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            YearCheckResult ret = secondDataList.get(i);
//            Log.i("tang","系统位号------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String systemNumber = ret.getSystemNumber();
                if(systemNumberCombo!=""){
                    systemNumberCombo = systemNumberCombo + "、" + systemNumber;
                }
                else {
                    systemNumberCombo = systemNumberCombo + systemNumber;
                }
            }
        }
        systemMap.put("systemNumber",systemNumberCombo);
        secondQueryBuilder = daoSession.queryBuilder(YearCheckResult.class).
                where(
                        YearCheckResultDao.Properties.CheckDate.ge(startDate),
                        YearCheckResultDao.Properties.CheckDate.le(endDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY PROTECT_AREA", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        String protectAreaCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            YearCheckResult ret = secondDataList.get(i);
//            Log.i("tang","保护区域------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String protectArea = ret.getProtectArea();
                if(protectAreaCombo!=""){
                    protectAreaCombo = protectAreaCombo + "、" + protectArea;
                }
                else {
                    protectAreaCombo = protectAreaCombo + protectArea;
                }
            }
        }
        systemMap.put("protectArea",protectAreaCombo);
        retList.add(systemMap);

        systemName = "消防水灭火系统";
        tableName = "消防软管";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "消防水灭火系统";
        tableName = "消防炮";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "固定式干粉灭火系统";
        tableName = "干粉罐";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "固定式干粉灭火系统";
        tableName = "启动瓶";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "消防员装备";
        tableName = "SCBA气瓶";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        systemName = "消防员装备";
        tableName = "EEBD气瓶";
        systemMap = this.getResultBySystem(companyInfoId, startDate,endDate, systemName, tableName);
        retList.add(systemMap);

        // 泡沫灭火系统
        systemName = "泡沫灭火系统";
        tableName = "";
        //        // 获取对应id
        checkTypeQueryBuilder = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq(systemName));
        systemId = checkTypeQueryBuilder.list().get(0).getId();
        queryBuilder = daoSession.queryBuilder(YearCheckResult.class).
                where(
                        YearCheckResultDao.Properties.CompanyInfoId.eq(companyInfoId),
                        YearCheckResultDao.Properties.CheckDate.ge(startDate),
                        YearCheckResultDao.Properties.CheckDate.le(endDate)
                );
        checkTypeJoin = queryBuilder.join(YearCheckResultDao.Properties.CheckTypeId, CheckType.class).
                        where(CheckTypeDao.Properties.ParentId.eq(systemId));
        dataList = queryBuilder.list();
        systemMap = new HashMap();
        systemMap.put("systemName",systemName);
        systemMap.put("tableName",tableName);
        systemMap.put("data",new ArrayList());
        systemMap.put("count",0);
        systemMap.put("weight","0");
        // 获取区域和位号
//        String dateString = "2019-08-03 10:10";
        secondQueryBuilder = daoSession.queryBuilder(YearCheckResult.class).
                where(
                        YearCheckResultDao.Properties.CheckDate.ge(startDate),
                        YearCheckResultDao.Properties.CheckDate.le(endDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY SYSTEM_NUMBER", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        systemNumberCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            YearCheckResult ret = secondDataList.get(i);
//            Log.i("tang","系统位号------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String systemNumber = ret.getSystemNumber();
                if(systemNumberCombo!=""){
                    systemNumberCombo = systemNumberCombo + "、" + systemNumber;
                }
                else {
                    systemNumberCombo = systemNumberCombo + systemNumber;
                }
            }
        }
        systemMap.put("systemNumber",systemNumberCombo);
        secondQueryBuilder = daoSession.queryBuilder(YearCheckResult.class).
                where(
                        YearCheckResultDao.Properties.CheckDate.ge(startDate),
                        YearCheckResultDao.Properties.CheckDate.le(endDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY PROTECT_AREA", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        protectAreaCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            YearCheckResult ret = secondDataList.get(i);
//            Log.i("tang","保护区域------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String protectArea = ret.getProtectArea();
                if(protectAreaCombo!=""){
                    protectAreaCombo = protectAreaCombo + "、" + protectArea;
                }
                else {
                    protectAreaCombo = protectAreaCombo + protectArea;
                }
            }
        }
        systemMap.put("protectArea",protectAreaCombo);
        retList.add(systemMap);
        return retList;





        // 暂时弃用----------------------------------------------------------------
//        HashMap systemMap;
//        ArrayList<HashMap> retList = new ArrayList();
//        // 获取system信息
////        List<CheckType> systemList = this.getSystemNameData();
//        String systemName = "高压二氧化碳灭火系统";
//        String tableName = "药剂瓶";
//        // 获取对应id
//        QueryBuilder<CheckType> checkTypeQueryBuilder = daoSession.queryBuilder(CheckType.class).
//                where(CheckTypeDao.Properties.Name.eq(systemName));
//        long systemId = checkTypeQueryBuilder.list().get(0).getId();
//        // 获取表id
//        QueryBuilder<CheckType> tableQueryBuilder = daoSession.queryBuilder(CheckType.class).
//                where(
//                        CheckTypeDao.Properties.ParentId.eq(systemId),
//                        CheckTypeDao.Properties.Type.eq(1),
//                        CheckTypeDao.Properties.Name.eq(tableName)
//                );
//        long checkTypeId = tableQueryBuilder.list().get(0).getId();
//        QueryBuilder<ItemInfo> queryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        ItemInfoDao.Properties.CheckTypeId.eq(checkTypeId)
//                );
////        Join checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
////                        where(CheckTypeDao.Properties.ParentId.eq(systemId));
//        List<ItemInfo> dataList = queryBuilder.list();
//        String weight;
//        if(dataList.size() > 0){
//            weight = dataList.get(0).getWeight();
//        }
//        else {
//            weight = "0";
//        }
//        systemMap = new HashMap();
//        systemMap.put("systemName",systemName);
//        systemMap.put("tableName",tableName);
//        systemMap.put("data",dataList);
//        systemMap.put("count",dataList.size());
//        systemMap.put("weight",weight);
//        // 获取区域和位号
////        String dateString = "2019-08-03 10:10";
//        QueryBuilder<ItemInfo> secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        new WhereCondition.StringCondition(
//                                String.format("COMPANY_INFO_ID=%s GROUP BY SYSTEM_NUMBER", companyInfoId))
//                );
//        List<ItemInfo> secondDataList = secondQueryBuilder.list();
//        String systemNumberCombo = "";
//        for(int i=0;i<secondDataList.size();i++){
//            ItemInfo ret = secondDataList.get(i);
////            Log.i("tang","系统位号------" + ret);
//            long DBsystemId = ret.getCheckType().getParent().getId();
//            if(systemId==DBsystemId){
//                String systemNumber = ret.getSystemNumber();
//                if(systemNumberCombo!=""){
//                    systemNumberCombo = systemNumberCombo + "、" + systemNumber;
//                }
//                else {
//                    systemNumberCombo = systemNumberCombo + systemNumber;
//                }
//            }
//        }
//        systemMap.put("systemNumber",systemNumberCombo);
//        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        new WhereCondition.StringCondition(
//                                String.format("COMPANY_INFO_ID=%s GROUP BY PROTECT_AREA", companyInfoId))
//                );
//        secondDataList = secondQueryBuilder.list();
//        String protectAreaCombo = "";
//        for(int i=0;i<secondDataList.size();i++){
//            ItemInfo ret = secondDataList.get(i);
////            Log.i("tang","保护区域------" + ret);
//            long DBsystemId = ret.getCheckType().getParent().getId();
//            if(systemId==DBsystemId){
//                String protectArea = ret.getProtectArea();
//                if(protectAreaCombo!=""){
//                    protectAreaCombo = protectAreaCombo + "、" + protectArea;
//                }
//                else {
//                    protectAreaCombo = protectAreaCombo + protectArea;
//                }
//            }
//        }
//        systemMap.put("protectArea",protectAreaCombo);
////        // 获取总容积
////        //Sat Aug 03 10:10:00 GMT+00:00 2019 Sat Aug 03 10:10:00 GMT+00:00 2019
////        Cursor cursor = daoSession.getDatabase().rawQuery(String.format("SELECT SUM(%s.%s) FROM %s INNER JOIN %s ON %s.CHECK_TYPE_ID=%s._id WHERE %s.PARENT_ID=%s AND CAST(%s.CHECK_DATE AS TEXT)='%s'",
////                ItemInfoDao.TABLENAME,
////                ItemInfoDao.Properties.GoodsWeight.columnName,
////                ItemInfoDao.TABLENAME,
////                CheckTypeDao.TABLENAME,
////                ItemInfoDao.TABLENAME,
////                CheckTypeDao.TABLENAME,
////                CheckTypeDao.TABLENAME,
////                systemId,
////                ItemInfoDao.TABLENAME,
////                checkDate.getTime()
//////                dateString
////        ), new String []{});
////        cursor.moveToFirst();
//////        Log.i("tang","getOutputItemData:xxx:::" + cursor.getString());
////        long result = cursor.getLong(0);
//////        Log.i("tang","getOutputItemData:xxx:::" + result);
////        systemMap.put("weights",result);
//        retList.add(systemMap);
//
//        systemName = "高压二氧化碳灭火系统";
//        tableName = "氮气瓶";
//        // 获取对应id
//       checkTypeQueryBuilder = daoSession.queryBuilder(CheckType.class).
//                where(CheckTypeDao.Properties.Name.eq(systemName));
//        systemId = checkTypeQueryBuilder.list().get(0).getId();
//        // 获取表id
//        tableQueryBuilder = daoSession.queryBuilder(CheckType.class).
//                where(
//                        CheckTypeDao.Properties.ParentId.eq(systemId),
//                        CheckTypeDao.Properties.Type.eq(1),
//                        CheckTypeDao.Properties.Name.eq(tableName)
//                );
//        checkTypeId = tableQueryBuilder.list().get(0).getId();
//        queryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        ItemInfoDao.Properties.CheckTypeId.eq(checkTypeId)
//                );
////        Join checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
////                        where(CheckTypeDao.Properties.ParentId.eq(systemId));
//       dataList = queryBuilder.list();
//        if(dataList.size() > 0){
//            weight = dataList.get(0).getWeight();
//        }
//        else {
//            weight = "0";
//        }
//        systemMap = new HashMap();
//        systemMap.put("systemName",systemName);
//        systemMap.put("tableName",tableName);
//        systemMap.put("data",dataList);
//        systemMap.put("count",dataList.size());
//        systemMap.put("weight",weight);
//        // 获取区域和位号
////        String dateString = "2019-08-03 10:10";
//        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        new WhereCondition.StringCondition(
//                                String.format("COMPANY_INFO_ID=%s GROUP BY SYSTEM_NUMBER", companyInfoId))
//                );
//        secondDataList = secondQueryBuilder.list();
//        systemNumberCombo = "";
//        for(int i=0;i<secondDataList.size();i++){
//            ItemInfo ret = secondDataList.get(i);
////            Log.i("tang","系统位号------" + ret);
//            long DBsystemId = ret.getCheckType().getParent().getId();
//            if(systemId==DBsystemId){
//                String systemNumber = ret.getSystemNumber();
//                if(systemNumberCombo!=""){
//                    systemNumberCombo = systemNumberCombo + "、" + systemNumber;
//                }
//                else {
//                    systemNumberCombo = systemNumberCombo + systemNumber;
//                }
//            }
//        }
//        systemMap.put("systemNumber",systemNumberCombo);
//        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        new WhereCondition.StringCondition(
//                                String.format("COMPANY_INFO_ID=%s GROUP BY PROTECT_AREA", companyInfoId))
//                );
//        secondDataList = secondQueryBuilder.list();
//        protectAreaCombo = "";
//        for(int i=0;i<secondDataList.size();i++){
//            ItemInfo ret = secondDataList.get(i);
////            Log.i("tang","保护区域------" + ret);
//            long DBsystemId = ret.getCheckType().getParent().getId();
//            if(systemId==DBsystemId){
//                String protectArea = ret.getProtectArea();
//                if(protectAreaCombo!=""){
//                    protectAreaCombo = protectAreaCombo + "、" + protectArea;
//                }
//                else {
//                    protectAreaCombo = protectAreaCombo + protectArea;
//                }
//            }
//        }
//        systemMap.put("protectArea",protectAreaCombo);
//        retList.add(systemMap);
//
//        systemName = "七氟丙烷灭火系统";
//        tableName = "七氟丙烷钢瓶";
//        // 获取对应id
//        checkTypeQueryBuilder = daoSession.queryBuilder(CheckType.class).
//                where(CheckTypeDao.Properties.Name.eq(systemName));
//        systemId = checkTypeQueryBuilder.list().get(0).getId();
//        // 获取表id
//        tableQueryBuilder = daoSession.queryBuilder(CheckType.class).
//                where(
//                        CheckTypeDao.Properties.ParentId.eq(systemId),
//                        CheckTypeDao.Properties.Type.eq(1),
//                        CheckTypeDao.Properties.Name.eq(tableName)
//                );
//        checkTypeId = tableQueryBuilder.list().get(0).getId();
//        queryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        ItemInfoDao.Properties.CheckTypeId.eq(checkTypeId)
//                );
////        Join checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
////                        where(CheckTypeDao.Properties.ParentId.eq(systemId));
//        dataList = queryBuilder.list();
//        if(dataList.size() > 0){
//            weight = dataList.get(0).getWeight();
//        }
//        else {
//            weight = "0";
//        }
//        systemMap = new HashMap();
//        systemMap.put("systemName",systemName);
//        systemMap.put("tableName",tableName);
//        systemMap.put("data",dataList);
//        systemMap.put("count",dataList.size());
//        systemMap.put("weight",weight);
//        // 获取区域和位号
////        String dateString = "2019-08-03 10:10";
//        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        new WhereCondition.StringCondition(
//                                String.format("COMPANY_INFO_ID=%s GROUP BY SYSTEM_NUMBER", companyInfoId))
//                );
//        secondDataList = secondQueryBuilder.list();
//        systemNumberCombo = "";
//        for(int i=0;i<secondDataList.size();i++){
//            ItemInfo ret = secondDataList.get(i);
////            Log.i("tang","系统位号------" + ret);
//            long DBsystemId = ret.getCheckType().getParent().getId();
//            if(systemId==DBsystemId){
//                String systemNumber = ret.getSystemNumber();
//                if(systemNumberCombo!=""){
//                    systemNumberCombo = systemNumberCombo + "、" + systemNumber;
//                }
//                else {
//                    systemNumberCombo = systemNumberCombo + systemNumber;
//                }
//            }
//        }
//        systemMap.put("systemNumber",systemNumberCombo);
//        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        new WhereCondition.StringCondition(
//                                String.format("COMPANY_INFO_ID=%s GROUP BY PROTECT_AREA", companyInfoId))
//                );
//        secondDataList = secondQueryBuilder.list();
//        protectAreaCombo = "";
//        for(int i=0;i<secondDataList.size();i++){
//            ItemInfo ret = secondDataList.get(i);
////            Log.i("tang","保护区域------" + ret);
//            long DBsystemId = ret.getCheckType().getParent().getId();
//            if(systemId==DBsystemId){
//                String protectArea = ret.getProtectArea();
//                if(protectAreaCombo!=""){
//                    protectAreaCombo = protectAreaCombo + "、" + protectArea;
//                }
//                else {
//                    protectAreaCombo = protectAreaCombo + protectArea;
//                }
//            }
//        }
//        systemMap.put("protectArea",protectAreaCombo);
//        retList.add(systemMap);
//
//        systemName = "七氟丙烷灭火系统";
//        tableName = "氮气驱动瓶";
//        // 获取对应id
//        checkTypeQueryBuilder = daoSession.queryBuilder(CheckType.class).
//                where(CheckTypeDao.Properties.Name.eq(systemName));
//        systemId = checkTypeQueryBuilder.list().get(0).getId();
//        // 获取表id
//        tableQueryBuilder = daoSession.queryBuilder(CheckType.class).
//                where(
//                        CheckTypeDao.Properties.ParentId.eq(systemId),
//                        CheckTypeDao.Properties.Type.eq(1),
//                        CheckTypeDao.Properties.Name.eq(tableName)
//                );
//        checkTypeId = tableQueryBuilder.list().get(0).getId();
//        queryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        ItemInfoDao.Properties.CheckTypeId.eq(checkTypeId)
//                );
////        Join checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
////                        where(CheckTypeDao.Properties.ParentId.eq(systemId));
//        dataList = queryBuilder.list();
//        if(dataList.size() > 0){
//            weight = dataList.get(0).getWeight();
//        }
//        else {
//            weight = "0";
//        }
//        systemMap = new HashMap();
//        systemMap.put("systemName",systemName);
//        systemMap.put("tableName",tableName);
//        systemMap.put("data",dataList);
//        systemMap.put("count",dataList.size());
//        systemMap.put("weight",weight);
//        // 获取区域和位号
////        String dateString = "2019-08-03 10:10";
//        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        new WhereCondition.StringCondition(
//                                String.format("COMPANY_INFO_ID=%s GROUP BY SYSTEM_NUMBER", companyInfoId))
//                );
//        secondDataList = secondQueryBuilder.list();
//        systemNumberCombo = "";
//        for(int i=0;i<secondDataList.size();i++){
//            ItemInfo ret = secondDataList.get(i);
////            Log.i("tang","系统位号------" + ret);
//            long DBsystemId = ret.getCheckType().getParent().getId();
//            if(systemId==DBsystemId){
//                String systemNumber = ret.getSystemNumber();
//                if(systemNumberCombo!=""){
//                    systemNumberCombo = systemNumberCombo + "、" + systemNumber;
//                }
//                else {
//                    systemNumberCombo = systemNumberCombo + systemNumber;
//                }
//            }
//        }
//        systemMap.put("systemNumber",systemNumberCombo);
//        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        new WhereCondition.StringCondition(
//                                String.format("COMPANY_INFO_ID=%s GROUP BY PROTECT_AREA", companyInfoId))
//                );
//        secondDataList = secondQueryBuilder.list();
//        protectAreaCombo = "";
//        for(int i=0;i<secondDataList.size();i++){
//            ItemInfo ret = secondDataList.get(i);
////            Log.i("tang","保护区域------" + ret);
//            long DBsystemId = ret.getCheckType().getParent().getId();
//            if(systemId==DBsystemId){
//                String protectArea = ret.getProtectArea();
//                if(protectAreaCombo!=""){
//                    protectAreaCombo = protectAreaCombo + "、" + protectArea;
//                }
//                else {
//                    protectAreaCombo = protectAreaCombo + protectArea;
//                }
//            }
//        }
//        systemMap.put("protectArea",protectAreaCombo);
//        retList.add(systemMap);
//
//        systemName = "灭火器";
//        tableName = "灭火器";
//        // 获取对应id
//        checkTypeQueryBuilder = daoSession.queryBuilder(CheckType.class).
//                where(CheckTypeDao.Properties.Name.eq(systemName));
//        systemId = checkTypeQueryBuilder.list().get(0).getId();
//        // 获取表id
//        tableQueryBuilder = daoSession.queryBuilder(CheckType.class).
//                where(
//                        CheckTypeDao.Properties.ParentId.eq(systemId),
//                        CheckTypeDao.Properties.Type.eq(1),
//                        CheckTypeDao.Properties.Name.eq(tableName)
//                );
//        checkTypeId = tableQueryBuilder.list().get(0).getId();
//        queryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        ItemInfoDao.Properties.CheckTypeId.eq(checkTypeId)
//                );
////        Join checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
////                        where(CheckTypeDao.Properties.ParentId.eq(systemId));
//        dataList = queryBuilder.list();
//        if(dataList.size() > 0){
//            weight = dataList.get(0).getWeight();
//        }
//        else {
//            weight = "0";
//        }
//        systemMap = new HashMap();
//        systemMap.put("systemName",systemName);
//        systemMap.put("tableName",tableName);
//        systemMap.put("data",dataList);
//        systemMap.put("count",dataList.size());
//        systemMap.put("weight",weight);
//        // 获取区域和位号
////        String dateString = "2019-08-03 10:10";
//        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        new WhereCondition.StringCondition(
//                                String.format("COMPANY_INFO_ID=%s GROUP BY SYSTEM_NUMBER", companyInfoId))
//                );
//        secondDataList = secondQueryBuilder.list();
//        systemNumberCombo = "";
//        for(int i=0;i<secondDataList.size();i++){
//            ItemInfo ret = secondDataList.get(i);
////            Log.i("tang","系统位号------" + ret);
//            long DBsystemId = ret.getCheckType().getParent().getId();
//            if(systemId==DBsystemId){
//                String systemNumber = ret.getSystemNumber();
//                if(systemNumberCombo!=""){
//                    systemNumberCombo = systemNumberCombo + "、" + systemNumber;
//                }
//                else {
//                    systemNumberCombo = systemNumberCombo + systemNumber;
//                }
//            }
//        }
//        systemMap.put("systemNumber",systemNumberCombo);
//        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        new WhereCondition.StringCondition(
//                                String.format("COMPANY_INFO_ID=%s GROUP BY PROTECT_AREA", companyInfoId))
//                );
//        secondDataList = secondQueryBuilder.list();
//        protectAreaCombo = "";
//        for(int i=0;i<secondDataList.size();i++){
//            ItemInfo ret = secondDataList.get(i);
////            Log.i("tang","保护区域------" + ret);
//            long DBsystemId = ret.getCheckType().getParent().getId();
//            if(systemId==DBsystemId){
//                String protectArea = ret.getProtectArea();
//                if(protectAreaCombo!=""){
//                    protectAreaCombo = protectAreaCombo + "、" + protectArea;
//                }
//                else {
//                    protectAreaCombo = protectAreaCombo + protectArea;
//                }
//            }
//        }
//        systemMap.put("protectArea",protectAreaCombo);
//        retList.add(systemMap);
//
//        systemName = "灭火器";
//        tableName = "灭火器";
//        // 获取对应id
//        checkTypeQueryBuilder = daoSession.queryBuilder(CheckType.class).
//                where(CheckTypeDao.Properties.Name.eq(systemName));
//        systemId = checkTypeQueryBuilder.list().get(0).getId();
//        // 获取表id
//        tableQueryBuilder = daoSession.queryBuilder(CheckType.class).
//                where(
//                        CheckTypeDao.Properties.ParentId.eq(systemId),
//                        CheckTypeDao.Properties.Type.eq(1),
//                        CheckTypeDao.Properties.Name.eq(tableName)
//                );
//        checkTypeId = tableQueryBuilder.list().get(0).getId();
//        queryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        ItemInfoDao.Properties.CheckTypeId.eq(checkTypeId)
//                );
////        Join checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
////                        where(CheckTypeDao.Properties.ParentId.eq(systemId));
//        dataList = queryBuilder.list();
//        if(dataList.size() > 0){
//            weight = dataList.get(0).getWeight();
//        }
//        else {
//            weight = "0";
//        }
//        systemMap = new HashMap();
//        systemMap.put("systemName",systemName);
//        systemMap.put("tableName",tableName);
//        systemMap.put("data",dataList);
//        systemMap.put("count",dataList.size());
//        systemMap.put("weight",weight);
//        // 获取区域和位号
////        String dateString = "2019-08-03 10:10";
//        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        new WhereCondition.StringCondition(
//                                String.format("COMPANY_INFO_ID=%s GROUP BY SYSTEM_NUMBER", companyInfoId))
//                );
//        secondDataList = secondQueryBuilder.list();
//        systemNumberCombo = "";
//        for(int i=0;i<secondDataList.size();i++){
//            ItemInfo ret = secondDataList.get(i);
////            Log.i("tang","系统位号------" + ret);
//            long DBsystemId = ret.getCheckType().getParent().getId();
//            if(systemId==DBsystemId){
//                String systemNumber = ret.getSystemNumber();
//                if(systemNumberCombo!=""){
//                    systemNumberCombo = systemNumberCombo + "、" + systemNumber;
//                }
//                else {
//                    systemNumberCombo = systemNumberCombo + systemNumber;
//                }
//            }
//        }
//        systemMap.put("systemNumber",systemNumberCombo);
//        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
//                where(
//                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
//                        new WhereCondition.StringCondition(
//                                String.format("COMPANY_INFO_ID=%s GROUP BY PROTECT_AREA", companyInfoId))
//                );
//        secondDataList = secondQueryBuilder.list();
//        protectAreaCombo = "";
//        for(int i=0;i<secondDataList.size();i++){
//            ItemInfo ret = secondDataList.get(i);
////            Log.i("tang","保护区域------" + ret);
//            long DBsystemId = ret.getCheckType().getParent().getId();
//            if(systemId==DBsystemId){
//                String protectArea = ret.getProtectArea();
//                if(protectAreaCombo!=""){
//                    protectAreaCombo = protectAreaCombo + "、" + protectArea;
//                }
//                else {
//                    protectAreaCombo = protectAreaCombo + protectArea;
//                }
//            }
//        }
//        systemMap.put("protectArea",protectAreaCombo);
//        retList.add(systemMap);
//
//        return retList;

        /*
        // 22222222
        systemName = "七氟丙烷灭火系统";
        // 获取对应id
        checkTypeQueryBuilder = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq(systemName));
        systemId = checkTypeQueryBuilder.list().get(0).getId();
        queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
                        ItemInfoDao.Properties.CheckDate.eq(checkDate)
                );
        checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
                where(CheckTypeDao.Properties.ParentId.eq(systemId));
        dataList = queryBuilder.list();
        systemMap = new HashMap();
        systemMap.put("systemName",systemName);
        systemMap.put("data",dataList);
        systemMap.put("count",dataList.size());
        // 获取区域和位号
        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY SYSTEM_NUMBER", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        systemNumberCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","系统位号------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String systemNumber = ret.getSystemNumber();
                if(systemNumberCombo!=""){
                    systemNumberCombo = systemNumberCombo + "、" + systemNumber;
                }
                else {
                    systemNumberCombo = systemNumberCombo + systemNumber;
                }
            }
        }
        systemMap.put("systemNumber",systemNumberCombo);
        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY PROTECT_AREA", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        protectAreaCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","保护区域------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String protectArea = ret.getProtectArea();
                if(protectAreaCombo!=""){
                    protectAreaCombo = protectAreaCombo + "、" + protectArea;
                }
                else {
                    protectAreaCombo = protectAreaCombo + protectArea;
                }
            }
        }
        systemMap.put("protectArea",protectAreaCombo);
        // 获取总容积
        cursor = daoSession.getDatabase().rawQuery(String.format("SELECT SUM(%s.%s) FROM %s INNER JOIN %s ON %s.CHECK_TYPE_ID=%s._id WHERE %s.PARENT_ID=%s AND CAST(%s.CHECK_DATE AS TEXT)='%s'",
                ItemInfoDao.TABLENAME,
                ItemInfoDao.Properties.GoodsWeight.columnName,
                ItemInfoDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                ItemInfoDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                systemId,
                ItemInfoDao.TABLENAME,
                checkDate.getTime()
        ), new String []{});
        cursor.moveToFirst();
        result = cursor.getLong(0);
        systemMap.put("weights",result);
        retList.add(systemMap);

        // 3333333333333333
        systemName = "灭火器";
        // 获取对应id
        checkTypeQueryBuilder = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq(systemName));
        systemId = checkTypeQueryBuilder.list().get(0).getId();
        queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
                        ItemInfoDao.Properties.CheckDate.eq(checkDate)
                );
        checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
                where(CheckTypeDao.Properties.ParentId.eq(systemId));
        dataList = queryBuilder.list();
        systemMap = new HashMap();
        systemMap.put("systemName",systemName);
        systemMap.put("data",dataList);
        systemMap.put("count",dataList.size());
        // 获取区域和位号
        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY SYSTEM_NUMBER", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        systemNumberCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","系统位号------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String systemNumber = ret.getSystemNumber();
                if(systemNumberCombo!=""){
                    systemNumberCombo = systemNumberCombo + "、" + systemNumber;
                }
                else {
                    systemNumberCombo = systemNumberCombo + systemNumber;
                }
            }
        }
        systemMap.put("systemNumber",systemNumberCombo);
        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY PROTECT_AREA", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        protectAreaCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","保护区域------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String protectArea = ret.getProtectArea();
                if(protectAreaCombo!=""){
                    protectAreaCombo = protectAreaCombo + "、" + protectArea;
                }
                else {
                    protectAreaCombo = protectAreaCombo + protectArea;
                }
            }
        }
        systemMap.put("protectArea",protectAreaCombo);
        // 获取总容积
        cursor = daoSession.getDatabase().rawQuery(String.format("SELECT SUM(%s.%s) FROM %s INNER JOIN %s ON %s.CHECK_TYPE_ID=%s._id WHERE %s.PARENT_ID=%s AND CAST(%s.CHECK_DATE AS TEXT)='%s'",
                ItemInfoDao.TABLENAME,
                ItemInfoDao.Properties.GoodsWeight.columnName,
                ItemInfoDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                ItemInfoDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                systemId,
                ItemInfoDao.TABLENAME,
                checkDate.getTime()
        ), new String []{});
        cursor.moveToFirst();
        result = cursor.getLong(0);
        systemMap.put("weights",result);
        retList.add(systemMap);

        //// 4444444444444444444444444444
        systemName = "火灾自动报警系统";
        // 获取对应id
        checkTypeQueryBuilder = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq(systemName));
        systemId = checkTypeQueryBuilder.list().get(0).getId();
        queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
                        ItemInfoDao.Properties.CheckDate.eq(checkDate)
                );
        checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
                where(CheckTypeDao.Properties.ParentId.eq(systemId));
        dataList = queryBuilder.list();
        systemMap = new HashMap();
        systemMap.put("systemName",systemName);
        systemMap.put("data",dataList);
        systemMap.put("count",dataList.size());
        // 获取区域和位号
        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY SYSTEM_NUMBER", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        systemNumberCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","系统位号------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String systemNumber = ret.getSystemNumber();
                if(systemNumberCombo!=""){
                    systemNumberCombo = systemNumberCombo + "、" + systemNumber;
                }
                else {
                    systemNumberCombo = systemNumberCombo + systemNumber;
                }
            }
        }
        systemMap.put("systemNumber",systemNumberCombo);
        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY PROTECT_AREA", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        protectAreaCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","保护区域------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String protectArea = ret.getProtectArea();
                if(protectAreaCombo!=""){
                    protectAreaCombo = protectAreaCombo + "、" + protectArea;
                }
                else {
                    protectAreaCombo = protectAreaCombo + protectArea;
                }
            }
        }
        systemMap.put("protectArea",protectAreaCombo);
        // 获取总容积
        cursor = daoSession.getDatabase().rawQuery(String.format("SELECT SUM(%s.%s) FROM %s INNER JOIN %s ON %s.CHECK_TYPE_ID=%s._id WHERE %s.PARENT_ID=%s AND CAST(%s.CHECK_DATE AS TEXT)='%s'",
                ItemInfoDao.TABLENAME,
                ItemInfoDao.Properties.GoodsWeight.columnName,
                ItemInfoDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                ItemInfoDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                systemId,
                ItemInfoDao.TABLENAME,
                checkDate.getTime()
        ), new String []{});
        cursor.moveToFirst();
        result = cursor.getLong(0);
        systemMap.put("weights",result);
        retList.add(systemMap);

        // 5555555555555555555555555
        systemName = "厨房设备灭火装置";
        // 获取对应id
        checkTypeQueryBuilder = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq(systemName));
        systemId = checkTypeQueryBuilder.list().get(0).getId();
        queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
                        ItemInfoDao.Properties.CheckDate.eq(checkDate)
                );
        checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
                where(CheckTypeDao.Properties.ParentId.eq(systemId));
        dataList = queryBuilder.list();
        systemMap = new HashMap();
        systemMap.put("systemName",systemName);
        systemMap.put("data",dataList);
        systemMap.put("count",dataList.size());
        // 获取区域和位号
        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY SYSTEM_NUMBER", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        systemNumberCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","系统位号------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String systemNumber = ret.getSystemNumber();
                if(systemNumberCombo!=""){
                    systemNumberCombo = systemNumberCombo + "、" + systemNumber;
                }
                else {
                    systemNumberCombo = systemNumberCombo + systemNumber;
                }
            }
        }
        systemMap.put("systemNumber",systemNumberCombo);
        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY PROTECT_AREA", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        protectAreaCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","保护区域------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String protectArea = ret.getProtectArea();
                if(protectAreaCombo!=""){
                    protectAreaCombo = protectAreaCombo + "、" + protectArea;
                }
                else {
                    protectAreaCombo = protectAreaCombo + protectArea;
                }
            }
        }
        systemMap.put("protectArea",protectAreaCombo);
        // 获取总容积
        cursor = daoSession.getDatabase().rawQuery(String.format("SELECT SUM(%s.%s) FROM %s INNER JOIN %s ON %s.CHECK_TYPE_ID=%s._id WHERE %s.PARENT_ID=%s AND CAST(%s.CHECK_DATE AS TEXT)='%s'",
                ItemInfoDao.TABLENAME,
                ItemInfoDao.Properties.GoodsWeight.columnName,
                ItemInfoDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                ItemInfoDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                systemId,
                ItemInfoDao.TABLENAME,
                checkDate.getTime()
        ), new String []{});
        cursor.moveToFirst();
        result = cursor.getLong(0);
        systemMap.put("weights",result);
        retList.add(systemMap);

        /// 6666666666666666666666
        systemName = "海水雨淋灭火系统";
        // 获取对应id
        checkTypeQueryBuilder = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq(systemName));
        systemId = checkTypeQueryBuilder.list().get(0).getId();
        queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
                        ItemInfoDao.Properties.CheckDate.eq(checkDate)
                );
        checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
                where(CheckTypeDao.Properties.ParentId.eq(systemId));
        dataList = queryBuilder.list();
        systemMap = new HashMap();
        systemMap.put("systemName",systemName);
        systemMap.put("data",dataList);
        systemMap.put("count",dataList.size());
        // 获取区域和位号
        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY SYSTEM_NUMBER", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        systemNumberCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","系统位号------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String systemNumber = ret.getSystemNumber();
                if(systemNumberCombo!=""){
                    systemNumberCombo = systemNumberCombo + "、" + systemNumber;
                }
                else {
                    systemNumberCombo = systemNumberCombo + systemNumber;
                }
            }
        }
        systemMap.put("systemNumber",systemNumberCombo);
        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY PROTECT_AREA", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        protectAreaCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","保护区域------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String protectArea = ret.getProtectArea();
                if(protectAreaCombo!=""){
                    protectAreaCombo = protectAreaCombo + "、" + protectArea;
                }
                else {
                    protectAreaCombo = protectAreaCombo + protectArea;
                }
            }
        }
        systemMap.put("protectArea",protectAreaCombo);
        // 获取总容积
        cursor = daoSession.getDatabase().rawQuery(String.format("SELECT SUM(%s.%s) FROM %s INNER JOIN %s ON %s.CHECK_TYPE_ID=%s._id WHERE %s.PARENT_ID=%s AND CAST(%s.CHECK_DATE AS TEXT)='%s'",
                ItemInfoDao.TABLENAME,
                ItemInfoDao.Properties.GoodsWeight.columnName,
                ItemInfoDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                ItemInfoDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                systemId,
                ItemInfoDao.TABLENAME,
                checkDate.getTime()
        ), new String []{});
        cursor.moveToFirst();
        result = cursor.getLong(0);
        systemMap.put("weights",result);
        retList.add(systemMap);

        // 7777777777777777777777777777
        systemName = "消防水灭火系统";
        // 获取对应id
        checkTypeQueryBuilder = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq(systemName));
        systemId = checkTypeQueryBuilder.list().get(0).getId();
        queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
                        ItemInfoDao.Properties.CheckDate.eq(checkDate)
                );
        checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
                where(CheckTypeDao.Properties.ParentId.eq(systemId));
        dataList = queryBuilder.list();
        systemMap = new HashMap();
        systemMap.put("systemName",systemName);
        systemMap.put("data",dataList);
        systemMap.put("count",dataList.size());
        // 获取区域和位号
        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY SYSTEM_NUMBER", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        systemNumberCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","系统位号------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String systemNumber = ret.getSystemNumber();
                if(systemNumberCombo!=""){
                    systemNumberCombo = systemNumberCombo + "、" + systemNumber;
                }
                else {
                    systemNumberCombo = systemNumberCombo + systemNumber;
                }
            }
        }
        systemMap.put("systemNumber",systemNumberCombo);
        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY PROTECT_AREA", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        protectAreaCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","保护区域------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String protectArea = ret.getProtectArea();
                if(protectAreaCombo!=""){
                    protectAreaCombo = protectAreaCombo + "、" + protectArea;
                }
                else {
                    protectAreaCombo = protectAreaCombo + protectArea;
                }
            }
        }
        systemMap.put("protectArea",protectAreaCombo);
        // 获取总容积
        cursor = daoSession.getDatabase().rawQuery(String.format("SELECT SUM(%s.%s) FROM %s INNER JOIN %s ON %s.CHECK_TYPE_ID=%s._id WHERE %s.PARENT_ID=%s AND CAST(%s.CHECK_DATE AS TEXT)='%s'",
                ItemInfoDao.TABLENAME,
                ItemInfoDao.Properties.GoodsWeight.columnName,
                ItemInfoDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                ItemInfoDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                systemId,
                ItemInfoDao.TABLENAME,
                checkDate.getTime()
        ), new String []{});
        cursor.moveToFirst();
        result = cursor.getLong(0);
        systemMap.put("weights",result);
        retList.add(systemMap);

        // 8888888888888888888
        systemName = "固定式干粉灭火系统";
        // 获取对应id
        checkTypeQueryBuilder = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq(systemName));
        systemId = checkTypeQueryBuilder.list().get(0).getId();
        queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
                        ItemInfoDao.Properties.CheckDate.eq(checkDate)
                );
        checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
                where(CheckTypeDao.Properties.ParentId.eq(systemId));
        dataList = queryBuilder.list();
        systemMap = new HashMap();
        systemMap.put("systemName",systemName);
        systemMap.put("data",dataList);
        systemMap.put("count",dataList.size());
        // 获取区域和位号
        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY SYSTEM_NUMBER", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        systemNumberCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","系统位号------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String systemNumber = ret.getSystemNumber();
                if(systemNumberCombo!=""){
                    systemNumberCombo = systemNumberCombo + "、" + systemNumber;
                }
                else {
                    systemNumberCombo = systemNumberCombo + systemNumber;
                }
            }
        }
        systemMap.put("systemNumber",systemNumberCombo);
        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY PROTECT_AREA", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        protectAreaCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","保护区域------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String protectArea = ret.getProtectArea();
                if(protectAreaCombo!=""){
                    protectAreaCombo = protectAreaCombo + "、" + protectArea;
                }
                else {
                    protectAreaCombo = protectAreaCombo + protectArea;
                }
            }
        }
        systemMap.put("protectArea",protectAreaCombo);
        // 获取总容积
        cursor = daoSession.getDatabase().rawQuery(String.format("SELECT SUM(%s.%s) FROM %s INNER JOIN %s ON %s.CHECK_TYPE_ID=%s._id WHERE %s.PARENT_ID=%s AND CAST(%s.CHECK_DATE AS TEXT)='%s'",
                ItemInfoDao.TABLENAME,
                ItemInfoDao.Properties.GoodsWeight.columnName,
                ItemInfoDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                ItemInfoDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                systemId,
                ItemInfoDao.TABLENAME,
                checkDate.getTime()
        ), new String []{});
        cursor.moveToFirst();
        result = cursor.getLong(0);
        systemMap.put("weights",result);
        retList.add(systemMap);

        // 99999999999999999999999999999
        systemName = "消防员装备";
        // 获取对应id
        checkTypeQueryBuilder = daoSession.queryBuilder(CheckType.class).
                where(CheckTypeDao.Properties.Name.eq(systemName));
        systemId = checkTypeQueryBuilder.list().get(0).getId();
        queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CompanyInfoId.eq(companyInfoId),
                        ItemInfoDao.Properties.CheckDate.eq(checkDate)
                );
        checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId, CheckType.class).
                where(CheckTypeDao.Properties.ParentId.eq(systemId));
        dataList = queryBuilder.list();
        systemMap = new HashMap();
        systemMap.put("systemName",systemName);
        systemMap.put("data",dataList);
        systemMap.put("count",dataList.size());
        // 获取区域和位号
        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY SYSTEM_NUMBER", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        systemNumberCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","系统位号------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String systemNumber = ret.getSystemNumber();
                if(systemNumberCombo!=""){
                    systemNumberCombo = systemNumberCombo + "、" + systemNumber;
                }
                else {
                    systemNumberCombo = systemNumberCombo + systemNumber;
                }
            }
        }
        systemMap.put("systemNumber",systemNumberCombo);
        secondQueryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(
                        ItemInfoDao.Properties.CheckDate.eq(checkDate),
                        new WhereCondition.StringCondition(
                                String.format("COMPANY_INFO_ID=%s GROUP BY PROTECT_AREA", companyInfoId))
                );
        secondDataList = secondQueryBuilder.list();
        protectAreaCombo = "";
        for(int i=0;i<secondDataList.size();i++){
            ItemInfo ret = secondDataList.get(i);
//            Log.i("tang","保护区域------" + ret);
            long DBsystemId = ret.getCheckType().getParent().getId();
            if(systemId==DBsystemId){
                String protectArea = ret.getProtectArea();
                if(protectAreaCombo!=""){
                    protectAreaCombo = protectAreaCombo + "、" + protectArea;
                }
                else {
                    protectAreaCombo = protectAreaCombo + protectArea;
                }
            }
        }
        systemMap.put("protectArea",protectAreaCombo);
        // 获取总容积
        cursor = daoSession.getDatabase().rawQuery(String.format("SELECT SUM(%s.%s) FROM %s INNER JOIN %s ON %s.CHECK_TYPE_ID=%s._id WHERE %s.PARENT_ID=%s AND CAST(%s.CHECK_DATE AS TEXT)='%s'",
                ItemInfoDao.TABLENAME,
                ItemInfoDao.Properties.GoodsWeight.columnName,
                ItemInfoDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                ItemInfoDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                CheckTypeDao.TABLENAME,
                systemId,
                ItemInfoDao.TABLENAME,
                checkDate.getTime()
        ), new String []{});
        cursor.moveToFirst();
        result = cursor.getLong(0);
        systemMap.put("weights",result);
        retList.add(systemMap);


        return retList;
        */


    }

}
