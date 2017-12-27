package com.liu.service;

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
     * @return int
	 * @throws PlatformException 异常
	 */
    public int addUser(String username, String password, int age) throws PlatformException;
}
