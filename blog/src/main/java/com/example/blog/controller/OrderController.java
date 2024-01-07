package com.example.blog.controller;

import com.example.blog.mapper.OrderMapper;
import com.example.blog.mapper.PetMapper;
import com.example.blog.entity.Order;
import com.example.blog.entity.Pet;
import com.example.blog.entity.User;
import com.example.blog.service.UserService;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderMapper orderDao;

    @Autowired
    private PetMapper petDao;

    @Autowired
    private UserService userService;

    /**
     * 获取列表
     *
     * @param
     * @return
     */
    @RequestMapping("/list")
    public Map list(@RequestBody Order order) {
        Map result = new HashMap();
        List<Order> list = orderDao.getList(order);
        for (int i = 0; i < list.size(); i++) {
            User user1 = userService.getById(list.get(i).getUid());
            if(user1 != null){
                list.get(i).setUname(userService.getById(list.get(i).getUid()).getName());
            }
            User user2 = userService.getById(list.get(i).getNid());
            if(user2 != null){
                list.get(i).setNname(userService.getById(list.get(i).getNid()).getName());
            }
            Pet pet = petDao.getPid(list.get(i).getPid());
            if(pet != null){
                list.get(i).setPname(pet.getName());
            }
        }
        result.put("list", list);
        result.put("count", list.size());
        return result;
    }


    /**
     * 新增
     *
     * @param
     * @return
     */
    @RequestMapping("/add")
    public Map add(@RequestBody Order order) {
        Map result = new HashMap();
        List<Order> list = orderDao.getByPid(order.getPid());
        for (int i = 0; i < list.size(); i++) {
           if(!list.get(i).getStatus().equals("拒绝")){
               result.put("msg", "已有人领养，请重新选择！");
               result.put("code", -1);
               return result;
           }
        }
        orderDao.add(order);
        result.put("msg", "新增成功");
        result.put("code", 200);
        return result;
    }


    /**
     * 修改
     *
     * @param
     * @return
     */
    @RequestMapping("/update")
    public Map update(@RequestBody Order order) {
        Map result = new HashMap();
        orderDao.update(order);
        result.put("code", 200);
        return result;
    }


    @RequestMapping("/del")
    public Map del(@RequestBody Order order) {
        Map result = new HashMap();
        orderDao.del(order);
        result.put("msg","删除成功");
        result.put("code",200);
        return result;
    }


}