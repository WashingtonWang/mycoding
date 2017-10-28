package com.mycoding.mybatispagehelper.service;

import com.mycoding.mybatispagehelper.model.User;

import java.util.List;

public interface UserService {
    int addUser(User user);

    List<User> findAllUser();

    int deleteUserById(int id);

    User selectUserById(int id);
}
