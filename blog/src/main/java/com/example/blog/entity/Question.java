package com.example.blog.entity;

import lombok.Data;

//该注解可省略实体的get/set等方法，使用前需在应用商店安装lombok插件
@Data
public class Question {

    private int id;

    private String title;


    private String answer;


    private String qa;

    private String qb;

    private String qc;

    private String qd;

}
