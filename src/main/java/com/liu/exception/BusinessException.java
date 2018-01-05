/*
 * Copyright © 2013-2016 BLT, Co., Ltd. All Rights Reserved.
 */

package com.liu.exception;

public class BusinessException extends PlatformException {
    private static final long serialVersionUID = 1459740955397908809L;

    public BusinessException() {
        super();
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
    
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public BusinessException(String code, String message) {
        super(code, message);
    }

    public BusinessException(String level, String code, String message, Throwable cause) {
        super(level, code, message, cause);
    }
}
