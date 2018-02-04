package com.lh.service.impl;

import com.lh.dao.UserDao;
import com.lh.entity.User;
import com.lh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

 /*   public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }*/

    public List<User> selectAll() {
        return userDao.selectAll();
    }


    public User doUserLogin(String username) {
        return userDao.doUserLogin(username);
    }



    public User selectOne(User user) {
        return userDao.selectOne(user);
    }


}
