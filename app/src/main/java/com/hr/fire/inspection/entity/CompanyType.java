package com.hr.fire.inspection.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.hr.fire.inspection.dao.DaoSession;
import com.hr.fire.inspection.dao.CompanyTypeDao;

@Entity(
        nameInDb = "t_company_check"
)
public class CompanyType {
    @Id(autoincrement = true)
    private Long id;

    private String name;//名称

    private int type;// 1公司 2油田 3平台

    private Long parentId;

    @ToOne(joinProperty = "parentId")
    private CompanyType companyType;

/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;

/** Used for active entity operations. */
@Generated(hash = 2012024077)
private transient CompanyTypeDao myDao;

@Generated(hash = 1597874674)
public CompanyType(Long id, String name, int type, Long parentId) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.parentId = parentId;
}

@Generated(hash = 177598857)
public CompanyType() {
}

public Long getId() {
    return this.id;
}

public void setId(Long id) {
    this.id = id;
}

public String getName() {
    return this.name;
}

public void setName(String name) {
    this.name = name;
}

public int getType() {
    return this.type;
}

public void setType(int type) {
    this.type = type;
}

public Long getParentId() {
    return this.parentId;
}

public void setParentId(Long parentId) {
    this.parentId = parentId;
}

@Generated(hash = 1034066450)
private transient Long companyType__resolvedKey;

/** To-one relationship, resolved on first access. */
@Generated(hash = 1711028100)
public CompanyType getCompanyType() {
    Long __key = this.parentId;
    if (companyType__resolvedKey == null
            || !companyType__resolvedKey.equals(__key)) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        CompanyTypeDao targetDao = daoSession.getCompanyTypeDao();
        CompanyType companyTypeNew = targetDao.load(__key);
        synchronized (this) {
            companyType = companyTypeNew;
            companyType__resolvedKey = __key;
        }
    }
    return companyType;
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 907982089)
public void setCompanyType(CompanyType companyType) {
    synchronized (this) {
        this.companyType = companyType;
        parentId = companyType == null ? null : companyType.getId();
        companyType__resolvedKey = parentId;
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
@Generated(hash = 647708127)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getCompanyTypeDao() : null;
}
}
