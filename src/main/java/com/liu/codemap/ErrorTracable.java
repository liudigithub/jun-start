/*
 * Copyright © 2013-2016 BLT, Co., Ltd. All Rights Reserved.
 */

package com.liu.codemap;

/**
 * <p>
 * 异常可追踪接口。
 * </p>
 * 
 * @author liudi
 * 
 */
public interface ErrorTracable {

    public String getMessage();

    public String getErrorLevel();
    
    public String getReturnCode();
    
    public String getReturnMessage();
}
