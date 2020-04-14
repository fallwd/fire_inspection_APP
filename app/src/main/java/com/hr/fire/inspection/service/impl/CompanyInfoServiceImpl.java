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


    @Override
    public long addData(String companyName, String oilfieldName, String platformName) {
        QueryBuilder<CompanyInfo> queryBuilder = daoSession.queryBuilder(CompanyInfo.class).
                where(
                        CompanyInfoDao.Properties.CompanyName.eq(companyName),
                        CompanyInfoDao.Properties.OilfieldName.eq(oilfieldName),
                        CompanyInfoDao.Properties.PlatformName.eq(platformName)
                );
        List<CompanyInfo> retList = queryBuilder.list();
        if(retList.size()!=0){
            return 1;
        }
        CompanyInfo item = new CompanyInfo();
        item.setCompanyName(companyName);
        item.setOilfieldName(oilfieldName);
        item.setPlatformName(platformName);
        daoSession.insert(item);
        return 0;
    }

    @Override
    public long rename(String oldName, String newName, String type) {
        List<CompanyInfo> dataList;
        if(type=="company"){
            //更新公司对应所有数据
            QueryBuilder<CompanyInfo> queryBuilder = daoSession.queryBuilder(CompanyInfo.class).
                    where(
                            CompanyInfoDao.Properties.CompanyName.eq(oldName)
                    );
            dataList = queryBuilder.list();
            for(int i=0;i<dataList.size();i++){
                CompanyInfo obj = dataList.get(i);
                obj.setCompanyName(newName);
                daoSession.update(obj);
            }

        }
        else if(type=="oilfield"){
            QueryBuilder<CompanyInfo> queryBuilder = daoSession.queryBuilder(CompanyInfo.class).
                    where(
                            CompanyInfoDao.Properties.OilfieldName.eq(oldName)
                    );
            dataList = queryBuilder.list();
            for(int i=0;i<dataList.size();i++){
                CompanyInfo obj = dataList.get(i);
                obj.setOilfieldName(newName);
                daoSession.update(obj);
            }
        }
        else if(type=="platform"){
            QueryBuilder<CompanyInfo> queryBuilder = daoSession.queryBuilder(CompanyInfo.class).
                    where(
                            CompanyInfoDao.Properties.PlatformName.eq(oldName)
                    );
            dataList = queryBuilder.list();
            for(int i=0;i<dataList.size();i++){
                CompanyInfo obj = dataList.get(i);
                obj.setPlatformName(newName);
                daoSession.update(obj);
            }
        }
        else {
            return 1;
        }

        return 0;
    }

    @Override
    public long deleteData(String name, String type) {

        List<CompanyInfo> dataList;
        if(type=="company"){
            //更新公司对应所有数据
            QueryBuilder<CompanyInfo> queryBuilder = daoSession.queryBuilder(CompanyInfo.class).
                    where(
                            CompanyInfoDao.Properties.CompanyName.eq(name)
                    );
            dataList = queryBuilder.list();
//            for(int i=0;i<dataList.size();i++){
//                CompanyInfo obj = dataList.get(i);
//                daoSession.delete(obj);
//            }

        }
        else if(type=="oilfield"){
            QueryBuilder<CompanyInfo> queryBuilder = daoSession.queryBuilder(CompanyInfo.class).
                    where(
                            CompanyInfoDao.Properties.OilfieldName.eq(name)
                    );
            dataList = queryBuilder.list();
//            for(int i=0;i<dataList.size();i++){
//                CompanyInfo obj = dataList.get(i);
//                obj.setOilfieldName(null);
//                obj.setPlatformName(null);
//                daoSession.update(obj);
//            }
        }
        else if(type=="platform"){
            QueryBuilder<CompanyInfo> queryBuilder = daoSession.queryBuilder(CompanyInfo.class).
                    where(
                            CompanyInfoDao.Properties.PlatformName.eq(name)
                    );
            dataList = queryBuilder.list();
//            for(int i=0;i<dataList.size();i++){
//                CompanyInfo obj = dataList.get(i);
//                obj.setPlatformName(null);
//                daoSession.update(obj);
//            }
        }
        else {
            return 1;
        }
        for(int i=0;i<dataList.size();i++){
            CompanyInfo obj = dataList.get(i);
            daoSession.delete(obj);
        }

        return 0;
    }

}
