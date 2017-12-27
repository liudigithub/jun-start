/*
 * Copyright © 2013-2016 BLT, Co., Ltd. All Rights Reserved.
 */

package com.liu.codemap;

/**
 * <p>
 * 错误级别数据字典枚举。
 * </p>
 * .
 *
 * @author liudi
 */
public enum ErrorLevelEnum implements CodeMap {

    INFO("0", "info", "消息"),
    WARN("1", "warn", "警告"),
    ERROR("2", "error", "错误"),
    FATAL("3", "fatal", "严重错误");

    private String code;

    private String name;

    private String description;

    private ErrorLevelEnum(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
