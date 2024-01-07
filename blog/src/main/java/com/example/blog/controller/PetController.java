package com.example.blog.controller;

import com.example.blog.entity.Pet;
import com.example.blog.mapper.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器
 */
@CrossOrigin
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetMapper petDao;


    /**
     * 获取列表
     *
     * @param
     * @return
     */
    @RequestMapping("/list")
    public Map list(@RequestBody Pet pet) {
        Map result = new HashMap();
        List<Pet> list = petDao.getList(pet);
        result.put("list", list);
        result.put("count", list.size());
        return result;
    }


    /**
     * 获取s筛选条件
     *
     * @param
     * @return
     */
    @RequestMapping("/tiaojian")
    public Map tiaojian(@RequestBody Pet pet) {
        Map result = new HashMap();
        List<Pet> list = petDao.getKind(pet);
        List<Pet> clist = petDao.getColor(pet);
        result.put("kind", list);
        result.put("color", clist);
        return result;
    }


    /**
     * 新增
     *
     * @param
     * @return
     */
    @RequestMapping("/add")
    public Map add(@RequestBody Pet pet) {
        Map result = new HashMap();
        String msg = "";
        int code = 0;
        Pet animal1 = petDao.get(pet.getName());
        if (animal1 != null) {
            msg = "宠物名已存在！";
            code = 400;
        } else {
            petDao.add(pet);
            code = 200;
            msg = "新增成功";
        }
        result.put("msg", msg);
        result.put("code", code);
        return result;
    }


    /**
     * 修改
     *
     * @param
     * @return
     */
    @RequestMapping("/update")
    public Map update(@RequestBody Pet animal) {
        Map result = new HashMap();
        petDao.update(animal);
        result.put("code", 200);
        return result;
    }


    @RequestMapping("/del")
    public Map del(@RequestBody Pet animal) {
        Map result = new HashMap();
        petDao.del(animal);
        result.put("msg","删除成功");
        result.put("code",200);
        return result;
    }


}