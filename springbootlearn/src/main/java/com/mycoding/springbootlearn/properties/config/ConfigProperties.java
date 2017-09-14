package com.mycoding.springbootlearn.properties.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author chhliu
 * 自定义属性对应的实体类
 * spring boot会将配置文件中自定义的属性值，自动设置到该类对应的属性上，使用的使用直接注入该类即可
 * prefix用来指定自定义属性值的前缀
 */
@ConfigurationProperties(prefix = "com.mycoding.springboot")
@Component
public class ConfigProperties {
    private String author;
    private int age;
    private String sex;
    private String time;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ConfigProperties{" +
                "author='" + author + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
