package com.hr.fire.inspection.service.impl;

import android.util.Log;

import com.hr.fire.inspection.dao.CompanyInfoDao;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.service.CompanyInfoService;

import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

public class CompanyInfoServiceImpl extends BaseServiceImpl<CompanyInfo> implements CompanyInfoService {
    @Override
    public List<CompanyInfo> getAll() {
        QueryBuilder<CompanyInfo> queryBuilder = daoSession.queryBuilder(CompanyInfo.class);
        List<CompanyInfo> dataList = queryBuilder.list();
        return dataList;
    }

    @Override
    public List getCompanyList() {
        QueryBuilder<CompanyInfo> queryBuilder = daoSession.queryBuilder(CompanyInfo.class).
                where(new WhereCondition.StringCondition("1 GROUP BY COMPANY_NAME" ));

        List<CompanyInfo> dataList = queryBuilder.list();
//        for(int i=0;i<dataList.size();i++){
//            CompanyInfo result = dataList.get(i);
//            Log.i("getCompanyList",result.getCompanyName());
////            Log.i("getCompanyList",result.toString());
//        }
        return dataList;
    }

    @Override
    public List<CompanyInfo> getOilfieldList(String companyName) {
        QueryBuilder<CompanyInfo> queryBuilder = daoSession.queryBuilder(CompanyInfo.class).
                where(new WhereCondition.StringCondition(
                        String.format("COMPANY_NAME='%s' GROUP BY OILFIELD_NAME", companyName)));
//                        CompanyInfoDao.Properties.CompanyName.eq(companyName).toString() + "GROUP BY OILFIELD_NAME"));
        List<CompanyInfo> dataList = queryBuilder.list();
//        for(int i=0;i<dataList.size();i++){
//            CompanyInfo result = dataList.get(i);
//            Log.i("getOilfieldList",result.getOilfieldName());
////            Log.i("getOilfieldList",result.toString());
//        }
        return dataList;
    }

    @Override
    public List<CompanyInfo> getPlatformList(String oilfieldName) {
        QueryBuilder<CompanyInfo> queryBuilder = daoSession.queryBuilder(CompanyInfo.class).
                where(CompanyInfoDao.Properties.OilfieldName.eq(oilfieldName));
        List<CompanyInfo> dataList = queryBuilder.list();
//        for(int i=0;i<dataList.size();i++){
//            CompanyInfo result = dataList.get(i);
//            Log.i("getPlatformList",result.getPlatformName());
////            Log.i("getPlatformList",result.toString());
//        }
        return dataList;

    }

}
