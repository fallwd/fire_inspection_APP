package com.hr.fire.inspection.service;

import com.hr.fire.inspection.service.impl.CompanyInfoServiceImpl;
import com.hr.fire.inspection.service.impl.YearCheckServiceImpl;

public class ServiceFactory {

    public static YearCheckService getYearCheckService() {
        return new YearCheckServiceImpl();
    }

    public static CompanyInfoService getCompanyInfoService() {
        return new CompanyInfoServiceImpl();
    }

}
