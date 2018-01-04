package com.liu.service;

import org.springframework.web.multipart.MultipartFile;

import com.liu.exception.PlatformException;
import com.liu.model.User;

/**
 * 
 * 
 * @author liudi
 * @date 2017年12月22日
 */
public interface IUserService {
    /**
     * 查询用户
     * 
     * @author liudi
     * @date 2017年12月26日
     * @param userId 用户ID
     * @return User
     */
    public User getUserById(int userId);

    /**
     * 新增用户
     * 
     * @author liudi
     * @date 2017年12月26日
     * @param username 用户名
     * @param password 密码
     * @param age 年龄
     * @param headImage 头像
     * @param content 简介
     * @return User
     * @throws PlatformException 异常
     */
    public User addUser(String username, String password, int age, MultipartFile headImage, String content)
            throws PlatformException;

    /**
     * 用户登录
     * 
     * @author liudi
     * @date 2018年1月4日
     * @param username 用户名
     * @param password 密码
     * @return User
     * @throws PlatformException 异常
     */
    public User login(String username, String password) throws PlatformException;
}
