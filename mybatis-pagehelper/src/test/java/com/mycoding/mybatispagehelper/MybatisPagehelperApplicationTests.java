package com.mycoding.mybatispagehelper;

import com.mycoding.mybatispagehelper.dao.UserMapper;
import com.mycoding.mybatispagehelper.enummodel.Sex;
import com.mycoding.mybatispagehelper.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisPagehelperApplication.class)
//启注解事务管理
@Transactional
@Rollback
public class MybatisPagehelperApplicationTests {
	@Autowired
	private UserMapper userMapper;

	@Test
	public void insertUser(){
		User user = new User();
		user.setName("梦无常");
		user.setAge(24);
		user.setSex(Sex.MAN.getCode());
		user.setCreatedTime(new Date());
		user.setModifiedTime(new Date());
		int result = userMapper.insertUser(user);
		System.out.println(result);
	}

	@Test
	public void selectUser(){
		//User user = userMapper.selectUserById(11);
		List<User> list = userMapper.queryUserAll();
		list.stream().forEach(System.out::println);
		System.out.println(Sex.MAN.getCode());
		//System.out.println(user);
	}

	@Test
	public void updateUser(){
		User user = new User();
		user.setId(11);
		user.setName("无常索命");
		user.setSex(2);
		user.setAge(12);
		user.setModifiedTime(new Date());
		int result = userMapper.updateUserById(user);
		System.out.println(result);
	}

}
