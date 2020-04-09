package com.hr.fire.inspection.utils;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hr.fire.inspection.dao.DaoMaster;

import org.greenrobot.greendao.database.Database;
public class DBManager {
    private final static String dbName = "t_company_info";
    private static DBManager mInstance;
    private DaoMaster.OpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.OpenHelper(context, dbName, null){};
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new MySQLiteOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new MySQLiteOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }
    public void deleSQL(){
        SQLiteDatabase db=getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoMaster.dropAllTables(daoMaster.getDatabase(),true);
        DaoMaster.createAllTables(daoMaster.getDatabase(),true);

    }
    class MySQLiteOpenHelper extends DaoMaster.OpenHelper{

        @Override
        public void onCreate(Database db) {
            super.onCreate(db);
        }

        public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }
    }
}
