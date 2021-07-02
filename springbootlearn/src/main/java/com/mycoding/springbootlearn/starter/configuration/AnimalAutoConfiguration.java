package com.mycoding.springbootlearn.starter.configuration;

import com.mycoding.springbootlearn.starter.configuration.properties.DogProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/2/9 下午5:03
 */
@Configuration
@EnableConfigurationProperties(value = DogProperties.class)
@ConditionalOnProperty(prefix = "animal.dog.properties", value = "enabled", matchIfMissing = true)
public class AnimalAutoConfiguration {

    @Autowired
    private DogProperties dogProperties;

    public AnimalAutoConfiguration(DogProperties dogProperties) {
        this.dogProperties = dogProperties;
        System.out.println(dogProperties);
    }
}

