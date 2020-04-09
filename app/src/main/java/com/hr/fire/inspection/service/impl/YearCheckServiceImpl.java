package com.hr.fire.inspection.service.impl;

import android.util.Log;

import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.CompanyInfoDao;
import com.hr.fire.inspection.dao.ItemInfoDao;
import com.hr.fire.inspection.dao.YearCheckResultDao;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.service.YearCheckService;

import org.greenrobot.greendao.query.Join;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class YearCheckServiceImpl extends BaseServiceImpl<YearCheck> implements YearCheckService {

    @Override
    public List<ItemInfo> getItemData(String companyName, String oilfieldName, String platformName, String systemName, String tableName, String number) {

        // 根据公司名，油田名，平台名,联表查询
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

        List<ItemInfo> dataList = queryBuilder.list();
//        List<ItemInfo> dataList =  queryBuilder.build().list();
        Log.i("info","查询完成01-------------------------------------------");
        for(int i = 0; i < dataList.size(); i++){
            ItemInfo result = dataList.get(i);
            Log.i("result", result.toString());
            Log.i("result", result.getCompanyInfo().toString());
            Log.i("result", result.getCheckType().toString());
        }
        Log.i("info","查询完成01-------------------------------------------");

        return dataList;
    }

    @Override
    public List<YearCheckResult> getCheckResultData(long id,String companyName, String oilfieldName, String platformName, String systemName, String tableName, String number) {
        QueryBuilder<YearCheckResult> queryBuilder;
        List<YearCheckResult> dataList;
        if(id!=0) {
            queryBuilder = daoSession.queryBuilder(YearCheckResult.class);
            Join itemInfoJoin = queryBuilder.join(YearCheckResultDao.Properties.ItemInfoId, ItemInfo.class).
                    where(ItemInfoDao.Properties.Id.eq(id));

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
//            QueryBuilder<ItemInfo> queryBuilder;
            if(number != null && number!=""){
                queryBuilder = daoSession.queryBuilder(YearCheckResult.class).
                        where(ItemInfoDao.Properties.SystemNumber.eq(number));
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
            dataList = queryBuilder.list();
        }
        return dataList;
    }
}
