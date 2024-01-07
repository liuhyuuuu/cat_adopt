package com.example.blog.service.impl;

import com.example.blog.entity.User;
import com.example.blog.mapper.UserMapper;
import com.example.blog.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userDao;

    public User selectUserByLoginName(String name) {
        return userDao.selectUserByLoginName(name);
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    public List<User> getList(User user) {
        return userDao.getList(user);
    }

    public void add(User user) {
         userDao.add(user);
    }

    public void update(User user) {
         userDao.update(user);
    }

    public void del(User user) {
        userDao.del(user);
    }

    @Override
    public void blacklist(User user) {
        userDao.blacklist(user);
    }


}
