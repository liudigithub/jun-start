/*
 * Copyright © 2013-2016 BLT, Co., Ltd. All Rights Reserved.
 */

package com.liu.codemap;

/**
 * 画面选择项目
 * 
 * @author liudi
 */
public interface CodeMap {

    /**
     * 取得CODE值
     * 
     * @return CODE值
     * @since 2015年4月14日
     */
    public abstract Object getCode();

    /**
     * 取得表示名
     * 
     * @return CODE名
     * @since 2015年4月14日
     */
    public abstract String getName();

}
