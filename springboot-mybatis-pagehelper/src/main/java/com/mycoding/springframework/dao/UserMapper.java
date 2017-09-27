package com.mycoding.springframework.dao;

import com.mycoding.springframework.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    User selectUserById(@Param("id") int id);

    int updateUserById(User user);

    int deleteUserById(@Param("id")int id);

    List<User> queryUserAll();
}
