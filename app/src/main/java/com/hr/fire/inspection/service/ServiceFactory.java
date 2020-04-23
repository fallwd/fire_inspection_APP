package com.hr.fire.inspection.service;

import com.hr.fire.inspection.service.impl.AnalysisServiceImpl;
import com.hr.fire.inspection.service.impl.CompanyInfoServiceImpl;
import com.hr.fire.inspection.service.impl.InspectionServiceImpl;
import com.hr.fire.inspection.service.impl.StandardInfoServiceImpl;
import com.hr.fire.inspection.service.impl.YearCheckServiceImpl;

public class ServiceFactory {

    public static YearCheckService getYearCheckService() {
        return new YearCheckServiceImpl();
    }

    public static CompanyInfoService getCompanyInfoService() {
        return new CompanyInfoServiceImpl();
    }

    public static StandardInfoService getStandardService(){
        return new StandardInfoServiceImpl();
    }

    public static InspectionService getInspectionService() {
        return new InspectionServiceImpl();
    }

    public static AnalysisService getAnalysisService() {
        return new AnalysisServiceImpl();
    }

}
