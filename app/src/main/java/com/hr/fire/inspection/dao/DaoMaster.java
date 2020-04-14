package com.hr.fire.inspection.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.identityscope.IdentityScopeType;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/**
 * Master of DAO (schema version 4): knows all DAOs.
 */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 4;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(Database db, boolean ifNotExists) {
        CheckTypeDao.createTable(db, ifNotExists);
<<<<<<< HEAD
        YearCheckDao.createTable(db, ifNotExists);
        YearCheckResultDao.createTable(db, ifNotExists);
        ItemInfoDao.createTable(db, ifNotExists);
        CompanyInfoDao.createTable(db, ifNotExists);
        StandardInfoDao.createTable(db, ifNotExists);
        StandardTypeDao.createTable(db, ifNotExists);
<<<<<<< HEAD
=======
=======
        ItemInfoDao.createTable(db, ifNotExists);
>>>>>>> ba55a7c522be9163ffd8d30ea067dff5f3de2d82
        YearCheckDao.createTable(db, ifNotExists);
        YearCheckResultDao.createTable(db, ifNotExists);
        CheckPersonDao.createTable(db, ifNotExists);
        CompanyInfoDao.createTable(db, ifNotExists);
        CompanyTypeDao.createTable(db, ifNotExists);
        InspectionResultDao.createTable(db, ifNotExists);
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 14845b1b17fea59071c755784a22119d9c2d7bae
        CheckPersonDao.createTable(db, ifNotExists);
=======
>>>>>>> be2e63913f7d082439c2eb34ca3c024e4ea4f05b
>>>>>>> 9069637e91f76f416f6f181e0d49e06685d6224b
=======
        StandardInfoDao.createTable(db, ifNotExists);
        StandardTypeDao.createTable(db, ifNotExists);
>>>>>>> ba55a7c522be9163ffd8d30ea067dff5f3de2d82
    }

    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(Database db, boolean ifExists) {
        CheckTypeDao.dropTable(db, ifExists);
<<<<<<< HEAD
        YearCheckDao.dropTable(db, ifExists);
        YearCheckResultDao.dropTable(db, ifExists);
        ItemInfoDao.dropTable(db, ifExists);
        CompanyInfoDao.dropTable(db, ifExists);
        StandardInfoDao.dropTable(db, ifExists);
        StandardTypeDao.dropTable(db, ifExists);
<<<<<<< HEAD
=======
        YearCheckDao.dropTable(db, ifExists);
        YearCheckResultDao.dropTable(db, ifExists);
<<<<<<< HEAD
>>>>>>> 14845b1b17fea59071c755784a22119d9c2d7bae
=======
        ItemInfoDao.dropTable(db, ifExists);
        YearCheckDao.dropTable(db, ifExists);
        YearCheckResultDao.dropTable(db, ifExists);
>>>>>>> ba55a7c522be9163ffd8d30ea067dff5f3de2d82
        CheckPersonDao.dropTable(db, ifExists);
        CompanyInfoDao.dropTable(db, ifExists);
        CompanyTypeDao.dropTable(db, ifExists);
        InspectionResultDao.dropTable(db, ifExists);
        StandardInfoDao.dropTable(db, ifExists);
        StandardTypeDao.dropTable(db, ifExists);
    }

    /**
     * WARNING: Drops all table on Upgrade! Use only during development.
     * Convenience method using a {@link DevOpenHelper}.
     */
    public static DaoSession newDevSession(Context context, String name) {
        Database db = new DevOpenHelper(context, name).getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        return daoMaster.newSession();
    }

    public DaoMaster(SQLiteDatabase db) {
        this(new StandardDatabase(db));
    }

    public DaoMaster(Database db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(CheckTypeDao.class);
<<<<<<< HEAD
        registerDaoClass(YearCheckDao.class);
        registerDaoClass(YearCheckResultDao.class);
        registerDaoClass(ItemInfoDao.class);
        registerDaoClass(CompanyInfoDao.class);
        registerDaoClass(StandardInfoDao.class);
        registerDaoClass(StandardTypeDao.class);
<<<<<<< HEAD
=======
=======
        registerDaoClass(ItemInfoDao.class);
>>>>>>> ba55a7c522be9163ffd8d30ea067dff5f3de2d82
        registerDaoClass(YearCheckDao.class);
        registerDaoClass(YearCheckResultDao.class);
        registerDaoClass(CheckPersonDao.class);
        registerDaoClass(CompanyInfoDao.class);
        registerDaoClass(CompanyTypeDao.class);
        registerDaoClass(InspectionResultDao.class);
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> 14845b1b17fea59071c755784a22119d9c2d7bae
        registerDaoClass(CheckPersonDao.class);
=======
>>>>>>> be2e63913f7d082439c2eb34ca3c024e4ea4f05b
>>>>>>> 9069637e91f76f416f6f181e0d49e06685d6224b
=======
        registerDaoClass(StandardInfoDao.class);
        registerDaoClass(StandardTypeDao.class);
>>>>>>> ba55a7c522be9163ffd8d30ea067dff5f3de2d82
    }

    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }

    /**
     * Calls {@link #createAllTables(Database, boolean)} in {@link #onCreate(Database)} -
     */
    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String name) {
            super(context, name, SCHEMA_VERSION);
        }

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(Database db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }

    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name) {
            super(context, name);
        }

        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

}
