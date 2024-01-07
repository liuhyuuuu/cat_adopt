package com.example.blog.entity;

import lombok.Data;

//该注解可省略实体的get/set等方法，使用前需在应用商店安装lombok插件
@Data
public class Admin {

    /**
     *  用户ID
    */
    private int id;

    /**
     *  用户名
     */
    private String name;


    private String pwd;

}
