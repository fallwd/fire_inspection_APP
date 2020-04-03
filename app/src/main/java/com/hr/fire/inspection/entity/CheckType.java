package com.hr.fire.inspection.entity;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;


import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.DaoSession;

@Entity(
        nameInDb = "t_check_type"
)
public class CheckType {

    @Id(autoincrement = true)
    private Long id;

    private String name; //类型名称

    private int type; //年检 月检 巡检等(1 年检)

    private Long parentId;

    @ToOne(joinProperty = "parentId")
    private CheckType parent;

/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;

/** Used for active entity operations. */
@Generated(hash = 1270543700)
private transient CheckTypeDao myDao;

@Generated(hash = 119751077)
public CheckType(Long id, String name, int type, Long parentId) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.parentId = parentId;
}

@Generated(hash = 281386950)
public CheckType() {
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

@Generated(hash = 1293412156)
private transient Long parent__resolvedKey;

/** To-one relationship, resolved on first access. */
@Generated(hash = 38821098)
public CheckType getParent() {
    Long __key = this.parentId;
    if (parent__resolvedKey == null || !parent__resolvedKey.equals(__key)) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        CheckTypeDao targetDao = daoSession.getCheckTypeDao();
        CheckType parentNew = targetDao.load(__key);
        synchronized (this) {
            parent = parentNew;
            parent__resolvedKey = __key;
        }
    }
    return parent;
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 2094832494)
public void setParent(CheckType parent) {
    synchronized (this) {
        this.parent = parent;
        parentId = parent == null ? null : parent.getId();
        parent__resolvedKey = parentId;
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
@Generated(hash = 1736019214)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getCheckTypeDao() : null;
}


}
