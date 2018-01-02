package com.liu.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.liu.api.UrlApi;
import com.liu.service.ITestService;

/**
 * 测试sevice
 * 
 * @author liudi
 * @date 2017年12月29日
 */
@Service
public class ITestServiceImpl implements ITestService {

    @Override
    public Map<String, String> getRoot() {
        String[] fieldNames = this.getFiledName(UrlApi.class);
        String[] fieldValues = this.getFieldValueByName(UrlApi.class);
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 0; i < fieldNames.length; i++) {
            map.put(fieldNames[i], fieldValues[i]);
        }
        return map;
    }

    /**
     * 根据属性名获取属性值
     * */
    private String[] getFieldValueByName(Class<?> cls) {
//        try {
//            String firstLetter = fieldName.substring(0, 1).toUpperCase();
//            String getter = "get" + firstLetter + fieldName.substring(1);
//            Method method = cls.getMethod(getter, new Class[] {});
//            Object value = method.invoke(cls, new Object[] {});
//            return value;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
        Field[] fields = cls.getDeclaredFields();
        String[] fieldValues = new String[fields.length];
        try {
            for (int i = 0; i < fields.length; i++) {
                String value = (String) fields[i].get(cls);
                fieldValues[i] = value.substring(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fieldValues;
    }
    
    

    /**
     * 获取属性名数组
     * */
    private String[] getFiledName(Class<?> cls) {
        Field[] fields = cls.getDeclaredFields();
        String[] fieldNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fieldNames[i] = fields[i].getName();
        }
        return fieldNames;
    }
}
