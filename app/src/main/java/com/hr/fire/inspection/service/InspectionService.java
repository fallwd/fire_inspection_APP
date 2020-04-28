package com.hr.fire.inspection.service;

import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.InspectionResult;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface InspectionService extends BaseService<InspectionResult>{

    /**
     * 获取系统类型数据
     * @param
     * @return
     */
    List<CheckType> getSystemNameData();

    /**
     * 获取检查表类型数据
     * @param
     * @return
     */
    List<CheckType> gettableNameData(long checkTypeId);

    /**
     * 获取历史数据
     * @param
     * @return
     */
    List<HashMap> getHistoryList(long companyId, long systemId);

    /**
     * 获取巡检信息表信息
     * @param
     * @return
     */
//    List<InspectionResult> getInspectionData(long companyInfoId, long checkTypeId, Date checkDate);
    List<InspectionResult> getInspectionData(long companyInfoId, long systemId, Date checkDate);

    /**
     * 插入一条巡检信息
     * @param   inspectionData InspectionResult，属性对应输入内容即可
     * @return
     */
//    long insertInspectionData(InspectionResult inspectionData, long companyInfoId,  long checkTypeId, Date checkDate);
    long insertInspectionData(InspectionResult inspectionData, long companyInfoId,  long systemId, Date checkDate);

    // 导出报告相关接口
    /**
     * 获取可导出结果列表
     * @param
     * @return
     */
    List<HashMap> getOutputList();

    /**
     * 获取导出结果数据
     * @param
     * @return
     */
    List<HashMap> getOutputItemData(long companyInfoId, long systemId, String checkPerson,Date checkDate);


}
