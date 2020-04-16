package com.hr.fire.inspection.service;



import com.hr.fire.inspection.entity.StandardInfo;
import com.hr.fire.inspection.entity.StandardType;

import java.util.List;

public interface StandardInfoService extends BaseService<Object> {

    /**
     * 获取pdf文件信息
     * @param typeName 为分类名
     * @return
     */
    List<StandardInfo> getStandardData(String typeName);

    /**
     * 根据名称查询接口
     * @param name 为输入的内容
     * @return
     */
    List<StandardInfo> getSearchResult(String name, String typeName);

    /**
     * 获取分类信息
     * @param
     * @return
     */
    List<StandardType> getTypeData();

}
