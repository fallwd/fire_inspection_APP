package com.hr.fire.inspection.service;

import java.util.HashMap;
import java.util.List;

public interface AnalysisService {

    /**
     * 各公司隐患占比
     * @param
     * @return
     */
    List<HashMap> getCompanyCountByYearCheck(String year);

    /**
     * 公司各油田隐患占比
     * @param
     * @return
     */
    List<HashMap> getOilfieldCountByYearCheck(String year);

    /**
     * 公司油田各平台隐患占比
     * @param
     * @return
     */
    List<HashMap> getPlatformCountByYearCheck(String year);


    /**
     * 公司油田平台各系统隐患占比
     * @param
     * @return
     */
    List<HashMap> getSystemCountByYearCheck(String year);

}
