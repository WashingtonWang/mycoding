package com.mycoding.springbootlearn.starter.configuration;

import com.mycoding.springbootlearn.starter.configuration.properties.PersonServiceProperties;
import com.mycoding.springbootlearn.starter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration// 配置注解
@EnableConfigurationProperties(PersonServiceProperties.class) // 开启指定类的配置
@ConditionalOnClass(PersonService.class)// 当PersonService这个类在类路径中时，且当前容器中没有这个Bean的情况下，开始自动配置
@ConditionalOnProperty(prefix="person.proterties.set", value="enabled", matchIfMissing=true)// 指定的属性是否有指定的值
public class PersonServiceAutoConfiguration {
    @Autowired
    private PersonServiceProperties person;

    @Bean
    @ConditionalOnMissingBean(PersonService.class)
    public PersonService personService(){
        return new PersonService(person);
    }
}
