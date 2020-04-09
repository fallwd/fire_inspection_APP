package com.hr.fire.inspection.service;

import com.hr.fire.inspection.entity.YearCheck;

public interface BaseService<T> {

    /**
     *  插入
     * @param t 插入对象
     * @return
     */
    long insert(T t);

    /**
     *
     * @param
     * @return
     */
    void update(T t);
}
