package com.hr.fire.inspection.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.CompanyInfo;
import com.hr.fire.inspection.entity.CompanyType;
import com.hr.fire.inspection.entity.InspectionResult;
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
>>>>>>> a04a50a42e8f214c7104bc5a591b87ed0e1c9c80

import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.CompanyInfoDao;
import com.hr.fire.inspection.dao.CompanyTypeDao;
import com.hr.fire.inspection.dao.InspectionResultDao;
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
>>>>>>> a04a50a42e8f214c7104bc5a591b87ed0e1c9c80

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig checkTypeDaoConfig;
    private final DaoConfig companyInfoDaoConfig;
    private final DaoConfig companyTypeDaoConfig;
    private final DaoConfig inspectionResultDaoConfig;
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
>>>>>>> a04a50a42e8f214c7104bc5a591b87ed0e1c9c80

    private final CheckTypeDao checkTypeDao;
    private final CompanyInfoDao companyInfoDao;
    private final CompanyTypeDao companyTypeDao;
    private final InspectionResultDao inspectionResultDao;
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
>>>>>>> a04a50a42e8f214c7104bc5a591b87ed0e1c9c80

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        checkTypeDaoConfig = daoConfigMap.get(CheckTypeDao.class).clone();
        checkTypeDaoConfig.initIdentityScope(type);

        companyInfoDaoConfig = daoConfigMap.get(CompanyInfoDao.class).clone();
        companyInfoDaoConfig.initIdentityScope(type);

        companyTypeDaoConfig = daoConfigMap.get(CompanyTypeDao.class).clone();
        companyTypeDaoConfig.initIdentityScope(type);

        inspectionResultDaoConfig = daoConfigMap.get(InspectionResultDao.class).clone();
        inspectionResultDaoConfig.initIdentityScope(type);

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

        companyTypeDaoConfig = daoConfigMap.get(CompanyTypeDao.class).clone();
        companyTypeDaoConfig.initIdentityScope(type);

        inspectionResultDaoConfig = daoConfigMap.get(InspectionResultDao.class).clone();
        inspectionResultDaoConfig.initIdentityScope(type);
=======
>>>>>>> a04a50a42e8f214c7104bc5a591b87ed0e1c9c80

        checkTypeDao = new CheckTypeDao(checkTypeDaoConfig, this);
        companyInfoDao = new CompanyInfoDao(companyInfoDaoConfig, this);
        companyTypeDao = new CompanyTypeDao(companyTypeDaoConfig, this);
        inspectionResultDao = new InspectionResultDao(inspectionResultDaoConfig, this);
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
>>>>>>> a04a50a42e8f214c7104bc5a591b87ed0e1c9c80

        registerDao(CheckType.class, checkTypeDao);
        registerDao(CompanyInfo.class, companyInfoDao);
        registerDao(CompanyType.class, companyTypeDao);
        registerDao(InspectionResult.class, inspectionResultDao);
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
>>>>>>> a04a50a42e8f214c7104bc5a591b87ed0e1c9c80
    }
    
    public void clear() {
        checkTypeDaoConfig.clearIdentityScope();
        companyInfoDaoConfig.clearIdentityScope();
        companyTypeDaoConfig.clearIdentityScope();
        inspectionResultDaoConfig.clearIdentityScope();
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
    }

    public CheckPersonDao getCheckPersonDao() {
        return checkPersonDao;
>>>>>>> a04a50a42e8f214c7104bc5a591b87ed0e1c9c80
    }

    public CheckTypeDao getCheckTypeDao() {
        return checkTypeDao;
    }

    public CompanyInfoDao getCompanyInfoDao() {
        return companyInfoDao;
    }

    public CompanyTypeDao getCompanyTypeDao() {
        return companyTypeDao;
    }

    public InspectionResultDao getInspectionResultDao() {
        return inspectionResultDao;
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

    public CompanyTypeDao getCompanyTypeDao() {
        return companyTypeDao;
    }

    public InspectionResultDao getInspectionResultDao() {
        return inspectionResultDao;
    }

=======
>>>>>>> a04a50a42e8f214c7104bc5a591b87ed0e1c9c80
}
