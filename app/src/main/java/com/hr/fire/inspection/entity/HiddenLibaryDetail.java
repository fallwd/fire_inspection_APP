package com.hr.fire.inspection.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 页面跳转统使用对象进行传参
 */
public class HiddenLibaryDetail implements Serializable {

    public long platformId;  //公司ID
    public long systemId;  //系统ID
    public String checkDate;
    public String systemNumber;
    public String protectArea;
    public String checkPerson;
    public String profession;


    @Override
    public String toString() {
        return "HiddenLibaryDetail{" +
                ", platformId=" + platformId +
                ", systemId=" + systemId +
                ", systemNumber='" + systemNumber + '\'' +
                ", checkDate=" + checkDate +
                ", protectArea=" + protectArea +
                ", checkPerson=" + checkPerson +
                ", profession=" + profession +
                '}';
    }
}