package com.hr.fire.inspection.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.hr.fire.inspection.dao.DaoSession;
import com.hr.fire.inspection.dao.YearCheckDao;
import com.hr.fire.inspection.dao.YearCheckResultDao;
import com.hr.fire.inspection.dao.CheckTypeDao;

import java.util.Date;
import com.hr.fire.inspection.dao.CompanyInfoDao;
import com.hr.fire.inspection.dao.ItemInfoDao;

@Entity(
        nameInDb = "t_year_check_result"
)
public class YearCheckResult   {

    @Id(autoincrement = true)
    private Long id;

    private Long yearCheckId; //外键

    @ToOne(joinProperty = "yearCheckId")
    private YearCheck yearCheck; //年检项目及标准

    private String isPass; // 合格

    private String imageUrl; //图片路径

    private String videoUrl; //视频路径

    private String description; //描述

    private String SystemNumber;//系统位号

    private String ProtectArea;//保护区域

    private Date checkDate;//检查日期

    //设备的ID
    private Long itemInfoId;
    @ToOne(joinProperty="itemInfoId")
    private ItemInfo itemInfo;

    private Long companyInfoId;
    @ToOne(joinProperty = "companyInfoId")
    private CompanyInfo companyInfo;//公司选择信息

    private Long checkTypeId;
    @ToOne(joinProperty = "checkTypeId")
    private CheckType checkType; //设备类型

    // 其他项的ID,这里就不做外键关联了
    private Long targetId;

/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;

/** Used for active entity operations. */
@Generated(hash = 232162975)
private transient YearCheckResultDao myDao;

@Generated(hash = 19132713)
public YearCheckResult(Long id, Long yearCheckId, String isPass,
        String imageUrl, String videoUrl, String description,
        String SystemNumber, String ProtectArea, Date checkDate,
        Long itemInfoId, Long companyInfoId, Long checkTypeId, Long targetId) {
    this.id = id;
    this.yearCheckId = yearCheckId;
    this.isPass = isPass;
    this.imageUrl = imageUrl;
    this.videoUrl = videoUrl;
    this.description = description;
    this.SystemNumber = SystemNumber;
    this.ProtectArea = ProtectArea;
    this.checkDate = checkDate;
    this.itemInfoId = itemInfoId;
    this.companyInfoId = companyInfoId;
    this.checkTypeId = checkTypeId;
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

public String getDescription() {
    return this.description;
}

public void setDescription(String description) {
    this.description = description;
}

public Long getItemInfoId() {
    return this.itemInfoId;
}

public void setItemInfoId(Long itemInfoId) {
    this.itemInfoId = itemInfoId;
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

@Generated(hash = 1822795957)
private transient Long checkType__resolvedKey;

@Generated(hash = 702142230)
private transient Long companyInfo__resolvedKey;

/** To-one relationship, resolved on first access. */
@Generated(hash = 1277888882)
public ItemInfo getItemInfo() {
    Long __key = this.itemInfoId;
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
@Generated(hash = 1653646858)
public void setItemInfo(ItemInfo itemInfo) {
    synchronized (this) {
        this.itemInfo = itemInfo;
        itemInfoId = itemInfo == null ? null : itemInfo.getId();
        itemInfo__resolvedKey = itemInfoId;
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

    @Override
    public String toString() {
        return "YearCheckResult{" +
                "id=" + id +
                ", yearCheckId=" + yearCheckId +
                ", yearCheck=" + yearCheck +
                ", isPass='" + isPass + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", description='" + description + '\'' +
                ", itemInfoId=" + itemInfoId +
                ", itemInfo=" + itemInfo +
                ", targetId=" + targetId +
                ", daoSession=" + daoSession +
                ", myDao=" + myDao +
                ", yearCheck__resolvedKey=" + yearCheck__resolvedKey +
                ", itemInfo__resolvedKey=" + itemInfo__resolvedKey +
                '}';
    }

    public Long getCheckTypeId() {
        return this.checkTypeId;
    }

    public void setCheckTypeId(Long checkTypeId) {
        this.checkTypeId = checkTypeId;
    }

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

    public String getSystemNumber() {
        return this.SystemNumber;
    }

    public void setSystemNumber(String SystemNumber) {
        this.SystemNumber = SystemNumber;
    }

    public String getProtectArea() {
        return this.ProtectArea;
    }

    public void setProtectArea(String ProtectArea) {
        this.ProtectArea = ProtectArea;
    }

    public Date getCheckDate() {
        return this.checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Long getCompanyInfoId() {
        return this.companyInfoId;
    }

    public void setCompanyInfoId(Long companyInfoId) {
        this.companyInfoId = companyInfoId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1193346340)
    public CompanyInfo getCompanyInfo() {
        Long __key = this.companyInfoId;
        if (companyInfo__resolvedKey == null
                || !companyInfo__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CompanyInfoDao targetDao = daoSession.getCompanyInfoDao();
            CompanyInfo companyInfoNew = targetDao.load(__key);
            synchronized (this) {
                companyInfo = companyInfoNew;
                companyInfo__resolvedKey = __key;
            }
        }
        return companyInfo;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 777983097)
    public void setCompanyInfo(CompanyInfo companyInfo) {
        synchronized (this) {
            this.companyInfo = companyInfo;
            companyInfoId = companyInfo == null ? null : companyInfo.getId();
            companyInfo__resolvedKey = companyInfoId;
        }
    }

}
