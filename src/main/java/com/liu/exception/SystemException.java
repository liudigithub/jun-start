/*
 * Copyright © 2013-2016 BLT, Co., Ltd. All Rights Reserved.
 */

package com.liu.exception;

import java.util.LinkedHashMap;

import com.liu.codemap.ErrorLevelEnum;
import com.liu.codemap.GlobalErrorCode;

/**
 * <p>
 * 系统级关键异常。
 * </p>
 * 
 * @author liudi
 * 
 */
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -3686124829493398192L;

    /**
     * <p>
     * 错误级别。
     * </p>
     */
    private String errorLevel = ErrorLevelEnum.FATAL.getCode();

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

    public SystemException() {
        super();
    }

    public SystemException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.returnMessage = message;
        errors.put(GlobalErrorCode.QJZ0002.getCode(), message);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
        this.returnMessage = message;
        errors.put(GlobalErrorCode.QJZ0002.getCode(), message);
    }

    public SystemException(String message) {
        super(message);
        this.returnMessage = message;
        errors.put(GlobalErrorCode.QJZ0002.getCode(), message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String code, String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.returnCode = code;
        this.returnMessage = message;
        errors.put(code, message);
    }

    public SystemException(String code, String message, Throwable cause) {
        super(message, cause);
        this.returnCode = code;
        this.returnMessage = message;
        errors.put(code, message);
    }

    public SystemException(String code, String message) {
        super(message);
        this.returnCode = code;
        this.returnMessage = message;
        errors.put(code, message);
    }

    public SystemException(String level, String code, String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorLevel = level;
        this.returnCode = code;
        this.returnMessage = message;
        errors.put(code, message);
    }

    public SystemException(String level, String code, String message, Throwable cause) {
        super(message, cause);
        this.errorLevel = level;
        this.returnCode = code;
        this.returnMessage = message;
        errors.put(code, message);
    }

    public SystemException(String level, String code, String message) {
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
