package com.mycoding.springframework.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mycoding.springframework.enummodel.Sex;
import com.mycoding.springframework.model.User;
import com.mycoding.springframework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/add")
    public int addUser(User user){
        user.setAge(23);
        user.setName("john");
        user.setSex(Sex.WOMAN.getCode());
        user.setCreatedTime(new Date());
        user.setModifiedTime(new Date());
        return userService.addUser(user);
    }

    @RequestMapping(value = "/all/{pageNum}/{pageSize}")
    public List<User> findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        if (pageSize == 0){
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        return userService.findAllUser();
    }

    @RequestMapping(value = "/deleteUser/{id}")
    public int deleteUserById(@PathVariable("id")int id){
        return userService.deleteUserById(id);
    }

    @RequestMapping(value = "/selectUser/{id}")
    public User selectUserById(@PathVariable("id")int id){
        return userService.selectUserById(id);
    }


    /**
     * 显示所有用户 分页插件使用
     *
     * @param mv
     * @return
     */
    @RequestMapping(value = "/showAllUser/{pageNum}/{pageSize}")
    public PageInfo<User> showAllUser(
            @PathVariable("pageNum") int pageNum,
            @PathVariable("pageSize") int pageSize,
            ModelAndView mv) {
        PageHelper.startPage(pageNum, pageSize);// 默认从第一页开始，每页五条
        List<User> users = userService.findAllUser();// 第一条执行的SQL语句会被分页，实际上输出users是page对象
        PageInfo<User> pageUser = new PageInfo<>(users);// 将users对象绑定到pageInfo
        return pageUser;
        //mv.addObject("users", users);// 设置到属性
        //mv.addObject("pageUser", pageUser);// 设置pageUser属性
        //mv.setViewName("user/showAllUser");// 返回视图
        //return mv;
    }
}
