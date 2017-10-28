package com.mycoding.mybatispagehelper.model;


import lombok.Data;

import java.util.Date;

@Data
public class User {
    private int id;
    private String name;
    private int age;
    private int sex;
    private Date createdTime;
    private Date modifiedTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
