package com.hr.fire.inspection.sqlHelpers;

import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.CompanyInfoDao;
import com.hr.fire.inspection.dao.DaoSession;
import com.hr.fire.inspection.dao.ItemInfoDao;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.utils.GreenDaoHelper;

import org.greenrobot.greendao.query.Join;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class YearCheckData {
    DaoSession daoSession = GreenDaoHelper.getDaoSession();
    public void getItemData(String companyName,String oilfieldName,String platformName,String systemName, String tableNmae,String number){
        // 根据公司名，油田名，平台名,联表查询

//        QueryBuilder<ItemInfo> queryBuilder = daoSession.queryBuilder(ItemInfo.class);

//        queryBuilder.join(ItemInfoDao.Properties.CompanyInfoId,CompanyInfo.class).
//                where(CompanyInfoDao.Properties.CompanyName.eq(companyName));
//        List<ItemInfo> items = queryBuilder.list();

        QueryBuilder<ItemInfo> queryBuilder = daoSession.queryBuilder(ItemInfo.class).
                where(ItemInfoDao.Properties.SystemNumber.eq(number));

        Join checkTypeJoin = queryBuilder.join(ItemInfoDao.Properties.CheckTypeId,CheckType.class)
                .where(CheckTypeDao.Properties.Name.eq(tableNmae));

        Join companyJoin = queryBuilder.join(ItemInfoDao.Properties.CompanyInfoId,CompanyInfo.class)
                .where(CompanyInfoDao.Properties.CompanyName.eq(companyName));

        queryBuilder.list();

        List<ItemInfo> list = queryBuilder.build().list();


    }

    public void getCheckResultData(){}

}
