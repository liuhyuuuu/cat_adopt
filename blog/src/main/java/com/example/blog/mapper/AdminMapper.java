package com.example.blog.mapper;

import com.example.blog.entity.Admin;

import java.util.List;

public interface AdminMapper {

    /**
     * 获取用户
     * @param
     * @return
     */
    Admin selectUserByLoginName(String name);

    Admin getById(int id);

    List<Admin> getList(Admin admin);

    void add(Admin admin);

    void update(Admin admin);

    void del(Admin admin);

}
