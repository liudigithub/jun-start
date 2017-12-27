package com.liu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.liu.api.UrlApi;
import com.liu.component.GtmAppExceptionUtils;
import com.liu.component.WebModelAndView;
import com.liu.exception.PlatformException;
import com.liu.model.User;
import com.liu.request.Req000002;
import com.liu.request.Req000003;
import com.liu.service.IUserService;

/**
 * 用户
 * 
 * @author liudi
 * @date 2017年12月22日
 */
@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 用户展示
     * 
     * @author liudi
     * @date 2017年12月26日
     * @param req 参数
     * @return ModelAndView
     */
    @RequestMapping(value = {UrlApi.URL_000002}, method = {RequestMethod.GET})
    public ModelAndView showUser(@ModelAttribute Req000002 req) {
        int userId = req.getId();
        WebModelAndView modelView = new WebModelAndView("showUser");
        User user = userService.getUserById(userId);
        modelView.addObject("user", user);
        return modelView;
    }

    /**
     * 新增用户
     * 
     * @author liudi
     * @date 2017年12月26日
     * @param req 参数
     * @return ModelMap
     */
    @RequestMapping(value = {UrlApi.URL_000003}, method = {RequestMethod.POST})
    public ModelAndView addUser(@ModelAttribute Req000003 req) {

        WebModelAndView modelView = new WebModelAndView("user/addUser");
        try {
            int id = userService.addUser(req.getUsername(), req.getPassword(), req.getAge());
            modelView.setViewName("redirect:".concat(UrlApi.URL_000002).concat("?id=").concat(id+""));
        } catch (PlatformException e) {
            GtmAppExceptionUtils.processException(modelView, e);
        }
        return modelView;
    }

    /**
     * 新增用户(TO) 
     * 
     * @author liudi
     * @date 2017年12月26日
     * @return ModelAndView
     */
    @RequestMapping(value = {UrlApi.URL_000004}, method = {RequestMethod.GET})
    public ModelAndView toAddUesr() {
        WebModelAndView modelView = new WebModelAndView("user/addUser");
        return modelView;
    }
}
