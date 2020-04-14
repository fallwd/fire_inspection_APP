package com.hr.fire.inspection.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.CompanyInfo;

import com.hr.fire.inspection.entity.ItemInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "t_item_info".
*/
public class ItemInfoDao extends AbstractDao<ItemInfo, Long> {

    public static final String TABLENAME = "t_item_info";

    /**
     * Properties of entity ItemInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CheckTypeId = new Property(1, Long.class, "checkTypeId", false, "CHECK_TYPE_ID");
        public final static Property CompanyInfoId = new Property(2, Long.class, "companyInfoId", false, "COMPANY_INFO_ID");
        public final static Property TypeNo = new Property(3, String.class, "typeNo", false, "TYPE_NO");
        public final static Property No = new Property(4, String.class, "no", false, "NO");
        public final static Property Volume = new Property(5, String.class, "volume", false, "VOLUME");
        public final static Property Weight = new Property(6, String.class, "weight", false, "WEIGHT");
        public final static Property GoodsWeight = new Property(7, String.class, "goodsWeight", false, "GOODS_WEIGHT");
        public final static Property Pressure = new Property(8, String.class, "pressure", false, "PRESSURE");
        public final static Property ProdFactory = new Property(9, String.class, "prodFactory", false, "PROD_FACTORY");
        public final static Property ProdDate = new Property(10, java.util.Date.class, "prodDate", false, "PROD_DATE");
        public final static Property TypeConformity = new Property(11, String.class, "typeConformity", false, "TYPE_CONFORMITY");
        public final static Property PositionConformity = new Property(12, String.class, "positionConformity", false, "POSITION_CONFORMITY");
        public final static Property Appearance = new Property(13, String.class, "appearance", false, "APPEARANCE");
        public final static Property Check = new Property(14, String.class, "check", false, "CHECK");
        public final static Property Slience = new Property(15, String.class, "slience", false, "SLIENCE");
        public final static Property Reset = new Property(16, String.class, "reset", false, "RESET");
        public final static Property PowerAlarmFunction = new Property(17, String.class, "powerAlarmFunction", false, "POWER_ALARM_FUNCTION");
        public final static Property AlarmFunction = new Property(18, String.class, "alarmFunction", false, "ALARM_FUNCTION");
        public final static Property Effectiveness = new Property(19, String.class, "effectiveness", false, "EFFECTIVENESS");
        public final static Property ResponseTime = new Property(20, String.class, "responseTime", false, "RESPONSE_TIME");
        public final static Property Descrption = new Property(21, String.class, "descrption", false, "DESCRPTION");
        public final static Property SetAlarm25 = new Property(22, String.class, "setAlarm25", false, "SET_ALARM25");
        public final static Property SetAlarm50 = new Property(23, String.class, "setAlarm50", false, "SET_ALARM50");
        public final static Property TestAlarm25 = new Property(24, String.class, "testAlarm25", false, "TEST_ALARM25");
        public final static Property TestAlarm50 = new Property(25, String.class, "testAlarm50", false, "TEST_ALARM50");
        public final static Property ObserveDate = new Property(26, java.util.Date.class, "observeDate", false, "OBSERVE_DATE");
        public final static Property IsPass = new Property(27, String.class, "isPass", false, "IS_PASS");
        public final static Property LabelNo = new Property(28, String.class, "labelNo", false, "LABEL_NO");
        public final static Property CodePath = new Property(29, String.class, "codePath", false, "CODE_PATH");
        public final static Property SystemNumber = new Property(30, String.class, "SystemNumber", false, "SYSTEM_NUMBER");
        public final static Property ProtectArea = new Property(31, String.class, "ProtectArea", false, "PROTECT_AREA");
        public final static Property CheckDate = new Property(32, java.util.Date.class, "checkDate", false, "CHECK_DATE");
    }

    private DaoSession daoSession;


    public ItemInfoDao(DaoConfig config) {
        super(config);
    }
    
    public ItemInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"t_item_info\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CHECK_TYPE_ID\" INTEGER," + // 1: checkTypeId
                "\"COMPANY_INFO_ID\" INTEGER," + // 2: companyInfoId
                "\"TYPE_NO\" TEXT," + // 3: typeNo
                "\"NO\" TEXT," + // 4: no
                "\"VOLUME\" TEXT," + // 5: volume
                "\"WEIGHT\" TEXT," + // 6: weight
                "\"GOODS_WEIGHT\" TEXT," + // 7: goodsWeight
                "\"PRESSURE\" TEXT," + // 8: pressure
                "\"PROD_FACTORY\" TEXT," + // 9: prodFactory
                "\"PROD_DATE\" INTEGER," + // 10: prodDate
                "\"TYPE_CONFORMITY\" TEXT," + // 11: typeConformity
                "\"POSITION_CONFORMITY\" TEXT," + // 12: positionConformity
                "\"APPEARANCE\" TEXT," + // 13: appearance
                "\"CHECK\" TEXT," + // 14: check
                "\"SLIENCE\" TEXT," + // 15: slience
                "\"RESET\" TEXT," + // 16: reset
                "\"POWER_ALARM_FUNCTION\" TEXT," + // 17: powerAlarmFunction
                "\"ALARM_FUNCTION\" TEXT," + // 18: alarmFunction
                "\"EFFECTIVENESS\" TEXT," + // 19: effectiveness
                "\"RESPONSE_TIME\" TEXT," + // 20: responseTime
                "\"DESCRPTION\" TEXT," + // 21: descrption
                "\"SET_ALARM25\" TEXT," + // 22: setAlarm25
                "\"SET_ALARM50\" TEXT," + // 23: setAlarm50
                "\"TEST_ALARM25\" TEXT," + // 24: testAlarm25
                "\"TEST_ALARM50\" TEXT," + // 25: testAlarm50
                "\"OBSERVE_DATE\" INTEGER," + // 26: observeDate
                "\"IS_PASS\" TEXT," + // 27: isPass
                "\"LABEL_NO\" TEXT," + // 28: labelNo
                "\"CODE_PATH\" TEXT," + // 29: codePath
                "\"SYSTEM_NUMBER\" TEXT," + // 30: SystemNumber
                "\"PROTECT_AREA\" TEXT," + // 31: ProtectArea
                "\"CHECK_DATE\" INTEGER);"); // 32: checkDate
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"t_item_info\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ItemInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long checkTypeId = entity.getCheckTypeId();
        if (checkTypeId != null) {
            stmt.bindLong(2, checkTypeId);
        }
 
        Long companyInfoId = entity.getCompanyInfoId();
        if (companyInfoId != null) {
            stmt.bindLong(3, companyInfoId);
        }
 
        String typeNo = entity.getTypeNo();
        if (typeNo != null) {
            stmt.bindString(4, typeNo);
        }
 
        String no = entity.getNo();
        if (no != null) {
            stmt.bindString(5, no);
        }
 
        String volume = entity.getVolume();
        if (volume != null) {
            stmt.bindString(6, volume);
        }
 
        String weight = entity.getWeight();
        if (weight != null) {
            stmt.bindString(7, weight);
        }
 
        String goodsWeight = entity.getGoodsWeight();
        if (goodsWeight != null) {
            stmt.bindString(8, goodsWeight);
        }
 
        String pressure = entity.getPressure();
        if (pressure != null) {
            stmt.bindString(9, pressure);
        }
 
        String prodFactory = entity.getProdFactory();
        if (prodFactory != null) {
            stmt.bindString(10, prodFactory);
        }
 
        java.util.Date prodDate = entity.getProdDate();
        if (prodDate != null) {
            stmt.bindLong(11, prodDate.getTime());
        }
 
        String typeConformity = entity.getTypeConformity();
        if (typeConformity != null) {
            stmt.bindString(12, typeConformity);
        }
 
        String positionConformity = entity.getPositionConformity();
        if (positionConformity != null) {
            stmt.bindString(13, positionConformity);
        }
 
        String appearance = entity.getAppearance();
        if (appearance != null) {
            stmt.bindString(14, appearance);
        }
 
        String check = entity.getCheck();
        if (check != null) {
            stmt.bindString(15, check);
        }
 
        String slience = entity.getSlience();
        if (slience != null) {
            stmt.bindString(16, slience);
        }
 
        String reset = entity.getReset();
        if (reset != null) {
            stmt.bindString(17, reset);
        }
 
        String powerAlarmFunction = entity.getPowerAlarmFunction();
        if (powerAlarmFunction != null) {
            stmt.bindString(18, powerAlarmFunction);
        }
 
        String alarmFunction = entity.getAlarmFunction();
        if (alarmFunction != null) {
            stmt.bindString(19, alarmFunction);
        }
 
        String effectiveness = entity.getEffectiveness();
        if (effectiveness != null) {
            stmt.bindString(20, effectiveness);
        }
 
        String responseTime = entity.getResponseTime();
        if (responseTime != null) {
            stmt.bindString(21, responseTime);
        }
 
        String descrption = entity.getDescrption();
        if (descrption != null) {
            stmt.bindString(22, descrption);
        }
 
        String setAlarm25 = entity.getSetAlarm25();
        if (setAlarm25 != null) {
            stmt.bindString(23, setAlarm25);
        }
 
        String setAlarm50 = entity.getSetAlarm50();
        if (setAlarm50 != null) {
            stmt.bindString(24, setAlarm50);
        }
 
        String testAlarm25 = entity.getTestAlarm25();
        if (testAlarm25 != null) {
            stmt.bindString(25, testAlarm25);
        }
 
        String testAlarm50 = entity.getTestAlarm50();
        if (testAlarm50 != null) {
            stmt.bindString(26, testAlarm50);
        }
 
        java.util.Date observeDate = entity.getObserveDate();
        if (observeDate != null) {
            stmt.bindLong(27, observeDate.getTime());
        }
 
        String isPass = entity.getIsPass();
        if (isPass != null) {
            stmt.bindString(28, isPass);
        }
 
        String labelNo = entity.getLabelNo();
        if (labelNo != null) {
            stmt.bindString(29, labelNo);
        }
 
        String codePath = entity.getCodePath();
        if (codePath != null) {
            stmt.bindString(30, codePath);
        }
 
        String SystemNumber = entity.getSystemNumber();
        if (SystemNumber != null) {
            stmt.bindString(31, SystemNumber);
        }
 
        String ProtectArea = entity.getProtectArea();
        if (ProtectArea != null) {
            stmt.bindString(32, ProtectArea);
        }
 
        java.util.Date checkDate = entity.getCheckDate();
        if (checkDate != null) {
            stmt.bindLong(33, checkDate.getTime());
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ItemInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long checkTypeId = entity.getCheckTypeId();
        if (checkTypeId != null) {
            stmt.bindLong(2, checkTypeId);
        }
 
        Long companyInfoId = entity.getCompanyInfoId();
        if (companyInfoId != null) {
            stmt.bindLong(3, companyInfoId);
        }
 
        String typeNo = entity.getTypeNo();
        if (typeNo != null) {
            stmt.bindString(4, typeNo);
        }
 
        String no = entity.getNo();
        if (no != null) {
            stmt.bindString(5, no);
        }
 
        String volume = entity.getVolume();
        if (volume != null) {
            stmt.bindString(6, volume);
        }
 
        String weight = entity.getWeight();
        if (weight != null) {
            stmt.bindString(7, weight);
        }
 
        String goodsWeight = entity.getGoodsWeight();
        if (goodsWeight != null) {
            stmt.bindString(8, goodsWeight);
        }
 
        String pressure = entity.getPressure();
        if (pressure != null) {
            stmt.bindString(9, pressure);
        }
 
        String prodFactory = entity.getProdFactory();
        if (prodFactory != null) {
            stmt.bindString(10, prodFactory);
        }
 
        java.util.Date prodDate = entity.getProdDate();
        if (prodDate != null) {
            stmt.bindLong(11, prodDate.getTime());
        }
 
        String typeConformity = entity.getTypeConformity();
        if (typeConformity != null) {
            stmt.bindString(12, typeConformity);
        }
 
        String positionConformity = entity.getPositionConformity();
        if (positionConformity != null) {
            stmt.bindString(13, positionConformity);
        }
 
        String appearance = entity.getAppearance();
        if (appearance != null) {
            stmt.bindString(14, appearance);
        }
 
        String check = entity.getCheck();
        if (check != null) {
            stmt.bindString(15, check);
        }
 
        String slience = entity.getSlience();
        if (slience != null) {
            stmt.bindString(16, slience);
        }
 
        String reset = entity.getReset();
        if (reset != null) {
            stmt.bindString(17, reset);
        }
 
        String powerAlarmFunction = entity.getPowerAlarmFunction();
        if (powerAlarmFunction != null) {
            stmt.bindString(18, powerAlarmFunction);
        }
 
        String alarmFunction = entity.getAlarmFunction();
        if (alarmFunction != null) {
            stmt.bindString(19, alarmFunction);
        }
 
        String effectiveness = entity.getEffectiveness();
        if (effectiveness != null) {
            stmt.bindString(20, effectiveness);
        }
 
        String responseTime = entity.getResponseTime();
        if (responseTime != null) {
            stmt.bindString(21, responseTime);
        }
 
        String descrption = entity.getDescrption();
        if (descrption != null) {
            stmt.bindString(22, descrption);
        }
 
        String setAlarm25 = entity.getSetAlarm25();
        if (setAlarm25 != null) {
            stmt.bindString(23, setAlarm25);
        }
 
        String setAlarm50 = entity.getSetAlarm50();
        if (setAlarm50 != null) {
            stmt.bindString(24, setAlarm50);
        }
 
        String testAlarm25 = entity.getTestAlarm25();
        if (testAlarm25 != null) {
            stmt.bindString(25, testAlarm25);
        }
 
        String testAlarm50 = entity.getTestAlarm50();
        if (testAlarm50 != null) {
            stmt.bindString(26, testAlarm50);
        }
 
        java.util.Date observeDate = entity.getObserveDate();
        if (observeDate != null) {
            stmt.bindLong(27, observeDate.getTime());
        }
 
        String isPass = entity.getIsPass();
        if (isPass != null) {
            stmt.bindString(28, isPass);
        }
 
        String labelNo = entity.getLabelNo();
        if (labelNo != null) {
            stmt.bindString(29, labelNo);
        }
 
        String codePath = entity.getCodePath();
        if (codePath != null) {
            stmt.bindString(30, codePath);
        }
 
        String SystemNumber = entity.getSystemNumber();
        if (SystemNumber != null) {
            stmt.bindString(31, SystemNumber);
        }
 
        String ProtectArea = entity.getProtectArea();
        if (ProtectArea != null) {
            stmt.bindString(32, ProtectArea);
        }
 
        java.util.Date checkDate = entity.getCheckDate();
        if (checkDate != null) {
            stmt.bindLong(33, checkDate.getTime());
        }
    }

    @Override
    protected final void attachEntity(ItemInfo entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ItemInfo readEntity(Cursor cursor, int offset) {
        ItemInfo entity = new ItemInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // checkTypeId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // companyInfoId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // typeNo
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // no
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // volume
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // weight
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // goodsWeight
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // pressure
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // prodFactory
            cursor.isNull(offset + 10) ? null : new java.util.Date(cursor.getLong(offset + 10)), // prodDate
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // typeConformity
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // positionConformity
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // appearance
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // check
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // slience
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // reset
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // powerAlarmFunction
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // alarmFunction
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // effectiveness
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // responseTime
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // descrption
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // setAlarm25
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // setAlarm50
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // testAlarm25
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // testAlarm50
            cursor.isNull(offset + 26) ? null : new java.util.Date(cursor.getLong(offset + 26)), // observeDate
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // isPass
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // labelNo
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // codePath
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30), // SystemNumber
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // ProtectArea
            cursor.isNull(offset + 32) ? null : new java.util.Date(cursor.getLong(offset + 32)) // checkDate
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ItemInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCheckTypeId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setCompanyInfoId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setTypeNo(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setNo(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setVolume(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setWeight(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setGoodsWeight(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPressure(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setProdFactory(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setProdDate(cursor.isNull(offset + 10) ? null : new java.util.Date(cursor.getLong(offset + 10)));
        entity.setTypeConformity(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setPositionConformity(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setAppearance(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setCheck(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setSlience(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setReset(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setPowerAlarmFunction(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setAlarmFunction(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setEffectiveness(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setResponseTime(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setDescrption(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setSetAlarm25(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setSetAlarm50(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setTestAlarm25(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setTestAlarm50(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setObserveDate(cursor.isNull(offset + 26) ? null : new java.util.Date(cursor.getLong(offset + 26)));
        entity.setIsPass(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setLabelNo(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setCodePath(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setSystemNumber(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
        entity.setProtectArea(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setCheckDate(cursor.isNull(offset + 32) ? null : new java.util.Date(cursor.getLong(offset + 32)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ItemInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ItemInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ItemInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getCheckTypeDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getCompanyInfoDao().getAllColumns());
            builder.append(" FROM t_item_info T");
            builder.append(" LEFT JOIN t_check_type T0 ON T.\"CHECK_TYPE_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN t_company_info T1 ON T.\"COMPANY_INFO_ID\"=T1.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected ItemInfo loadCurrentDeep(Cursor cursor, boolean lock) {
        ItemInfo entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        CheckType checkType = loadCurrentOther(daoSession.getCheckTypeDao(), cursor, offset);
        entity.setCheckType(checkType);
        offset += daoSession.getCheckTypeDao().getAllColumns().length;

        CompanyInfo companyInfo = loadCurrentOther(daoSession.getCompanyInfoDao(), cursor, offset);
        entity.setCompanyInfo(companyInfo);

        return entity;    
    }

    public ItemInfo loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<ItemInfo> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<ItemInfo> list = new ArrayList<ItemInfo>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<ItemInfo> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<ItemInfo> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}