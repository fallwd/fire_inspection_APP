package com.hr.fire.inspection.service.impl;


import com.hr.fire.inspection.dao.StandardInfoDao;
import com.hr.fire.inspection.dao.StandardTypeDao;
import com.hr.fire.inspection.entity.StandardInfo;
import com.hr.fire.inspection.entity.StandardType;
import com.hr.fire.inspection.service.StandardInfoService;

import org.greenrobot.greendao.query.Join;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class StandardInfoServiceImpl extends BaseServiceImpl<Object> implements StandardInfoService {

    @Override
    public List<StandardInfo> getStandardData(String typeName) {
        QueryBuilder<StandardInfo> queryBuilder = daoSession.queryBuilder(StandardInfo.class);
        Join standardJoin = queryBuilder.join(StandardInfoDao.Properties.StandardTypeId, StandardType.class).
                where(StandardTypeDao.Properties.Name.eq(typeName));
        List<StandardInfo> dataList = queryBuilder.list();
        return dataList;
    }

    @Override
    public List<StandardInfo> getSearchResult(String name, String typeName) {
        QueryBuilder<StandardInfo> queryBuilder = daoSession.queryBuilder(StandardInfo.class).
                where(StandardInfoDao.Properties.Name.like(name));

        if(typeName!=null && typeName!=""){
            Join standardJoin = queryBuilder.join(StandardInfoDao.Properties.StandardTypeId, StandardType.class).
                    where(StandardTypeDao.Properties.Name.eq(typeName));
        }
        List<StandardInfo> dataList = queryBuilder.list();
        return dataList;
    }

    @Override
    public List<StandardType> getTypeData() {
        QueryBuilder<StandardType> queryBuilder = daoSession.queryBuilder(StandardType.class);
        List<StandardType> dataList = queryBuilder.list();
        return dataList;
    }
}
