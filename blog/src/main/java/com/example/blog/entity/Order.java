package com.example.blog.entity;

import lombok.Data;

import java.util.Date;

//该注解可省略实体的get/set等方法，使用前需在应用商店安装lombok插件
@Data
public class Order {

    private int id;

    private int uid;

    private int pid;

    private int nid;

    private String uname;

    private String pname;
    private String nname;

    private String way;

    private String status;

    private String remark;

    private String addr;

    private String name;

    private String tel;

    private Date date;

}
