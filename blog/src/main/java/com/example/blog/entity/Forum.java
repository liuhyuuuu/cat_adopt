package com.example.blog.entity;

import lombok.Data;

import java.util.Date;

//该注解可省略实体的get/set等方法，使用前需在应用商店安装lombok插件
@Data
public class Forum {

    private int id;

    private int uid;

    private String message;

    private String reply;

    private Date date;

    private String name;

    private String img;
}
