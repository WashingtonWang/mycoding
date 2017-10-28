package com.mycoding.mybatispagehelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement(proxyTargetClass = true)
public class MybatisPagehelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisPagehelperApplication.class, args);
	}
}
