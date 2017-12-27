/*
 * Copyright © 2013-2016 BLT, Co., Ltd. All Rights Reserved.
 */

package com.liu.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liu.codemap.ErrorTracable;
import com.liu.exception.SystemException;

/**
 * 异常处理
 * 
 * @author liudi
 */
public abstract class GtmAppExceptionUtils {

    private static Logger logger = LoggerFactory.getLogger(GtmAppExceptionUtils.class);

    /**
     * 异常处理
     * 
     * @param modelView View对象
     * @param ex 异常
     * @return 更新异常信息后的View对象
     * @Since 2016年3月10日
     */
    public static WebModelAndView processException(WebModelAndView modelView,
            ErrorTracable ex) {

        logger.error(ex.getMessage(), ex);

        if (modelView == null) {
            modelView = new WebModelAndView();
        }
        modelView.setReturnCodeAndMessage(ex.getReturnCode(), ex.getReturnMessage(),
                ex.getErrorLevel());
        return modelView;
    }

    /**
     * 异常运行期处理
     * 
     * @param modelView View对象
     * @param ex 异常
     * @return 更新异常信息后的View对象
     */
    public static WebModelAndView processException(WebModelAndView modelView, SystemException ex) {

        logger.error(ex.getMessage(), ex);

        if (modelView == null) {
            modelView = new WebModelAndView();
        }
        modelView.setReturnCodeAndMessage(ex.getReturnCode(), ex.getReturnMessage(),
                ex.getErrorLevel());
        return modelView;
    }
}
