package com.mycoding.springframework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringframeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringframeworkApplication.class, args);
	}
}
