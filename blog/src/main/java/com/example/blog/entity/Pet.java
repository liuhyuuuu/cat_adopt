package com.example.blog.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Pet {

    /**
     *  ID
    */
    private int id;

    private int uid;

    /**
     *  名c称
     */
    private String name;

    private String img;

    private String category;

    private String sex;

    private int age;

    private String weight;

    private String height;

    private String addr;

    private String character;

    private String demand;

    private String status;
    private Date date;

}
