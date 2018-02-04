package com.lh.service;

import com.lh.entity.User;

import java.util.List;

/**
 *用户服务
 * */
public interface UserService {
    User selectOne(User user);

    User doUserLogin(String username);

    List<User> selectAll();
}
