package com.mycoding.springbootlearn.starter.service;

import com.mycoding.springbootlearn.starter.configuration.properties.PersonServiceProperties;

public class PersonService {
    private PersonServiceProperties person;

    public PersonService(PersonServiceProperties person) {
        this.person = person;
    }

    public PersonService(){

    }

    public String getPersonName(){
        return person.getName();
    }

    public int getPersonAge(){
        return person.getAge();
    }

    public String getPersonSex(){
        return person.getSex();
    }
}
