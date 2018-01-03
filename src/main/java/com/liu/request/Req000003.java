package com.liu.request;

import org.springframework.web.multipart.MultipartFile;


/**
 * 新增用户
 * 
 * @author liudi
 * @date 2017年12月26日
 */
public class Req000003 {

    /** 用户名 */
    private String username;
    /** 密码 */
    private String password;
    /** 年龄 */
    private int age;
    /** 头像*/
    private MultipartFile headImage;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MultipartFile getHeadImage() {
        return headImage;
    }

    public void setHeadImage(MultipartFile headImage) {
        this.headImage = headImage;
    }

}
