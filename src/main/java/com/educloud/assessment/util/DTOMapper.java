package com.educloud.assessment.util;

import java.lang.reflect.Field;
import java.util.*;

public class DTOMapper {

    public static Map<String, Object> toMap(Object dto) {
        Map<String, Object> map = new LinkedHashMap<>();

        for (Field field : dto.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(dto)); // key = field name, value = field value
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return map;
    }

    public static List<Map<String, Object>> getColumns(Class<?> clazz) {
        List<Map<String, Object>> columns = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            Map<String, Object> col = new HashMap<>();

            col.put("name", field.getName());

            // make id non-editable automatically
            if ("id".equalsIgnoreCase(field.getName())) {
                col.put("editable", false);
            }

            columns.add(col);
        }

        return columns;
    }
}
