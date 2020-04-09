package com.hr.fire.inspection.sqlHelpers;

import android.util.JsonReader;
import android.util.Log;

import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.CompanyInfoDao;
import com.hr.fire.inspection.dao.DaoSession;
import com.hr.fire.inspection.dao.ItemInfoDao;
import com.hr.fire.inspection.dao.YearCheckResultDao;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.YearCheckResult;
import com.hr.fire.inspection.utils.GreenDaoHelper;

import org.greenrobot.greendao.query.Join;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.json.JSONStringer;

import java.util.Arrays;
import java.util.List;

public class YearCheckData {
    DaoSession daoSession = GreenDaoHelper.getDaoSession();
    public List<ItemInfo> getItemData(String companyName,String oilfieldName,String platformName,String systemName, String tableName, String number){
        // 根据公司名，油田名，平台名,联表查询
        QueryBuilder<ItemInfo> queryBuilder;
        if(number != null && number!=""){
            queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                    where(ItemInfoDao.Properties.SystemNumber.eq(number));
        }
        else {
            queryBuilder = daoSession.queryBuilder(ItemInfo.class);
        }


        Join checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId,CheckType.class)
                .where(CheckTypeDao.Properties.Name.eq(tableName));
        Join checkTypeParentJoin = queryBuilder.join(checkTypeJoin, CheckTypeDao.Properties.ParentId, CheckType.class,CheckTypeDao.Properties.Id)
                .where(CheckTypeDao.Properties.Name.eq(systemName));
        Join companyJoin = queryBuilder.join(ItemInfoDao.Properties.CompanyInfoId,CompanyInfo.class)
                .where(
                        CompanyInfoDao.Properties.CompanyName.eq(companyName),
                        CompanyInfoDao.Properties.OilfieldName.eq(oilfieldName),
                        CompanyInfoDao.Properties.PlatformName.eq(platformName)
                        );

        List<ItemInfo> dataList = queryBuilder.list();
//        List<ItemInfo> dataList =  queryBuilder.build().list();
        Log.i("info","查询完成-------------------------------------------");
        for(int i = 0; i < dataList.size(); i++){
            Object object = dataList.get(i);
            Log.i("result", ((ItemInfo) object).getNo());
            Log.i("result", ((ItemInfo) object).getVolume());
            Log.i("result", ((ItemInfo) object).getWeight());
            Log.i("result", ((ItemInfo) object).getProdFactory());
            Log.i("result", ((ItemInfo) object).getCompanyInfoId().toString());
        }
        Log.i("info","查询完成-------------------------------------------");

        return dataList;
    }

//    public List<YearCheckResult> getCheckResultData(String companyName,String oilfieldName,String platformName,String systemName, String tableName, String number){
    public List<YearCheckResult> getCheckResultData(long id){

        QueryBuilder<YearCheckResult> queryBuilder = daoSession.queryBuilder(YearCheckResult.class);

        Join itemInfoJoin = queryBuilder.join(YearCheckResultDao.Properties.ItemInfoId, ItemInfo.class).
                where(ItemInfoDao.Properties.Id.eq(id));

        List<YearCheckResult> dataList = queryBuilder.list();
        Log.i("info","查询完成02-------------------------------------------");
        for(int i = 0; i < dataList.size(); i++){
            YearCheckResult result = dataList.get(i);
            Log.i("result",result.toString());
            Log.i("result", result.getYearCheck().toString());
        }
        Log.i("info","查询完成02-------------------------------------------");

        return dataList;



    }

}
