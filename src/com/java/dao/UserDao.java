package com.java.dao;

import com.java.domain.User;

/**
 * @Author shaonaiyi@163.com
 * @Date 2022/3/6 14:32
 * @Description 用户dao层接口
 */
public interface UserDao {

    // 登录
    User login(String username, String password);

    // 注册
    Boolean register(User user);

}