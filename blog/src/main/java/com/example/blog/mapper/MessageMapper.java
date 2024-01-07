package com.example.blog.mapper;

import com.example.blog.entity.Message;

import java.util.List;


public interface MessageMapper {

    Message get(String name);

    List<Message> getList(Message message);

    void add(Message message);

    void update(Message message);

    void del(Message message);

}
