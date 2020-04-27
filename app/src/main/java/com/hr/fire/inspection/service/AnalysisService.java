package com.hr.fire.inspection.service;

import com.hr.fire.inspection.entity.YearCheckResult;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface AnalysisService {

    /**
     * 各公司隐患占比
     * @param
     * @return
     */
    List<HashMap> getCompanyCountByYearCheck(String year);
    List<HashMap> getCompanyCountByInspection(String year);

    /**
     * 公司各油田隐患占比
     * @param
     * @return
     */
    List<HashMap> getOilfieldCountByYearCheck(String year,String companyName);
    List<HashMap> getOilfieldCountByInspection(String year);

    /**
     * 公司油田各平台隐患占比
     * @param
     * @return
     */
    List<HashMap> getPlatformCountByYearCheck(String year, String companyName, String oilfieldName);
    List<HashMap> getPlatformCountByInspection(String year);


    /**
     * 公司油田平台各系统隐患占比
     * @param
     * @return
     */
    List<HashMap> getSystemCountByYearCheck(String year, String companyName, String oilfieldName, String platform);
    List<HashMap> getSystemCountByInspection(String year);

    /**
     * 隐患预览 年月日 模糊查询a
     * @param platformId没有的话填0，systemId没有的话填0，startDate没有的话填null,endDate没有的话填null
     * @return
     */
    List<HashMap> getYearCheckView(long platformId, long systemId, Date startDate,Date endDate);
    List<HashMap> getInspectionView(long platformId, long systemId, Date startDate,Date endDate);


    /**
     * 隐患详情
     * @param platformId没有的话填0，systemId没有的话填0，startDate没有的话填null,endDate没有的话填null
     * @return
     */
    List<YearCheckResult> getYearCheckDetail(long platformId, long systemId, String checkDate, String systemNumber, String protectArea);
    List<YearCheckResult> getInspectionDetail(long platformId, long systemId, String checkDate, String checkPerson, String profession);

}