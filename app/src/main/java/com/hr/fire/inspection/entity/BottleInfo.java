package com.hr.fire.inspection.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.hr.fire.inspection.dao.DaoSession;
import com.hr.fire.inspection.dao.YearCheckResultDao;
import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.BottleInfoDao;

@Entity(
        nameInDb = "t_bottle_info"
)
public class BottleInfo {

    @Id(autoincrement = true)
    private Long id;

    private Long checkTypeId;

    @ToOne(joinProperty = "checkTypeId")
    private CheckType checkType; //瓶子类型

    private String no; //编号

    private String volume; //容积

    private String weight; //瓶重

    private String goodsWeight;//物品重量

    private String pressure;//压力

    private String prodFactory;//生产厂家

    private Date prodDate; //生产日期

    private Date observeDate;//检测日期/试验日期

    private String isPass;//合格

    private String labelNo;//标签号

    @ToMany(referencedJoinProperty = "targetId")
    private List<YearCheckResult> checkResultList;

/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;

/** Used for active entity operations. */
@Generated(hash = 2028711350)
private transient BottleInfoDao myDao;

@Generated(hash = 708684117)
public BottleInfo(Long id, Long checkTypeId, String no, String volume,
        String weight, String goodsWeight, String pressure, String prodFactory,
        Date prodDate, Date observeDate, String isPass, String labelNo) {
    this.id = id;
    this.checkTypeId = checkTypeId;
    this.no = no;
    this.volume = volume;
    this.weight = weight;
    this.goodsWeight = goodsWeight;
    this.pressure = pressure;
    this.prodFactory = prodFactory;
    this.prodDate = prodDate;
    this.observeDate = observeDate;
    this.isPass = isPass;
    this.labelNo = labelNo;
}

@Generated(hash = 40571089)
public BottleInfo() {
}

public Long getId() {
    return this.id;
}

public void setId(Long id) {
    this.id = id;
}

public Long getCheckTypeId() {
    return this.checkTypeId;
}

public void setCheckTypeId(Long checkTypeId) {
    this.checkTypeId = checkTypeId;
}

public String getNo() {
    return this.no;
}

public void setNo(String no) {
    this.no = no;
}

public String getVolume() {
    return this.volume;
}

public void setVolume(String volume) {
    this.volume = volume;
}

public String getWeight() {
    return this.weight;
}

public void setWeight(String weight) {
    this.weight = weight;
}

public String getGoodsWeight() {
    return this.goodsWeight;
}

public void setGoodsWeight(String goodsWeight) {
    this.goodsWeight = goodsWeight;
}

public String getPressure() {
    return this.pressure;
}

public void setPressure(String pressure) {
    this.pressure = pressure;
}

public String getProdFactory() {
    return this.prodFactory;
}

public void setProdFactory(String prodFactory) {
    this.prodFactory = prodFactory;
}

public Date getProdDate() {
    return this.prodDate;
}

public void setProdDate(Date prodDate) {
    this.prodDate = prodDate;
}

public Date getObserveDate() {
    return this.observeDate;
}

public void setObserveDate(Date observeDate) {
    this.observeDate = observeDate;
}

public String getIsPass() {
    return this.isPass;
}

public void setIsPass(String isPass) {
    this.isPass = isPass;
}

public String getLabelNo() {
    return this.labelNo;
}

public void setLabelNo(String labelNo) {
    this.labelNo = labelNo;
}

@Generated(hash = 1822795957)
private transient Long checkType__resolvedKey;

/** To-one relationship, resolved on first access. */
@Generated(hash = 863167469)
public CheckType getCheckType() {
    Long __key = this.checkTypeId;
    if (checkType__resolvedKey == null
            || !checkType__resolvedKey.equals(__key)) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        CheckTypeDao targetDao = daoSession.getCheckTypeDao();
        CheckType checkTypeNew = targetDao.load(__key);
        synchronized (this) {
            checkType = checkTypeNew;
            checkType__resolvedKey = __key;
        }
    }
    return checkType;
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 599256709)
public void setCheckType(CheckType checkType) {
    synchronized (this) {
        this.checkType = checkType;
        checkTypeId = checkType == null ? null : checkType.getId();
        checkType__resolvedKey = checkTypeId;
    }
}

/**
 * To-many relationship, resolved on first access (and after reset).
 * Changes to to-many relations are not persisted, make changes to the target entity.
 */
@Generated(hash = 623968391)
public List<YearCheckResult> getCheckResultList() {
    if (checkResultList == null) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        YearCheckResultDao targetDao = daoSession.getYearCheckResultDao();
        List<YearCheckResult> checkResultListNew = targetDao
                ._queryBottleInfo_CheckResultList(id);
        synchronized (this) {
            if (checkResultList == null) {
                checkResultList = checkResultListNew;
            }
        }
    }
    return checkResultList;
}

/** Resets a to-many relationship, making the next get call to query for a fresh result. */
@Generated(hash = 1934777524)
public synchronized void resetCheckResultList() {
    checkResultList = null;
}

/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 128553479)
public void delete() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.delete(this);
}

/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 1942392019)
public void refresh() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.refresh(this);
}

/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 713229351)
public void update() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.update(this);
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 195501829)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getBottleInfoDao() : null;
}
}
