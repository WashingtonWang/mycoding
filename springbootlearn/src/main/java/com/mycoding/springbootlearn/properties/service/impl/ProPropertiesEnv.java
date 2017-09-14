package com.mycoding.springbootlearn.properties.service.impl;

import com.mycoding.springbootlearn.properties.config.ConfigProperties;
import com.mycoding.springbootlearn.properties.service.PropertiesEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service("proPropertiesEnv")
@Profile("prod")
public class ProPropertiesEnv implements PropertiesEnv {
    @Autowired
    private ConfigProperties properties;
    @Override
    public String getPropertiesEnv() {
        return properties.toString();
    }
}
