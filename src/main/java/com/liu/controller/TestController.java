package com.liu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.liu.api.UrlApi;
import com.liu.component.WebModelAndView;
import com.liu.service.ITestService;

/**
 * 用户
 * 
 * @author liudi
 * @date 2017年12月22日
 */
@Controller
public class TestController {
    @Autowired
    private ITestService testService;

    /**
     * 用户展示
     * 
     * @author liudi
     * @date 2017年12月26日
     * @param req 参数
     * @return ModelAndView
     */
    @RequestMapping(value = {UrlApi.URL_010001}, method = {RequestMethod.GET})
    public ModelAndView toFileinput() {
        WebModelAndView modelView = new WebModelAndView("test/fileinput");
        return modelView;
    }
}
