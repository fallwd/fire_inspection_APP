package com.hr.fire.inspection.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;

import javax.xml.transform.sax.SAXResult;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.hr.fire.inspection.dao.DaoSession;
import com.hr.fire.inspection.dao.CompanyInfoDao;
import com.hr.fire.inspection.dao.CheckTypeDao;
import com.hr.fire.inspection.dao.InspectionResultDao;

@Entity(
        nameInDb = "t_inspection_result"
)
public class InspectionResult {

    @Id(autoincrement = true)
    private Long id;

//    private Long checkPersonId;
//    @ToOne(joinProperty = "checkTypeId")
//    private CheckPerson checkPerson; //检查人信息

    private Long checkTypeId;
    @ToOne(joinProperty = "checkTypeId")
    private CheckType checkType; //设备类型

    private Long companyInfoId;
    @ToOne(joinProperty = "companyInfoId")
    private CompanyInfo companyInfo;//公司选择信息

    private String profession;//专业

    private String checkPerson;//检查人

    private Date checkDate;//检查日期

    private String description;//隐患描述

    private String imgPath;//照片路径

    private String param1;//excel-B列
    private String param2;//excel-C列
    private String param3;//excel-D列
    private String param4;//excel-E列
    private String param5;//excel-F列
    private String param6;//excel-G列
    private String param7;//excel-H列
    private String param8;//excel-I列
    private String param9;//excel-J列
    private String param10;//excel-K列
    private String param11;//excel-L列
    private String param12;//excel-M列
    private String param13;//excel-N列
    private String param14;//excel-O列
    private String param15;//excel-P列
    private String param16;//excel-Q列
    private String param17;//excel-R列
    private String param18;//excel-S列
    private String param19;//excel-T列
    private String param20;//excel-V列
    private String param21;//excel-W列
    private String param22;//excel-X列
    private String param23;//excel-Y列
    private String param24;//excel-Z列
    private String param25;//excel-AA列
    private String param26;//excel-AB列

/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;

/** Used for active entity operations. */
@Generated(hash = 138421621)
private transient InspectionResultDao myDao;
@Generated(hash = 872787651)
public InspectionResult(Long id, Long checkTypeId, Long companyInfoId,
        String profession, String checkPerson, Date checkDate,
        String description, String imgPath, String param1, String param2,
        String param3, String param4, String param5, String param6,
        String param7, String param8, String param9, String param10,
        String param11, String param12, String param13, String param14,
        String param15, String param16, String param17, String param18,
        String param19, String param20, String param21, String param22,
        String param23, String param24, String param25, String param26) {
    this.id = id;
    this.checkTypeId = checkTypeId;
    this.companyInfoId = companyInfoId;
    this.profession = profession;
    this.checkPerson = checkPerson;
    this.checkDate = checkDate;
    this.description = description;
    this.imgPath = imgPath;
    this.param1 = param1;
    this.param2 = param2;
    this.param3 = param3;
    this.param4 = param4;
    this.param5 = param5;
    this.param6 = param6;
    this.param7 = param7;
    this.param8 = param8;
    this.param9 = param9;
    this.param10 = param10;
    this.param11 = param11;
    this.param12 = param12;
    this.param13 = param13;
    this.param14 = param14;
    this.param15 = param15;
    this.param16 = param16;
    this.param17 = param17;
    this.param18 = param18;
    this.param19 = param19;
    this.param20 = param20;
    this.param21 = param21;
    this.param22 = param22;
    this.param23 = param23;
    this.param24 = param24;
    this.param25 = param25;
    this.param26 = param26;
}
@Generated(hash = 1991225468)
public InspectionResult() {
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
public Long getCompanyInfoId() {
    return this.companyInfoId;
}
public void setCompanyInfoId(Long companyInfoId) {
    this.companyInfoId = companyInfoId;
}
public String getProfession() {
    return this.profession;
}
public void setProfession(String profession) {
    this.profession = profession;
}
public String getCheckPerson() {
    return this.checkPerson;
}
public void setCheckPerson(String checkPerson) {
    this.checkPerson = checkPerson;
}
public Date getCheckDate() {
    return this.checkDate;
}
public void setCheckDate(Date checkDate) {
    this.checkDate = checkDate;
}
public String getDescription() {
    return this.description;
}
public void setDescription(String description) {
    this.description = description;
}
public String getImgPath() {
    return this.imgPath;
}
public void setImgPath(String imgPath) {
    this.imgPath = imgPath;
}
public String getParam1() {
    return this.param1;
}
public void setParam1(String param1) {
    this.param1 = param1;
}
public String getParam2() {
    return this.param2;
}
public void setParam2(String param2) {
    this.param2 = param2;
}
public String getParam3() {
    return this.param3;
}
public void setParam3(String param3) {
    this.param3 = param3;
}
public String getParam4() {
    return this.param4;
}
public void setParam4(String param4) {
    this.param4 = param4;
}
public String getParam5() {
    return this.param5;
}
public void setParam5(String param5) {
    this.param5 = param5;
}
public String getParam6() {
    return this.param6;
}
public void setParam6(String param6) {
    this.param6 = param6;
}
public String getParam7() {
    return this.param7;
}
public void setParam7(String param7) {
    this.param7 = param7;
}
public String getParam8() {
    return this.param8;
}
public void setParam8(String param8) {
    this.param8 = param8;
}
public String getParam9() {
    return this.param9;
}
public void setParam9(String param9) {
    this.param9 = param9;
}
public String getParam10() {
    return this.param10;
}
public void setParam10(String param10) {
    this.param10 = param10;
}
public String getParam11() {
    return this.param11;
}
public void setParam11(String param11) {
    this.param11 = param11;
}
public String getParam12() {
    return this.param12;
}
public void setParam12(String param12) {
    this.param12 = param12;
}
public String getParam13() {
    return this.param13;
}
public void setParam13(String param13) {
    this.param13 = param13;
}
public String getParam14() {
    return this.param14;
}
public void setParam14(String param14) {
    this.param14 = param14;
}
public String getParam15() {
    return this.param15;
}
public void setParam15(String param15) {
    this.param15 = param15;
}
public String getParam16() {
    return this.param16;
}
public void setParam16(String param16) {
    this.param16 = param16;
}
public String getParam17() {
    return this.param17;
}
public void setParam17(String param17) {
    this.param17 = param17;
}
public String getParam18() {
    return this.param18;
}
public void setParam18(String param18) {
    this.param18 = param18;
}
public String getParam19() {
    return this.param19;
}
public void setParam19(String param19) {
    this.param19 = param19;
}
public String getParam20() {
    return this.param20;
}
public void setParam20(String param20) {
    this.param20 = param20;
}
public String getParam21() {
    return this.param21;
}
public void setParam21(String param21) {
    this.param21 = param21;
}
public String getParam22() {
    return this.param22;
}
public void setParam22(String param22) {
    this.param22 = param22;
}
public String getParam23() {
    return this.param23;
}
public void setParam23(String param23) {
    this.param23 = param23;
}
public String getParam24() {
    return this.param24;
}
public void setParam24(String param24) {
    this.param24 = param24;
}
public String getParam25() {
    return this.param25;
}
public void setParam25(String param25) {
    this.param25 = param25;
}
public String getParam26() {
    return this.param26;
}
public void setParam26(String param26) {
    this.param26 = param26;
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
@Generated(hash = 702142230)
private transient Long companyInfo__resolvedKey;
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
@Generated(hash = 537693467)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getInspectionResultDao() : null;
}

    @Override
    public String toString() {
        return "InspectionResult{" +
                "id=" + id +
                ", checkTypeId=" + checkTypeId +
                ", checkType=" + checkType +
                ", companyInfoId=" + companyInfoId +
                ", companyInfo=" + companyInfo +
                ", profession='" + profession + '\'' +
                ", checkPerson='" + checkPerson + '\'' +
                ", checkDate=" + checkDate +
                ", description='" + description + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", param1='" + param1 + '\'' +
                ", param2='" + param2 + '\'' +
                ", param3='" + param3 + '\'' +
                ", param4='" + param4 + '\'' +
                ", param5='" + param5 + '\'' +
                ", param6='" + param6 + '\'' +
                ", param7='" + param7 + '\'' +
                ", param8='" + param8 + '\'' +
                ", param9='" + param9 + '\'' +
                ", param10='" + param10 + '\'' +
                ", param11='" + param11 + '\'' +
                ", param12='" + param12 + '\'' +
                ", param13='" + param13 + '\'' +
                ", param14='" + param14 + '\'' +
                ", param15='" + param15 + '\'' +
                ", param16='" + param16 + '\'' +
                ", param17='" + param17 + '\'' +
                ", param18='" + param18 + '\'' +
                ", param19='" + param19 + '\'' +
                ", param20='" + param20 + '\'' +
                ", param21='" + param21 + '\'' +
                ", param22='" + param22 + '\'' +
                ", param23='" + param23 + '\'' +
                ", param24='" + param24 + '\'' +
                ", param25='" + param25 + '\'' +
                ", param26='" + param26 + '\'' +
                '}';
    }
}
