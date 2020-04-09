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
}
