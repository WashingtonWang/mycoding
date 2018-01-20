package com.mycoding.springframeworkbaselearn;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextAware;

/**
 * @author xiangyu.wang
 */
@SpringBootApplication
public class SpringframeworkBaseLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringframeworkBaseLearnApplication.class, args);
	}
}
