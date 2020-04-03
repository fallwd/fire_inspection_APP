package com.hr.fire.inspection.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.hr.fire.inspection.dao.DaoSession;
import com.hr.fire.inspection.dao.ItemInfoDao;
import com.hr.fire.inspection.dao.YearCheckDao;
import com.hr.fire.inspection.dao.YearCheckResultDao;

@Entity(
        nameInDb = "t_year_check_result"
)
public class YearCheckResult {

    @Id(autoincrement = true)
    private Long id;

    private Long yearCheckId; //外键

    @ToOne(joinProperty = "yearCheckId")
    private YearCheck yearCheck; //年检项目及标准

    private String isPass; // 合格

    private String imageUrl; //图片路径

    private String videoUrl; //视频路径

    private String descrption; //描述

    //瓶子的ID
    private Long bottleInfoId;

    @ToOne(joinProperty="bottleInfoId")
    private ItemInfo itemInfo;

    // 其他项的ID,这里就不做外键关联了
    private Long targetId;

/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;

/** Used for active entity operations. */
@Generated(hash = 232162975)
private transient YearCheckResultDao myDao;

@Generated(hash = 236090881)
public YearCheckResult(Long id, Long yearCheckId, String isPass,
        String imageUrl, String videoUrl, String descrption, Long bottleInfoId,
        Long targetId) {
    this.id = id;
    this.yearCheckId = yearCheckId;
    this.isPass = isPass;
    this.imageUrl = imageUrl;
    this.videoUrl = videoUrl;
    this.descrption = descrption;
    this.bottleInfoId = bottleInfoId;
    this.targetId = targetId;
}

@Generated(hash = 1676636117)
public YearCheckResult() {
}

public Long getId() {
    return this.id;
}

public void setId(Long id) {
    this.id = id;
}

public Long getYearCheckId() {
    return this.yearCheckId;
}

public void setYearCheckId(Long yearCheckId) {
    this.yearCheckId = yearCheckId;
}

public String getIsPass() {
    return this.isPass;
}

public void setIsPass(String isPass) {
    this.isPass = isPass;
}

public String getImageUrl() {
    return this.imageUrl;
}

public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
}

public String getVideoUrl() {
    return this.videoUrl;
}

public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
}

public String getDescrption() {
    return this.descrption;
}

public void setDescrption(String descrption) {
    this.descrption = descrption;
}

public Long getBottleInfoId() {
    return this.bottleInfoId;
}

public void setBottleInfoId(Long bottleInfoId) {
    this.bottleInfoId = bottleInfoId;
}

public Long getTargetId() {
    return this.targetId;
}

public void setTargetId(Long targetId) {
    this.targetId = targetId;
}

@Generated(hash = 1038162817)
private transient Long yearCheck__resolvedKey;

/** To-one relationship, resolved on first access. */
@Generated(hash = 1810697084)
public YearCheck getYearCheck() {
    Long __key = this.yearCheckId;
    if (yearCheck__resolvedKey == null
            || !yearCheck__resolvedKey.equals(__key)) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        YearCheckDao targetDao = daoSession.getYearCheckDao();
        YearCheck yearCheckNew = targetDao.load(__key);
        synchronized (this) {
            yearCheck = yearCheckNew;
            yearCheck__resolvedKey = __key;
        }
    }
    return yearCheck;
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 1718341194)
public void setYearCheck(YearCheck yearCheck) {
    synchronized (this) {
        this.yearCheck = yearCheck;
        yearCheckId = yearCheck == null ? null : yearCheck.getId();
        yearCheck__resolvedKey = yearCheckId;
    }
}

@Generated(hash = 2126539768)
private transient Long itemInfo__resolvedKey;

/** To-one relationship, resolved on first access. */
@Generated(hash = 1997461449)
public ItemInfo getItemInfo() {
    Long __key = this.bottleInfoId;
    if (itemInfo__resolvedKey == null || !itemInfo__resolvedKey.equals(__key)) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        ItemInfoDao targetDao = daoSession.getItemInfoDao();
        ItemInfo itemInfoNew = targetDao.load(__key);
        synchronized (this) {
            itemInfo = itemInfoNew;
            itemInfo__resolvedKey = __key;
        }
    }
    return itemInfo;
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 2051489145)
public void setItemInfo(ItemInfo itemInfo) {
    synchronized (this) {
        this.itemInfo = itemInfo;
        bottleInfoId = itemInfo == null ? null : itemInfo.getId();
        itemInfo__resolvedKey = bottleInfoId;
    }
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
@Generated(hash = 843266831)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getYearCheckResultDao() : null;
}


}
