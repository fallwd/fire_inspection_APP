package com.hr.fire.inspection.utils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Class2Map {


    public static Map<String, Object> getMapParams(Object obj) {
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        if (fields == null || fields.length == 0) {
            return Collections.emptyMap();
        }

        Map<String, Object> params = new HashMap<String, Object>();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                params.put(field.getName(),field.get(obj));
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return params;
    }
}
