package com.liu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.liu.api.UrlApi;
import com.liu.component.GtmAppExceptionUtils;
import com.liu.component.JsonModelAndView;
import com.liu.component.WebModelAndView;
import com.liu.exception.PlatformException;
import com.liu.model.User;
import com.liu.request.Req000003;
import com.liu.request.Req000007;
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
    public ModelAndView showUser(HttpServletRequest request) {
        WebModelAndView modelView = new WebModelAndView("user/showUser");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        if (user == null) {
            modelView.addObject("user", new User());
            return modelView;
        }
        int userId = user.getId();
        user = userService.getUserById(userId);
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
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Req000003 req) {

        WebModelAndView modelView = new WebModelAndView("user/addUser");
        try {
            User user =
                    userService.addUser(req.getUsername(), req.getPassword(), req.getAge(), req.getHeadImage(),
                            req.getContent());
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            modelView.setViewName("redirect:".concat(UrlApi.URL_000002));
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

    /**
     * 登录（TO）
     * 
     * @author liudi
     * @date 2017年12月26日
     * @return ModelAndView
     */
    @RequestMapping(value = {UrlApi.URL_000005}, method = {RequestMethod.GET})
    public ModelAndView toLogin() {
        WebModelAndView modelView = new WebModelAndView("login/login");
        return modelView;
    }

    /**
     * 登录
     * 
     * @author liudi
     * @date 2017年12月26日
     * @return ModelAndView
     */
    @RequestMapping(value = {UrlApi.URL_000007}, method = {RequestMethod.POST})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, Req000007 req) {
        WebModelAndView modelView = new WebModelAndView("user/addUser");
        try {
            User user = userService.login(req.getUsername(), req.getPassword());
            HttpSession session = request.getSession();
            session.setAttribute("currnetUser", user);
            modelView.setViewName("redirect:".concat(UrlApi.URL_000002));
        } catch (PlatformException e) {
            GtmAppExceptionUtils.processException(modelView, e);
        }
        return modelView;
    }

    /**
     * 登录ajax
     * 
     * @author liudi
     * @date 2017年12月26日
     * @return ModelAndView
     */
    @RequestMapping(value = {UrlApi.URL_000008}, method = {RequestMethod.POST})
    @ResponseBody
    public ModelMap loginajax(HttpServletRequest request, HttpServletResponse response, @ModelAttribute Req000007 req) {
        WebModelAndView modelView = new JsonModelAndView();
        try {
            User user = userService.login(req.getUsername(), req.getPassword());
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            modelView.setObject(user);
        } catch (PlatformException e) {
            GtmAppExceptionUtils.processException(modelView, e);
        }
        return modelView.getModelMap();
    }
}
