package com.example.blog.controller;

import com.example.blog.entity.Forum;
import com.example.blog.mapper.ForumMapper;
import com.example.blog.entity.User;
import com.example.blog.mapper.UserMapper;
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
@RequestMapping("/forum")
public class ForumController {

    @Autowired
    private ForumMapper questionDao;

    @Autowired
    private UserMapper userDao;

    /**
     * 获取列表
     *
     * @param
     * @return
     */
    @RequestMapping("/list")
    public Map list(@RequestBody Forum question) {
        Map result = new HashMap();
        List<Forum> list = questionDao.getList(question);
        for (int i = 0; i < list.size(); i++) {
            User user = userDao.getById(list.get(i).getUid());
            if(user!=null){
                list.get(i).setName(user.getName());
            }
        }
        result.put("list",list);
        result.put("count",list.size());
        return result;
    }


    /**
     * 新增
     *
     * @param
     * @return
     */
    @RequestMapping("/add")
    public Map add(@RequestBody Forum question) {
        Map result = new HashMap();
        questionDao.add(question);
        result.put("msg","新增成功");
        result.put("code",200);
        return result;
    }


    /**
     * 修改
     *
     * @param
     * @return
     */
    @RequestMapping("/edit")
    public Map edit(@RequestBody Forum question) {
        Map result = new HashMap();
        questionDao.update(question);
        result.put("msg","修改成功");
        result.put("code",200);
        return result;
    }


    /**
     * 删除
     *
     * @param
     * @return
     */
    @RequestMapping("/del")
    public Map del(@RequestBody Forum question) {
        Map result = new HashMap();
        questionDao.del(question);
        result.put("msg","删除成功");
        result.put("code",200);
        return result;
    }




}