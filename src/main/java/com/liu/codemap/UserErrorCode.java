/*
 * Copyright © 2013-2016 BLT, Co., Ltd. SELF Rights Reserved.
 */

package com.liu.codemap;

/**
 * 错误Code <br>
 * 
 * @author liudi
 */
public enum UserErrorCode implements CodeMap {
    USER0001("USER0001", "账号已存在"),
    ;


    private String code;

    private String name;

    private UserErrorCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

}
