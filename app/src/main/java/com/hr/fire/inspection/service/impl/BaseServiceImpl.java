package com.hr.fire.inspection.service.impl;

import com.hr.fire.inspection.dao.DaoSession;
import com.hr.fire.inspection.service.BaseService;
import com.hr.fire.inspection.utils.GreenDaoHelper;

public class BaseServiceImpl<T> implements BaseService<T> {
    DaoSession daoSession = GreenDaoHelper.getDaoSession();
    @Override
    public long insert(T o) {
        return daoSession.insert(o);
    }

    @Override
    public void update(T o) {
        daoSession.update(o);
    }

    @Override
    public void delete(T o) {
        daoSession.delete(o);
    }
}
