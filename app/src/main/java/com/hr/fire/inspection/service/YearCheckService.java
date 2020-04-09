package com.hr.fire.inspection.service;

import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;

import java.util.List;

public interface YearCheckService extends BaseService<YearCheck>{
    /**
     * 获取设备信息表信息
     * @param
     * @return
     */
    List<ItemInfo> getItemData(String companyName, String oilfieldName, String platformName, String systemName, String tableName, String number);

    /**
     * 获取检查表信息
     * @param
     * @return
     */
    List<YearCheckResult> getCheckResultData(long id, String companyName, String oilfieldName, String platformName, String systemName, String tableName, String number);

    /**
     * 插入一条设备信息
     * @param
     * @return
     */
//    long insertItemData(Item)


    /**
     * 插入一条检查表信息
     * @param
     * @return
     */

}
