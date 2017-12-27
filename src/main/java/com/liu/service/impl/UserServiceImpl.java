package com.liu.service.impl;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liu.codemap.UserErrorCode;
import com.liu.dao.UserMapper;
import com.liu.exception.PlatformException;
import com.liu.model.User;
import com.liu.model.UserExample;
import com.liu.service.IUserService;

/**
 * 用户service
 * 
 * @author liudi
 * @date 2017年12月22日
 */
@Service
public class UserServiceImpl implements IUserService {

	@Autowired  
    private UserMapper userDao; 
	
    @Override  
    public User getUserById(int userId) {  
        return userDao.selectByPrimaryKey(userId);  
    }

    @Override
    public int addUser(String username, String password, int age) throws PlatformException {
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(username);
        int count = userDao.countByExample(example);
        if(count>0){
            throw new PlatformException(UserErrorCode.USER0001.getCode(), UserErrorCode.USER0001.getName());
        }
        
        User user = new User();
        user.setAge(age);
        user.setPassword(new Md5Hash(password).toString());
        user.setUserName(username);
        userDao.insertSelective(user);
        return user.getId();
    }
}
