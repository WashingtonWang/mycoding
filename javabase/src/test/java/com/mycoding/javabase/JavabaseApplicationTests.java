package com.mycoding.javabase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavabaseApplicationTests {

	@Test
	public void contextLoads() {

	}

	public static void main(String[] args) {
		int x = 2, z, k;
		x = z = 5;
		System.out.println(x);
		System.out.println(z);
		List<String> list = new ArrayList<>();
		System.out.println(list.size() > 0);
	}

}
