/*
 * Copyright © 2013-2016 BLT, Co., Ltd. All Rights Reserved.
 */

package com.liu.codemap;

/**
 * <p>
 * 全局的错误码数据字典。
 * </p>
 * 
 * @author liudi
 * 
 */
public enum GlobalErrorCode implements CodeMap {
    QJZ0001("0", "操作成功"),
    QJZ0002("-1", "操作失败"),
    QJZ0003("QJZ0003", "日期格式化错误"),
    QJZ0004("QJZ0004", "文件上传失败"),
    QJZ0005("QJZ0005", "文件删除失败"),
    QJZ0006("QJZ0006", "不支持的图片格式"),
    QJZ0007("QJZ0007", "文件下载失败"),
    QJZ0008("QJZ0008", "定位失败"),
    QJZ0009("QJZ0009", "该信息已经删除"),
    ;

    private String code;

    private String name;

    private GlobalErrorCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
