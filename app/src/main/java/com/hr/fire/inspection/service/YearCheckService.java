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
     * 获取检查表结果信息
     * @param
     * @return
     */
    List<YearCheckResult> getCheckResultData(long id, String companyName, String oilfieldName, String platformName, String systemName, String tableName, String number);

    /**
     * 获取检查表信息
     * @param
     * @return
     */
    List<YearCheck> getCheckData(String tableName);

    /**
     * 插入一条设备信息
     * @param
     * @return
     */
    long insertItemData(ItemInfo itemData, String companyName, String oilfieldName, String platformName, String systemName, String tableName, String number);

    /**
     * 插入一条检查表信息 分两种情况，一种是没有一级表，直接插入即可，id=0，另一种是有一级表，需要数据id
     * @param
     * @return
     */
    long insertCheckResultData(YearCheckResult checkResultData, long itemId,long checkId,String companyName, String oilfieldName, String platformName, String systemName, String itemTableName, String checkTableName);

}
