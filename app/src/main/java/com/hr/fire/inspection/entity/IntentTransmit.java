package com.hr.fire.inspection.entity;

import java.io.Serializable;

/**
 * 页面跳转统使用对象进行传参
 */
public class IntentTransmit implements Serializable {

    public long platform_id;  //平台id
    public long companyInfoId;  //公司ID
    public long systemId;  //系统ID
    public String srt_Date;  //巡检时间

    @Override
    public String toString() {
        return "IntentTransmit{" +
                "platform_id=" + platform_id +
                ", companyInfoId=" + companyInfoId +
                ", systemId=" + systemId +
                ", srt_Date='" + srt_Date + '\'' +
                '}';
    }
}
