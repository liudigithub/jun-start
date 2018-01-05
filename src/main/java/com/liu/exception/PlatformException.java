/*
 * Copyright © 2013-2016 BLT, Co., Ltd. All Rights Reserved.
 */

package com.liu.exception;

import java.util.LinkedHashMap;

import com.liu.codemap.ErrorLevelEnum;
import com.liu.codemap.ErrorTracable;
import com.liu.codemap.GlobalErrorCode;


/**
 * <p>
 * 平台异常，用于处理业务逻辑执行过程中所发生的错误。其中主要的属性有：
 * </p>
 * <p>
 * <ul>
 * <li>errorLevel:错误级别(info/warn/error/fatal)</li>
 * <li>returnCode:错误返回码</li>
 * <li>returnMsg:返回消息</li>
 * <li>returnData:返回数据</li>
 * </ul>
 * </p>
 * 
 * @author liudi
 * 
 */
public class PlatformException extends Exception implements ErrorTracable {

    private static final long serialVersionUID = -3686124829493398192L;

    /**
     * <p>
     * 错误级别。
     * </p>
     */
    private String errorLevel = ErrorLevelEnum.ERROR.getCode();

    /**
     * <p>
     * 错误编码。
     * </p>
     */
    private String returnCode = GlobalErrorCode.QJZ0002.getCode();

    /**
     * <p>
     * 错误消息。
     * </p>
     */
    private String returnMessage = GlobalErrorCode.QJZ0002.getName();

    /**
     * 所有的错误消息对。
     */
    private LinkedHashMap<String, String> errors = new LinkedHashMap<String, String>();

    public PlatformException() {
        super();
    }

    public PlatformException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.returnMessage = message;
        errors.put(GlobalErrorCode.QJZ0002.getCode(), message);
    }

    public PlatformException(String message, Throwable cause) {
        super(message, cause);
        this.returnMessage = message;
        errors.put(GlobalErrorCode.QJZ0002.getCode(), message);
    }

    public PlatformException(String message) {
        super(message);
        this.returnMessage = message;
        errors.put(GlobalErrorCode.QJZ0002.getCode(), message);
    }

    public PlatformException(Throwable cause) {
        super(cause);
    }

    public PlatformException(String code, String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.returnCode = code;
        this.returnMessage = message;
        errors.put(code, message);
    }

    public PlatformException(String code, String message, Throwable cause) {
        super(message, cause);
        this.returnCode = code;
        this.returnMessage = message;
        errors.put(code, message);
    }

    public PlatformException(String code, String message) {
        super(message);
        this.returnCode = code;
        this.returnMessage = message;
        errors.put(code, message);
    }

    public PlatformException(String level, String code, String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorLevel = level;
        this.returnCode = code;
        this.returnMessage = message;
        errors.put(code, message);
    }

    public PlatformException(String level, String code, String message, Throwable cause) {
        super(message, cause);
        this.errorLevel = level;
        this.returnCode = code;
        this.returnMessage = message;
        errors.put(code, message);
    }

    public PlatformException(String level, String code, String message) {
        super(message);
        this.errorLevel = level;
        this.returnCode = code;
        this.returnMessage = message;
        errors.put(code, message);
    }

    /**
     * <p>
     * 添加一个错误消息对。
     * </p>
     * 
     * @param returnCode 错误码。
     * @param returnMessage 错误消息。
     */
    public void addError(String code, String message) {
        this.errors.put(code, message);
    }

    public String getErrorLevel() {
        return errorLevel;
    }

    public void setErrorLevel(String errorLevel) {
        this.errorLevel = errorLevel;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public void setErrors(LinkedHashMap<String, String> errors) {
        this.errors = errors;
    }

    public LinkedHashMap<String, String> getErrors() {
        return errors;
    }
}