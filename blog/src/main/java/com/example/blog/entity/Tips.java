package com.example.blog.entity;

import lombok.Data;

import java.util.Date;

//该注解可省略实体的get/set等方法，使用前需在应用商店安装lombok插件
@Data
public class Tips {

    private int id;

    private String type;

    private String title;

    private String content;

    private String img;

    private Date date;

}
