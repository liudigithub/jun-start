/*
 * Copyright © 2013-2016 BLT, Co., Ltd. All Rights Reserved.
 */

package com.liu.component;

import java.util.Map;

import org.jasypt.commons.CommonUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.liu.codemap.ErrorLevelEnum;
import com.liu.codemap.GlobalErrorCode;
import com.liu.exception.BusinessException;

/**
 * <p>
 * HTML类型的的视图和模型对象。
 * </p>
 * <p>
 * 无论是HTML，JSON，XML，统一返回如下格式的数据：<br>
 * { level: 0, returnCode: 0, returnMsg: 'success', resultCode: 0, resultMsg: 'success', data{ ... }
 * }
 * </p>
 * <p>
 * level:错误级别。<br>
 * returnCode：通信返回码<br>
 * returnMsg：通信消息。<br>
 * resultCode：业务返回码。<br>
 * resultMsg：业务返回消息。<br>
 * data：返回数据。<br>
 * </p>
 * <p>
 * 如果业务请求成功，retornCode和resultCode都返回0，如果通信成功但业务逻辑失败，则returnCode返回0，resultCode 返回相应的错误码，错误码和错误消息从
 * {@link BusinessException#getCode()}和{@link BusinessException#getMessage()}中获取。
 * 应该预先定义错误码和错误消息的数据字典枚举，以配合异常进行错误输出。
 * </p>
 * 
 * @author liudi
 * 
 */
public class WebModelAndView extends ModelAndView {

    /**
     * <p>
     * 接口通信返回码的键名称。
     * </p>
     */
    private static final String RETURN_CODE_KEY = "code";

    /**
     * <p>
     * 接口通信返回值的键名称。
     * </p>
     */
    private static final String RETURN_MESSAGE_KEY = "message";

    /**
     * <p>
     * 接口业务返回码的键名称。
     * </p>
     */
    // private static final String RESULT_CODE_KEY = "resultCode";

    /**
     * <p>
     * 接口业务返回值的键名称。
     * </p>
     */
    // private static final String RESULT_MESSAGE_KEY = "resultMsg";

    /**
     * <p>
     * 错误级别，用于根据不同级别进行不同类型的业务处理。
     * </p>
     */
    private static final String ERROR_LEVEL_KEY = "level";

    /**
     * <p>
     * 接口返回数据的键名称。
     * </p>
     */
    private static final String DATA_KEY = "data";

    /**
     * <p>
     * 请求被拒绝后，跳转的向导页。
     * </p>
     */
    private static final String REDIRECT_URL_KEY = "redirectUrl";

    private boolean headerInit = false;

    private void initModelHeader() {
        setErrorLevel(ErrorLevelEnum.INFO);
        setReturnCodeAndMessage(GlobalErrorCode.QJZ0001.getCode(),
                GlobalErrorCode.QJZ0001.getName());
        // setResultCodeAndMessage(GlobalErrorCode.QJZ0001.getCode(),
        // GlobalErrorCode.QJZ0001.getName());
        headerInit = true;
    }

    /**
     * 
     */
    public WebModelAndView() {
        super();
        initModelHeader();
        addAllObjects(new ModelMap());
    }

    /**
     * 
     * @param viewName
     */
    public WebModelAndView(String viewName) {
        super(viewName);
        // 重定向时，不在地址栏中暴露ModelMap中的参数
        if (!viewName.contains("redirect:")) {
            initModelHeader();
            addAllObjects(new ModelMap());
        }
    }

    /**
     * 
     * @param viewName
     * @param model
     */
    public WebModelAndView(String viewName, Map<String, ?> model) {
        super(viewName);
        // 重定向时，不在地址连中暴露ModelMap中的参数
        if (!viewName.contains("redirect:")) {
            initModelHeader();
            addAllObjects(model);
        }
    }

    /**
     * 
     * @param viewName
     * @param modelName
     * @param modelObject
     */
    public WebModelAndView(String viewName, String modelName, Object modelObject) {
        super(viewName);
        // 重定向时，不在地址连中暴露ModelMap中的参数
        if (!viewName.contains("redirect:")) {
            initModelHeader();
            addObject(modelName, modelObject);
        }
    }

    /**
     * 
     * @param view
     * @param model
     */
    public WebModelAndView(View view, Map<String, ?> model) {
        super(view);
        // 重定向时，不在地址连中暴露ModelMap中的参数
        if (!(view instanceof RedirectView)) {
            initModelHeader();
            addAllObjects(new ModelMap());
        }
    }

    /**
     * 
     * @param view
     * @param modelName
     * @param modelObject
     */
    public WebModelAndView(View view, String modelName, Object modelObject) {
        super(view);
        // 重定向时，不在地址连中暴露ModelMap中的参数
        if (!(view instanceof RedirectView)) {
            initModelHeader();
            addObject(modelName, modelObject);
        }
    }

    /**
     * 
     * @param view
     */
    public WebModelAndView(View view) {
        super(view);
        // 重定向时，不在地址连中暴露ModelMap中的参数
        if (!(view instanceof RedirectView)) {
            initModelHeader();
            addAllObjects(new ModelMap());
        }
    }

    public void setErrorLevel(ErrorLevelEnum errorLevelEnum) {
        getModelMap().addAttribute(ERROR_LEVEL_KEY, errorLevelEnum.getCode());
    }

    public void setErrorLevel(String errorLevel) {
        getModelMap().addAttribute(ERROR_LEVEL_KEY, errorLevel);
    }

