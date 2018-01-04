/*
 * Copyright © 2013-2016 BLT, Co., Ltd. All Rights Reserved.
 */

package com.liu.component;

import java.util.Map;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * <p>
 * JSON类型的视图和模型对象。
 * </p>
 * 
 * @author liudi
 * 
 * @since 1.0
 */
public class JsonModelAndView extends WebModelAndView {

    public JsonModelAndView() {
        super();
        setView(new MappingJackson2JsonView());
    }

    public JsonModelAndView(Map<String, ?> model) {
        super();
        setView(new MappingJackson2JsonView());
        addAllObjects(model);
    }

    public JsonModelAndView(String modelName, Object modelObject) {
        super();
        setView(new MappingJackson2JsonView());
        addObject(modelName, modelObject);
    }

    public JsonModelAndView(Object modelObject) {
        super();
        setObject(modelObject);
    }
}
