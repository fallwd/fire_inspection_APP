package com.hr.fire.inspection.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.hr.fire.inspection.entity.CheckPerson;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.StandardInfo;
import com.hr.fire.inspection.entity.StandardType;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;
<<<<<<< HEAD
import com.hr.fire.inspection.entity.CheckPerson;
import com.hr.fire.inspection.entity.CompanyType;
import com.hr.fire.inspection.entity.InspectionResult;
=======
<<<<<<< HEAD
import com.hr.fire.inspection.entity.CheckPerson;
=======
>>>>>>> be2e63913f7d082439c2eb34ca3c024e4ea4f05b
>>>>>>> 9069637e91f76f416f6f181e0d49e06685d6224b

import com.hr.fire.inspection.dao.CheckPersonDao;
import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.CompanyInfoDao;
import com.hr.fire.inspection.dao.ItemInfoDao;
import com.hr.fire.inspection.dao.StandardInfoDao;
import com.hr.fire.inspection.dao.StandardTypeDao;
import com.hr.fire.inspection.dao.YearCheckDao;
import com.hr.fire.inspection.dao.YearCheckResultDao;
<<<<<<< HEAD
import com.hr.fire.inspection.dao.CheckPersonDao;
import com.hr.fire.inspection.dao.CompanyTypeDao;
import com.hr.fire.inspection.dao.InspectionResultDao;
=======
<<<<<<< HEAD
import com.hr.fire.inspection.dao.CheckPersonDao;
=======
>>>>>>> be2e63913f7d082439c2eb34ca3c024e4ea4f05b
>>>>>>> 9069637e91f76f416f6f181e0d49e06685d6224b

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig checkPersonDaoConfig;
    private final DaoConfig checkTypeDaoConfig;
    private final DaoConfig companyInfoDaoConfig;
    private final DaoConfig itemInfoDaoConfig;
    private final DaoConfig standardInfoDaoConfig;
    private final DaoConfig standardTypeDaoConfig;
    private final DaoConfig yearCheckDaoConfig;
    private final DaoConfig yearCheckResultDaoConfig;
<<<<<<< HEAD
    private final DaoConfig checkPersonDaoConfig;
    private final DaoConfig companyTypeDaoConfig;
    private final DaoConfig inspectionResultDaoConfig;
=======
<<<<<<< HEAD
    private final DaoConfig checkPersonDaoConfig;
=======
>>>>>>> be2e63913f7d082439c2eb34ca3c024e4ea4f05b
>>>>>>> 9069637e91f76f416f6f181e0d49e06685d6224b

    private final CheckPersonDao checkPersonDao;
    private final CheckTypeDao checkTypeDao;
    private final CompanyInfoDao companyInfoDao;
    private final ItemInfoDao itemInfoDao;
    private final StandardInfoDao standardInfoDao;
    private final StandardTypeDao standardTypeDao;
    private final YearCheckDao yearCheckDao;
    private final YearCheckResultDao yearCheckResultDao;
<<<<<<< HEAD
    private final CheckPersonDao checkPersonDao;
    private final CompanyTypeDao companyTypeDao;
    private final InspectionResultDao inspectionResultDao;
=======
<<<<<<< HEAD
    private final CheckPersonDao checkPersonDao;
=======
>>>>>>> be2e63913f7d082439c2eb34ca3c024e4ea4f05b
>>>>>>> 9069637e91f76f416f6f181e0d49e06685d6224b

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        checkPersonDaoConfig = daoConfigMap.get(CheckPersonDao.class).clone();
        checkPersonDaoConfig.initIdentityScope(type);

        checkTypeDaoConfig = daoConfigMap.get(CheckTypeDao.class).clone();
        checkTypeDaoConfig.initIdentityScope(type);

        companyInfoDaoConfig = daoConfigMap.get(CompanyInfoDao.class).clone();
        companyInfoDaoConfig.initIdentityScope(type);

        itemInfoDaoConfig = daoConfigMap.get(ItemInfoDao.class).clone();
        itemInfoDaoConfig.initIdentityScope(type);

        standardInfoDaoConfig = daoConfigMap.get(StandardInfoDao.class).clone();
        standardInfoDaoConfig.initIdentityScope(type);

        standardTypeDaoConfig = daoConfigMap.get(StandardTypeDao.class).clone();
        standardTypeDaoConfig.initIdentityScope(type);

        yearCheckDaoConfig = daoConfigMap.get(YearCheckDao.class).clone();
        yearCheckDaoConfig.initIdentityScope(type);

        yearCheckResultDaoConfig = daoConfigMap.get(YearCheckResultDao.class).clone();
        yearCheckResultDaoConfig.initIdentityScope(type);
<<<<<<< HEAD

        checkPersonDaoConfig = daoConfigMap.get(CheckPersonDao.class).clone();
        checkPersonDaoConfig.initIdentityScope(type);
