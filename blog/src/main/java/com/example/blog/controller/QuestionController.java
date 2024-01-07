package com.example.blog.controller;

import com.example.blog.entity.Admin;
import com.example.blog.mapper.AdminMapper;
import com.example.blog.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionMapper questionMapper;


    @RequestMapping("/list")
    public Map update() {
        Map result = new HashMap();
        result.put("list",questionMapper.getList());
        result.put("code",200);
        return result;
    }

}