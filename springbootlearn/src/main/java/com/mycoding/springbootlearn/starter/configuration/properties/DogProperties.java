package com.mycoding.springbootlearn.starter.configuration.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/2/9 下午4:46
 */
@ConfigurationProperties(prefix = "animal.dog.properties")
public class DogProperties {

    private String name;
    private String sex;
    private Integer age;
    private String hobby;
    private String hairColor;
    private Boolean enabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "DogProperties{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", enabled='" + enabled + '\'' +
                '}';
    }
}
