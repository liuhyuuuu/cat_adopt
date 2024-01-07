package com.example.blog.mapper;

import com.example.blog.entity.Forum;

import java.util.List;


public interface ForumMapper {

    Forum get(String name);

    List<Forum> getList(Forum question);

    void add(Forum question);

    void update(Forum question);

    void del(Forum question);

}
