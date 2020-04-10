package com.hr.fire.inspection.service;



import com.hr.fire.inspection.entity.StandardInfo;

import java.util.List;

public interface StandardInfoService extends BaseService<Object> {

    /**
     * 获取pdf文件信息
     * @param typeName 为分类名
     * @return
     */
    List<StandardInfo> getStandardData(String typeName);
}
