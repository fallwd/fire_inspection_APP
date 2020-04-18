package com.hr.fire.inspection.service.impl;

import android.util.Log;

import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.InspectionResultDao;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.InspectionResult;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.service.InspectionService;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class InspectionServiceImpl extends BaseServiceImpl<InspectionResult> implements InspectionService {
    @Override
    public List<CheckType> getSystemNameData() {
        QueryBuilder<CheckType> queryBuilder = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.ParentId.eq(0),
                        CheckTypeDao.Properties.Type.eq(2)
                );
        List<CheckType> dataList = queryBuilder.list();
        return dataList;
    }

    @Override
    public List<CheckType> gettableNameData(long checkTypeId) {
        QueryBuilder<CheckType> queryBuilder = daoSession.queryBuilder(CheckType.class).
                where(
                        CheckTypeDao.Properties.ParentId.eq(checkTypeId),
                        CheckTypeDao.Properties.Type.eq(2)
                );
        List<CheckType> dataList = queryBuilder.list();
        return dataList;
    }

    @Override
    public List<HashMap> getHistoryList(long companyId, long systemId) {
        QueryBuilder<InspectionResult> queryBuilder = daoSession.queryBuilder(InspectionResult.class).
                where(new WhereCondition.StringCondition(
                        String.format("COMPANY_INFO_ID=%s GROUP BY CHECK_DATE", companyId)));
        List<InspectionResult> dataList = queryBuilder.list();
        ArrayList<HashMap> resultList = new ArrayList();
        Log.i("getHistoryList:::","查询完成");

        for(int i=0;i<dataList.size();i++){
            InspectionResult ret = dataList.get(i);
//            Log.i("getHistoryList:::",ret.toString());
            long DBsystemId = ret.getCheckType().getParent().getId();
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
            if(systemId==DBsystemId){
                HashMap obj = new HashMap();
                obj.put("ret",comboData);
                obj.put("companyInfoId",companyId);
                obj.put("systemId",systemId);
                obj.put("checkDate",checkDate);
                resultList.add(obj);
            }

        }
        return resultList;
    }

    @Override
    public List<InspectionResult> getInspectionData(long companyInfoId, long checkTypeId, Date checkDate) {
        QueryBuilder<InspectionResult> queryBuilder = daoSession.queryBuilder(InspectionResult.class).
                where(
                        InspectionResultDao.Properties.CompanyInfoId.eq(companyInfoId),
                        InspectionResultDao.Properties.CheckTypeId.eq(checkTypeId),
                        InspectionResultDao.Properties.CheckDate.eq(checkDate)
                );

        List<InspectionResult> dataList = queryBuilder.list();

        return dataList;
    }

    @Override
    public long insertInspectionData(InspectionResult inspectionData, long companyInfoId, long checkTypeId, Date checkDate) {
        inspectionData.setCompanyInfoId(companyInfoId);
        inspectionData.setCheckTypeId(checkTypeId);
        inspectionData.setCheckDate(checkDate);
        daoSession.insert(inspectionData);
        return 0;
    }

    @Override
    public List<HashMap> getOutputList() {

        QueryBuilder<InspectionResult> queryBuilder = daoSession.queryBuilder(InspectionResult.class).
                where(new WhereCondition.StringCondition(
                                "1 GROUP BY COMPANY_INFO_ID,CHECK_DATE,CHECK_PERSON"));
        List<InspectionResult> dataList = queryBuilder.list();
        ArrayList resultList = new ArrayList();
        for(int i=0;i<dataList.size();i++){
            InspectionResult ret = dataList.get(i);
            String companyName = ret.getCompanyInfo().getCompanyName();
            String oilfieldName = ret.getCompanyInfo().getOilfieldName();
            String platformName = ret.getCompanyInfo().getPlatformName();
            String checkPerson = ret.getCheckPerson();
            Date checkDate = ret.getCheckDate();
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
            String checkDateStr;
            if(checkDate!=null){
                checkDateStr = formatter.format(checkDate);
                String comboData = checkPerson + "_" + companyName + "_" + oilfieldName + "_" + platformName + "_"  + checkDateStr;
//            Log.i("getHistoryList:::",comboData);

                HashMap obj = new HashMap();
                obj.put("ret",comboData);
                obj.put("companyInfoId",ret.getCompanyInfoId());
                obj.put("checkDate",checkDate);
                obj.put("checkPerson",checkPerson);

                resultList.add(obj);
            }

        }

        return resultList;
    }

    @Override
    public List<HashMap> getOutputItemData(long companyInfoId, String checkPerson, Date checkDate) {
        return null;
    }
}
