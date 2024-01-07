package com.example.blog.mapper;

import com.example.blog.entity.Tips;

import java.util.List;


public interface TipsMapper {

    Tips get(String name);

    List<Tips> getList(Tips tips);

    void add(Tips tips);

    void update(Tips tips);

    void del(Tips tips);

}
