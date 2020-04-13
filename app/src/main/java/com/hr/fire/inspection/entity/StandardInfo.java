package com.hr.fire.inspection.entity;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.hr.fire.inspection.dao.DaoSession;
import com.hr.fire.inspection.dao.StandardTypeDao;
import com.hr.fire.inspection.dao.StandardInfoDao;

@Entity(
        nameInDb = "t_standard_info"
)
public class StandardInfo {

    @Id(autoincrement = true)
    private Long id;//法规id

    private Long standardTypeId;
    @ToOne(joinProperty = "standardTypeId")
    private StandardType standardType;//标准分类

    private String name;//法规名称

    private String path;//法规存放目录

    private String fileType;//法规文件类型

/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;

/** Used for active entity operations. */
@Generated(hash = 1383898226)
private transient StandardInfoDao myDao;

@Generated(hash = 1016483075)
public StandardInfo(Long id, Long standardTypeId, String name, String path,
        String fileType) {
    this.id = id;
    this.standardTypeId = standardTypeId;
    this.name = name;
    this.path = path;
    this.fileType = fileType;
}

@Generated(hash = 1393596397)
public StandardInfo() {
}

public Long getId() {
    return this.id;
}

public void setId(Long id) {
    this.id = id;
}

public Long getStandardTypeId() {
    return this.standardTypeId;
}

public void setStandardTypeId(Long standardTypeId) {
    this.standardTypeId = standardTypeId;
}

public String getName() {
    return this.name;
}

public void setName(String name) {
    this.name = name;
}

public String getPath() {
    return this.path;
}

public void setPath(String path) {
    this.path = path;
}

public String getFileType() {
    return this.fileType;
}

public void setFileType(String fileType) {
    this.fileType = fileType;
}

@Generated(hash = 18539822)
private transient Long standardType__resolvedKey;

/** To-one relationship, resolved on first access. */
@Generated(hash = 689736295)
public StandardType getStandardType() {
    Long __key = this.standardTypeId;
    if (standardType__resolvedKey == null
            || !standardType__resolvedKey.equals(__key)) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        StandardTypeDao targetDao = daoSession.getStandardTypeDao();
        StandardType standardTypeNew = targetDao.load(__key);
        synchronized (this) {
            standardType = standardTypeNew;
            standardType__resolvedKey = __key;
        }
    }
    return standardType;
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 1227013789)
public void setStandardType(StandardType standardType) {
    synchronized (this) {
        this.standardType = standardType;
        standardTypeId = standardType == null ? null : standardType.getId();
        standardType__resolvedKey = standardTypeId;
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
@Generated(hash = 948010742)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getStandardInfoDao() : null;
}


}
