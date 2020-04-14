package com.hr.fire.inspection.service;

import com.hr.fire.inspection.entity.CompanyInfo;

import java.util.List;

public interface CompanyInfoService extends BaseService<CompanyInfo> {
    /**
     * 获取公司平台信息数据信息
     * @param
     * @return
     */
    List<CompanyInfo> getAll();

    /**
     * 获取公司名列表
     * @param
     * @return
     */
    List<CompanyInfo> getCompanyList();

    /**
     * 获取油田名列表
     * @param
     * @return
     */
    List<CompanyInfo> getOilfieldList(String companyName);


    /**
     * 获取平台名列表
     * @param
     * @return
     */
    List<CompanyInfo> getPlatformList(String oilfieldName);

}
