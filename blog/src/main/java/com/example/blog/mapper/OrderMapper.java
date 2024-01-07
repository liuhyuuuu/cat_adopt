package com.example.blog.mapper;

import com.example.blog.entity.Order;

import java.util.List;


public interface OrderMapper {

    Order get(String name);

    List<Order> getByPid(int pid);

    List<Order> getList(Order order);

    void add(Order order);

    void update(Order order);

    void del(Order order);

}
