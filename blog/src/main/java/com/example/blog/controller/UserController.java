package com.example.blog.controller;

import com.example.blog.entity.User;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 用户控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 登录验证
     *
     * @param
     * @return
     */
    @RequestMapping("/login")
    public Map login(@RequestBody User user) {
        Map result = new HashMap();
        String msg = "";
        int code = 0;
        User user1 = userService.selectUserByLoginName(user.getName());
        if (user1 == null) {
            msg = "该用户不存在！";
            code = -1;
        } else {
            if (user1.getPwd().equals(user.getPwd())) {
                result.put("user",user1);
                code = 200;
                msg="登录成功";
            }  else {
                msg = "密码错误！";
                code = -1;
            }
        }
        result.put("msg",msg);
        result.put("code",code);
        return result;
    }



    /**
     * 获取用户列表
     *
     * @param
     * @return
     */
    @RequestMapping("/list")
    public Map list(@RequestBody User user) {
        Map result = new HashMap();
        List<User> list = userService.getList(user);
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
    public Map add(@RequestBody User user) {
        Map result = new HashMap();
        String msg = "";
        int code = 0;
        User user1 = userService.selectUserByLoginName(user.getName());
        if (user1 != null) {
            msg = "用户名已存在！";
            code = -1;
        }else{
            userService.add(user);
            code = 200;
            msg = "新增成功";
        }
        result.put("msg",msg);
        result.put("code",code);
        return result;
    }


    /**
     * 修改
     *
     * @param
     * @return
     */
    @RequestMapping("/update")
    public Map update(@RequestBody User user) {
        Map result = new HashMap();
        String msg = "";
        int code = 0;
        User user1 = userService.selectUserByLoginName(user.getName());
        if (user1 != null && user.getId() != user1.getId()) {
            msg = "用户名已存在！";
            code = -1;
        }else{
            userService.update(user);
            code = 200;
            msg = "修改成功";
        }
        result.put("msg",msg);
        result.put("code",code);
        return result;
    }


    /**
     * 删除
     *
     * @param
     * @return
     */
    @RequestMapping("/del")
    public Map del(@RequestBody User user) {
        Map result = new HashMap();
        String msg = "";
        int code = 0;
        userService.del(user);
        code = 200;
        msg = "删除成功";
        result.put("msg",msg);
        result.put("code",code);
        return result;
    }

    @RequestMapping("/black")//拉黑
    public Map black(@RequestBody User user) {
        Map result = new HashMap();
        String msg = "";
        int code = 0;
        userService.blacklist(user);
        code = 200;
        msg = "拉黑成功";
        result.put("msg",msg);
        result.put("code",code);
        return result;
    }


}