<<<<<<< HEAD

        companyTypeDaoConfig = daoConfigMap.get(CompanyTypeDao.class).clone();
        companyTypeDaoConfig.initIdentityScope(type);

        inspectionResultDaoConfig = daoConfigMap.get(InspectionResultDao.class).clone();
        inspectionResultDaoConfig.initIdentityScope(type);
=======
=======
>>>>>>> be2e63913f7d082439c2eb34ca3c024e4ea4f05b
>>>>>>> 9069637e91f76f416f6f181e0d49e06685d6224b

        checkPersonDao = new CheckPersonDao(checkPersonDaoConfig, this);
        checkTypeDao = new CheckTypeDao(checkTypeDaoConfig, this);
        companyInfoDao = new CompanyInfoDao(companyInfoDaoConfig, this);
        itemInfoDao = new ItemInfoDao(itemInfoDaoConfig, this);
        standardInfoDao = new StandardInfoDao(standardInfoDaoConfig, this);
        standardTypeDao = new StandardTypeDao(standardTypeDaoConfig, this);
        yearCheckDao = new YearCheckDao(yearCheckDaoConfig, this);
        yearCheckResultDao = new YearCheckResultDao(yearCheckResultDaoConfig, this);
<<<<<<< HEAD
        checkPersonDao = new CheckPersonDao(checkPersonDaoConfig, this);
        companyTypeDao = new CompanyTypeDao(companyTypeDaoConfig, this);
        inspectionResultDao = new InspectionResultDao(inspectionResultDaoConfig, this);
=======
<<<<<<< HEAD
        checkPersonDao = new CheckPersonDao(checkPersonDaoConfig, this);
=======
>>>>>>> be2e63913f7d082439c2eb34ca3c024e4ea4f05b
>>>>>>> 9069637e91f76f416f6f181e0d49e06685d6224b

        registerDao(CheckPerson.class, checkPersonDao);
        registerDao(CheckType.class, checkTypeDao);
        registerDao(CompanyInfo.class, companyInfoDao);
        registerDao(ItemInfo.class, itemInfoDao);
        registerDao(StandardInfo.class, standardInfoDao);
        registerDao(StandardType.class, standardTypeDao);
        registerDao(YearCheck.class, yearCheckDao);
        registerDao(YearCheckResult.class, yearCheckResultDao);
<<<<<<< HEAD
        registerDao(CheckPerson.class, checkPersonDao);
        registerDao(CompanyType.class, companyTypeDao);
        registerDao(InspectionResult.class, inspectionResultDao);
=======
<<<<<<< HEAD
        registerDao(CheckPerson.class, checkPersonDao);
=======
>>>>>>> be2e63913f7d082439c2eb34ca3c024e4ea4f05b
>>>>>>> 9069637e91f76f416f6f181e0d49e06685d6224b
    }
    
    public void clear() {
        checkPersonDaoConfig.clearIdentityScope();
        checkTypeDaoConfig.clearIdentityScope();
        companyInfoDaoConfig.clearIdentityScope();
        itemInfoDaoConfig.clearIdentityScope();
        standardInfoDaoConfig.clearIdentityScope();
        standardTypeDaoConfig.clearIdentityScope();
        yearCheckDaoConfig.clearIdentityScope();
        yearCheckResultDaoConfig.clearIdentityScope();
<<<<<<< HEAD
        checkPersonDaoConfig.clearIdentityScope();
        companyTypeDaoConfig.clearIdentityScope();
        inspectionResultDaoConfig.clearIdentityScope();
=======
<<<<<<< HEAD
        checkPersonDaoConfig.clearIdentityScope();
=======
    }

    public CheckPersonDao getCheckPersonDao() {
        return checkPersonDao;
>>>>>>> be2e63913f7d082439c2eb34ca3c024e4ea4f05b
>>>>>>> 9069637e91f76f416f6f181e0d49e06685d6224b
    }

    public CheckTypeDao getCheckTypeDao() {
        return checkTypeDao;
    }

    public CompanyInfoDao getCompanyInfoDao() {
        return companyInfoDao;
    }

    public ItemInfoDao getItemInfoDao() {
        return itemInfoDao;
    }

    public StandardInfoDao getStandardInfoDao() {
        return standardInfoDao;
    }

    public StandardTypeDao getStandardTypeDao() {
        return standardTypeDao;
    }

    public YearCheckDao getYearCheckDao() {
        return yearCheckDao;
    }

    public YearCheckResultDao getYearCheckResultDao() {
        return yearCheckResultDao;
    }

<<<<<<< HEAD
    public CheckPersonDao getCheckPersonDao() {
        return checkPersonDao;
    }

<<<<<<< HEAD
    public CompanyTypeDao getCompanyTypeDao() {
        return companyTypeDao;
    }

    public InspectionResultDao getInspectionResultDao() {
        return inspectionResultDao;
    }

=======
=======
>>>>>>> be2e63913f7d082439c2eb34ca3c024e4ea4f05b
>>>>>>> 9069637e91f76f416f6f181e0d49e06685d6224b
}
