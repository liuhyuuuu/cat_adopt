package com.example.blog.controller;

import com.example.blog.mapper.AdminMapper;
import com.example.blog.entity.Admin;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminMapper adminDao;

    /**
     * 登录验证
     *
     * @param
     * @return
     */
    @RequestMapping("/login")
    public Map login(@RequestBody Admin admin) {
        Map result = new HashMap();
        String msg = "";
        int code = 0;
        Admin admin1 = adminDao.selectUserByLoginName(admin.getName());
        if (admin1 == null) {
            msg = "该用户不存在！";
            code = -1;
        } else {
            if (admin1.getPwd().equals(admin.getPwd())) {
                result.put("user",admin1);
                code = 200;
                msg="登录成功";
            }  else {
                msg = "密码错误！";
                code = 400;
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
    public Map list(@RequestBody Admin user) {
        Map result = new HashMap();
        List<Admin> list = adminDao.getList(user);
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
    public Map add(@RequestBody Admin user) {
        Map result = new HashMap();
        String msg = "";
        int code = 0;
        Admin user1 = adminDao.selectUserByLoginName(user.getName());
        if (user1 != null) {
            msg = "用户名已存在！";
            code = 400;
        }else{
            adminDao.add(user);
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
    public Map update(@RequestBody Admin user) {
        Map result = new HashMap();
        String msg = "";
        int code = 0;
        Admin user1 = adminDao.selectUserByLoginName(user.getName());
        if (user1 != null && user.getId() != user1.getId()) {
            msg = "用户名已存在！";
            code = 400;
        }else{
            adminDao.update(user);
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
    public Map del(@RequestBody Admin user) {
        Map result = new HashMap();
        adminDao.del(user);
        result.put("msg","删除成功");
        result.put("code",200);
        return result;
    }




}