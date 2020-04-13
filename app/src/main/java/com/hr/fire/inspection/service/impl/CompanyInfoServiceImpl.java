package com.hr.fire.inspection.service.impl;

import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.service.CompanyInfoService;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class CompanyInfoServiceImpl extends BaseServiceImpl<CompanyInfo> implements CompanyInfoService {
    @Override
    public List<CompanyInfo> getAll() {
        QueryBuilder<CompanyInfo> queryBuilder = daoSession.queryBuilder(CompanyInfo.class);
        List<CompanyInfo> dataList = queryBuilder.list();
        return dataList;
    }
}
