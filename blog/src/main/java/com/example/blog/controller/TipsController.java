package com.example.blog.controller;

import com.example.blog.entity.Message;
import com.example.blog.entity.Tips;
import com.example.blog.mapper.TipsMapper;
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
@RequestMapping("/tips")
public class TipsController {

    @Autowired
    private TipsMapper tipsDao;


    /**
     * 获取列表
     *
     * @param
     * @return
     */
    @RequestMapping("/list")
    public Map list(@RequestBody Tips tips) {
        Map result = new HashMap();
        List<Tips> list = tipsDao.getList(tips);
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
    public Map add(@RequestBody Tips tips) {
        Map result = new HashMap();
        tipsDao.add(tips);
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
    public Map edit(@RequestBody Tips tips) {
        Map result = new HashMap();
        tipsDao.update(tips);
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
    public Map del(@RequestBody Tips tips) {
        Map result = new HashMap();
        tipsDao.del(tips);
        result.put("msg","删除成功");
        result.put("code",200);
        return result;
    }




}