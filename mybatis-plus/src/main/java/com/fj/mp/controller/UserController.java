package com.fj.mp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fj.mp.dao.UserDao;
import com.fj.mp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("/findAll")
    public List<User> findAll(){
        List<User> users = userDao.selectList(null);
        return users;
    }

    @PostMapping("/insert")
    public void insert(){
        User user = new User();
        user.setAge(20);
        user.setMail("test@itcast.cn");
        user.setName("曹操");
        user.setUserName("caocao");
        user.setPassword("123456");
        int result = userDao.insert(user); //返回的result是受影响的行数，并不是自增后的id
        System.out.println("result = " + result);
    }

    @PostMapping("/update")
    public void update(){
        User user = new User();
        user.setId(3l);
        user.setAge(11);
        int result = userDao.updateById(user); //根据id更新，更新不为null的字段
        System.out.println("result = " + result);
    }

    @PostMapping("/updateQueryWrapper")
    public void updateQueryWrapper(){
        User user = new User();
        user.setAge(11);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name","lisi");
        int result = userDao.update(user,queryWrapper);
        System.out.println("result = " + result);
    }

    @PostMapping("/updateWrapper")
    public void updateWrapper(){
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.set("age","12").set("email","123@qq.com") // 更新的字段
                .eq("user_name","lisi");             // 更新的条件
        int result = userDao.update(null,userUpdateWrapper);
        System.out.println("result = " + result);
    }

    @PostMapping("/deleteByMap")
    public void deleteByMap(@PathParam("age") int age,@PathParam("name") String name) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("age",age);
        columnMap.put("user_name",name);
        System.out.println(age+ ";"+name);
        //将columnMap中的元素设置为删除的条件，多个之间为and关系
        int result = userDao.deleteByMap(columnMap);
        System.out.println("result = " + result);
    }

    @PostMapping("/deleteByQueryWrapper")
    public void deleteByQueryWrapper() {
        User user = new User();
        user.setAge(20);
        user.setName("张三");
    //将实体对象进行包装，包装为操作条件
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        int result = userDao.delete(wrapper);
        System.out.println("result = " + result);

        //根据id集合批量删除
        /*int result1 = userDao.deleteBatchIds(Arrays.asList(1L,10L,20L));
        System.out.println("result = " + result);*/
    }

    @GetMapping("/selectPage")
    public List<User> selectPage() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.gt("age", 20); //年龄大于20岁
        Page<User> page = new Page<>(1,2);
    //根据条件查询数据
        IPage<User> iPage = userDao.selectPage(page, wrapper);
        System.out.println("数据总条数：" + iPage.getTotal());
        System.out.println("总页数：" + iPage.getPages());
        List<User> users = iPage.getRecords();
        for (User user : users) {
            System.out.println("user = " + user);
        }
        return users;
    }
}
