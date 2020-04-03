package com.hr.fire.inspection.sqlHelpers;

import com.hr.fire.inspection.dao.DaoSession;
import com.hr.fire.inspection.utils.GreenDaoHelper;

public class BaseData {
    DaoSession daoSession = GreenDaoHelper.getDaoSession();
    public void getData(String tableName, String number){ }
}
