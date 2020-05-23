package com.hr.fire.inspection.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

@Entity(
        nameInDb = "t_company_info"
)
public class CompanyInfo {

    @Id(autoincrement = true)
    private Long id;

    private String companyName;//公司名

    private String oilfieldName;//油田名

    private String platformName;//平台名

    private int isNecessary;//是否必要

    @Unique
    private String uuid;

@Generated(hash = 130895209)
public CompanyInfo(Long id, String companyName, String oilfieldName,
        String platformName, int isNecessary, String uuid) {
    this.id = id;
    this.companyName = companyName;
    this.oilfieldName = oilfieldName;
    this.platformName = platformName;
    this.isNecessary = isNecessary;
    this.uuid = uuid;
}

@Generated(hash = 1062273323)
public CompanyInfo() {
}

public Long getId() {
    return this.id;
}

public void setId(Long id) {
    this.id = id;
}

public String getCompanyName() {
    return this.companyName;
}

public void setCompanyName(String companyName) {
    this.companyName = companyName;
}

public String getOilfieldName() {
    return this.oilfieldName;
}

public void setOilfieldName(String oilfieldName) {
    this.oilfieldName = oilfieldName;
}

public String getPlatformName() {
    return this.platformName;
}

public void setPlatformName(String platformName) {
    this.platformName = platformName;
}

public int getIsNecessary() {
    return this.isNecessary;
}

public void setIsNecessary(int isNecessary) {
    this.isNecessary = isNecessary;
}

    @Override
    public String toString() {
        return "CompanyInfo{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", oilfieldName='" + oilfieldName + '\'' +
                ", platformName='" + platformName + '\'' +
                ", isNecessary=" + isNecessary +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    public String getUuid() {
    return this.uuid;
}

public void setUuid(String uuid) {
    this.uuid = uuid;
}
}
