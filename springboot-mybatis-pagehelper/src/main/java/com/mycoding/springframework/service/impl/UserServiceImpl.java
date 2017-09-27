package com.mycoding.springframework.service.impl;

import com.github.pagehelper.PageHelper;
import com.mycoding.springframework.dao.UserMapper;
import com.mycoding.springframework.model.User;
import com.mycoding.springframework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.queryUserAll();
    }

    @Override
    public int deleteUserById(int id) {
        return userMapper.deleteUserById(id);
    }

    @Override
    public User selectUserById(int id) {
        return userMapper.selectUserById(id);
    }
}
