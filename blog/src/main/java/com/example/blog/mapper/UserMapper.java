package com.example.blog.mapper;

import com.example.blog.entity.User;

import java.util.List;


public interface UserMapper {

    /**
     * 获取用户
     * @param
     * @return
     */
    User selectUserByLoginName(String name);

    User getById(int id);

    List<User> getList(User user);

    void add(User user);

    void update(User user);

    void del(User user);
    void blacklist(User user);

}
