package com.liu.service.impl;

import java.io.IOException;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.liu.codemap.GlobalErrorCode;
import com.liu.codemap.UserErrorCode;
import com.liu.component.FileUploadUtils;
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
    @Autowired
    private FileUploadUtils fileUploadUtils;

    @Override
    public User getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    @Override
    public int addUser(String username, String password, int age, MultipartFile headImage) throws PlatformException {
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(username);
        int count = userDao.countByExample(example);
        if (count > 0) {
            throw new PlatformException(UserErrorCode.USER0001.getCode(), UserErrorCode.USER0001.getName());
        }

        User user = new User();
        user.setAge(age);
        user.setPassword(new Md5Hash(password).toString());
        user.setUserName(username);
        if (headImage != null && !headImage.isEmpty()) {
            String url;
            try {
                url = fileUploadUtils.uploadImage(headImage.getOriginalFilename(), headImage.getInputStream(), true);
                user.setHeadImage(url);
            } catch (IOException e) {
                throw new PlatformException(GlobalErrorCode.QJZ0004.getCode(), GlobalErrorCode.QJZ0004.getName(), e);
            }
        }else{
            throw new PlatformException(UserErrorCode.USER0002.getCode(), UserErrorCode.USER0002.getName());
        }
        userDao.insertSelective(user);
        return user.getId();
    }
}
