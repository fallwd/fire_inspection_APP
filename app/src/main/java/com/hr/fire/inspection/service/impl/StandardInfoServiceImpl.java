package com.hr.fire.inspection.service.impl;


import com.hr.fire.inspection.dao.StandardInfoDao;
import com.hr.fire.inspection.entity.StandardInfo;
import com.hr.fire.inspection.service.StandardInfoService;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class StandardInfoServiceImpl extends BaseServiceImpl<Object> implements StandardInfoService {

    @Override
    public List<StandardInfo> getStandardData(String typeName) {
        QueryBuilder<StandardInfo> queryBuilder = daoSession.queryBuilder(StandardInfo.class).
                where(StandardInfoDao.Properties.Name.eq(typeName));
        List<StandardInfo> dataList = queryBuilder.list();
        return dataList;
    }
}
