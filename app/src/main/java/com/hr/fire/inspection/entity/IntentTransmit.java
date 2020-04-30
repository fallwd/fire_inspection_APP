package com.hr.fire.inspection.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 页面跳转统使用对象进行传参
 */
public class IntentTransmit implements Serializable {

    public long companyInfoId;  //公司ID
    public long systemId;  //系统ID
    public Date srt_Date;  //巡检时间
    public String number;  //系统位号


    public long id;  //平台id
    public long type;  //1年检, 2巡检
    public long parentId;
    public String name;  //药剂瓶\氮气瓶\高压二氧化碳灭火系统

    @Override
    public String toString() {
        return "IntentTransmit{" +
                ", companyInfoId=" + companyInfoId +
                ", systemId=" + systemId +
                ", srt_Date='" + srt_Date + '\'' +
                ", id=" + id +
                ", type=" + type +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                '}';
    }
}