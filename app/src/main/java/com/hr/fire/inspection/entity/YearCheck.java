package com.hr.fire.inspection.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.YearCheckDao;
import com.hr.fire.inspection.dao.DaoSession;

@Entity(
        nameInDb = "t_year_check",
        indexes = {
            @Index(value = "checkTypeId")
        }
)
public class YearCheck {

    @Id(autoincrement = true)
    private Long id;

    private Long checkTypeId; //检查目标

    @ToOne(joinProperty = "checkTypeId")
    private CheckType checkType;

    private String project; //检查项目

    private String content; //检查内容

    private String requirement; //检查要求

    private String standard; //检查标准

/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;

/** Used for active entity operations. */
@Generated(hash = 81198782)
private transient YearCheckDao myDao;

@Generated(hash = 771786218)
public YearCheck(Long id, Long checkTypeId, String project, String content,
        String requirement, String standard) {
    this.id = id;
    this.checkTypeId = checkTypeId;
    this.project = project;
    this.content = content;
    this.requirement = requirement;
    this.standard = standard;
}

@Generated(hash = 1759475232)
public YearCheck() {
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

public String getProject() {
    return this.project;
}

public void setProject(String project) {
    this.project = project;
}

public String getContent() {
    return this.content;
}

public void setContent(String content) {
    this.content = content;
}

public String getRequirement() {
    return this.requirement;
}

public void setRequirement(String requirement) {
    this.requirement = requirement;
}

public String getStandard() {
    return this.standard;
}

public void setStandard(String standard) {
    this.standard = standard;
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
@Generated(hash = 2084853646)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getYearCheckDao() : null;
}

    @Override
    public String toString() {
        return "YearCheck{" +
                "id=" + id +
                ", checkTypeId=" + checkTypeId +
                ", checkType=" + checkType +
                ", project='" + project + '\'' +
                ", content='" + content + '\'' +
                ", requirement='" + requirement + '\'' +
                ", standard='" + standard + '\'' +
                '}';
    }

}