    /**
     * 
     * @param returnCode
     * @param returnMessage
     */
    public void setReturnCodeAndMessage(String returnCode, String returnMessage) {
        CommonUtils.validateNotNull(returnCode, "The [returnCode] argument can not be null");
        CommonUtils.validateNotNull(returnMessage, "The [returnMessage] argument can not be null");
        getModelMap().addAttribute(RETURN_CODE_KEY, returnCode);
        getModelMap().addAttribute(RETURN_MESSAGE_KEY, returnMessage);
        // 如果通信标识失败，则不添加结果标识，同时将错误级别设置为fatal
        if (returnCode.equals(GlobalErrorCode.QJZ0001.getCode())) {
            setErrorLevel(ErrorLevelEnum.INFO);
        }
        // else {
        // setErrorLevel(ErrorLevelEnum.FATAL);
        // getModelMap().remove(RESULT_CODE_KEY);
        // getModelMap().remove(RESULT_MESSAGE_KEY);
        // ((ModelMap)getModelMap()).remove(DATA_KEY);
        // }
    }

    /**
     * 
     * @param returnCode
     * @param returnMessage
     * @param errorLevel
     */
    public void setReturnCodeAndMessage(String returnCode, String returnMessage,
            String errorLevel) {
        CommonUtils.validateNotNull(returnCode, "The [returnCode] argument can not be null");
        CommonUtils.validateNotNull(returnCode, "The [returnMessage] argument can not be null");
        if (errorLevel != null) {
            setErrorLevel(errorLevel);
        }
        setReturnCodeAndMessage(returnCode, returnMessage);
    }

    /**
     * <p>
     * 对象初始化后，默认的resultCode是success。调用此方法通常是业务发生了错误，所以这里错误级别为fail。
     * </p>
     * 
     * @param resultCode
     * @param resultMessage
     */
    // public void setResultCodeAndMessage(String resultCode, String resultMessage) {
    // CommonUtils.validateNotNull(resultCode, "The [returnCode] argument can not be null");
    // CommonUtils.validateNotNull(resultCode, "The [returnMessage] argument can not be null");
    // if(resultCode.equals(GlobalErrorCode.QJZ0001.getCode())){
    // setErrorLevel(ErrorLevelEnum.INFO);
    // }else {
    // setErrorLevel(ErrorLevelEnum.ERROR);
    // }
    //
    // getModelMap().addAttribute(RESULT_CODE_KEY, resultCode);
    // getModelMap().addAttribute(RESULT_MESSAGE_KEY, resultMessage);
    // }

    // public void setResultCodeAndMessage(String resultCode, String resultMessage, ErrorLevelEnum
    // errorLevel) {
    // CommonUtils.validateNotNull(resultCode, "The [resultCode] argument can not be null");
    // CommonUtils.validateNotNull(resultCode, "The [resultMessage] argument can not be null");
    // if(errorLevel != null){
    // setErrorLevel(errorLevel);
    // }
    // setResultCodeAndMessage(resultCode, resultMessage);
    // }

    private void initModelBody() {
        if (!getModelMap().containsKey(DATA_KEY)) {
            getModelMap().addAttribute(DATA_KEY, new ModelMap());
        }
    }

    /**
     * 
     */
    public ModelAndView addObject(String attributeName, Object attributeValue) {
        initModelBody();
        ((ModelMap) getModelMap().get(DATA_KEY)).addAttribute(attributeName, attributeValue);
        return this;
    }

    /**
     * 追加返回值
     * <br>
     * 推荐使用setObject或addObject(String, Object)
     * 
     * @see #addObject(String, Object)
     * @see #setObject(Object)
     * @deprecated Use setObject instead
     */
    @Deprecated
    public ModelAndView addObject(Object attributeValue) {
        initModelBody();
        ((ModelMap) getModelMap().get(DATA_KEY)).addAttribute(attributeValue);
        return this;
    }

    /**
     * 
     */
    public ModelAndView addAllObjects(Map<String, ?> modelMap) {
        initModelBody();
        ((ModelMap) getModelMap().get(DATA_KEY)).addAllAttributes(modelMap);
        return this;
    }

    /**
     * 
     */
    public void setView(View view) {
        super.setView(view);
        if (view instanceof RedirectView) {
            ((RedirectView) view).setExposeModelAttributes(false);
        }
    }

    /**
     * 
     */
    public void setViewName(String viewName) {
        super.setViewName(viewName);
        if (viewName.contains("redirect:")) {
            getModelMap().clear();
        } else if (headerInit = false) {
            initModelHeader();
        }
    }

    /**
     * 
     * @return
     */
    public String getRedirectUrl() {
        return (String) getModelMap().get(REDIRECT_URL_KEY);
    }

    /**
     * 
     * @param redirectUrl
     */
    public void setRedirectUrl(String redirectUrl) {
        if (redirectUrl.startsWith("/")) {
            redirectUrl = redirectUrl.substring(1);
        }
        getModelMap().addAttribute(REDIRECT_URL_KEY, redirectUrl);
    }

    /**
     * <p>
     * 给DATA对象赋值。如果返回结果是单一值，可以使用此方法重新设定返回数据的JSON结构。
     * </p>
     * 
     * @param attributeValue DATA对象
     */
    public void setObject(Object attributeValue) {
        initModelBody();
        ((ModelMap) getModelMap()).put(DATA_KEY, attributeValue);
    }
}
