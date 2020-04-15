package com.hr.fire.inspection.service;

import com.hr.fire.inspection.R;
import com.hr.fire.inspection.entity.CheckType;
import com.hr.fire.inspection.entity.ItemInfo;
import com.hr.fire.inspection.entity.YearCheck;
import com.hr.fire.inspection.entity.YearCheckResult;

import java.util.Date;
import java.util.List;

public interface YearCheckService extends BaseService<Object>{


    List<YearCheck> getCheckDataAll();
    List<CheckType> getCheckTypeAll();

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
    List getHistoryList(long companyId, long systemId);

    /**
     * 获取设备信息表信息
     * @param
     * @return
     */
    List<ItemInfo> getItemData(String companyName, String oilfieldName, String platformName, String systemName, String tableName, String number);
    List<ItemInfo> getItemDataEasy(long companyInfoId, long checkTypeId, String number, Date checkDate);

    /**
     * 获取检查表结果信息
     * @param   id 为查询到的设备表数据对应的id，如果没有设备表，则id=0;
     *             tableName为设备表名,如"药剂瓶"，
     *             如果没有设备表，则为tableName为检查表名，如"管线管件"
     * @return
     */
    List<YearCheckResult> getCheckResultData(long id, String companyName, String oilfieldName, String platformName, String systemName, String tableName, String number);
    List<YearCheckResult> getCheckResultDataEasy(long itemId, long companyInfoId, long checkTypeId, String number, Date checkDate);

    /**
     * 获取检查表信息
     * @param
     * @return
     */
    List<YearCheck> getCheckData(String tableName);
    List<YearCheck> getCheckDataEasy(long checkTypeId);

    /**
     * 插入一条设备信息
     * @param   itemData 为ItemInfo对象，属性对应输入内容即可
     * @return
     */
    long insertItemData(ItemInfo itemData, String companyName, String oilfieldName, String platformName, String systemName, String tableName, String number);
    long insertItemDataEasy(ItemInfo itemData, long companyInfoId,  long checkTypeId, String number, Date checkDate);

    /**
     * 插入一条检查表信息 分两种情况，一种是没有一级表，直接插入即可，itemId=0，另一种是有一级表，需要数据itemId
     * @param  checkResultData 为YearCheckResult对象，属性对应输入内容即可
     *                         itemId 为设备表一条数据对象对应的数据表id
     *                         checkId 为YearCheck检查表对应的一条检查项目的id
     *                         itemTableName 为设备表名
     *                         checkTableName 为检查表名
     * @return
     */
    long insertCheckResultData(YearCheckResult checkResultData, long itemId,long checkId,String companyName, String oilfieldName, String platformName, String systemName, String itemTableName, String checkTableName);
    long insertCheckResultDataEasy(YearCheckResult checkResultData, long itemId,long yearCheckId,long companyInfoId, long checkTypeId, String number, Date checkDate);

    // 导出报告相关接口
    /**
     * 获取可导出结果列表
     * @param
     * @return
     */
    List getOutputList();

    /**
     * 获取导出结果数据
     * @param
     * @return
     */
    List<ItemInfo> getOutputItemData();

}
