package com.hr.fire.inspection.utils;

import android.app.Application;

import com.hr.fire.inspection.dao.DaoMaster;
import com.hr.fire.inspection.dao.DaoSession;

import org.greenrobot.greendao.database.Database;


public class GreenDaoHelper extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        // regular SQLite database
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "hy_fire_inspection");
        Database db = helper.getWritableDb();

        daoSession = new DaoMaster(db).newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
