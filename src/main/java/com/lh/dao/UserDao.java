package com.lh.dao;

import com.lh.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *用户接口
 * */
@Repository
public interface UserDao {
    User selectOne(User user);

    User doUserLogin(String username);

    List<User> selectAll();
}
