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
    List<HashMap> getOilfieldCountByYearCheck(String year);
    List<HashMap> getOilfieldCountByInspection(String year);

    /**
     * 公司油田各平台隐患占比
     * @param
     * @return
     */
    List<HashMap> getPlatformCountByYearCheck(String year);
    List<HashMap> getPlatformCountByInspection(String year);


    /**
     * 公司油田平台各系统隐患占比
     * @param
     * @return
     */
    List<HashMap> getSystemCountByYearCheck(String year);
    List<HashMap> getSystemCountByInspection(String year);

    /**
     * 隐患预览 年月日 模糊查询a
     * @param platformId没有的话填0，systemId没有的话填0，startDate没有的话填null,endDate没有的话填null
     * @return
     */
    List<HashMap> getYearCheckView(long platformId, long systemId, Date startDate,Date endDate);
    List<YearCheckResult> getYearCheckDetail(long platformId, long systemId, String checkDate, String systemNumber, String protectArea);

}
