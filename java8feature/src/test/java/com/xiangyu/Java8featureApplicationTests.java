package com.xiangyu;

import org.junit.Test;

import java.util.function.Predicate;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class Java8featureApplicationTests {

	@Test
	public void contextLoads() {
		Predicate<Integer> at = x -> x  > 5;
		System.out.println();
	}

	public static void main(String[] args){
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(1);
			}
		});
		t.start();

		Thread t1 = new Thread( () -> {
			System.out.println(11);
		});
		t1.start();
		
	}

}
