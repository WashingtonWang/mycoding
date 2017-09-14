package com.mycoding.springbootlearn.properties.service.impl;

import com.mycoding.springbootlearn.properties.config.ConfigProperties;
import com.mycoding.springbootlearn.properties.service.PropertiesEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chhliu
 * 其中@Profile用来指定工作环境，例如示例中为“dev”,那么该类只会在配置文件为“dev”的环境下，才会调用该类
 */
@Service("devPropertiesEnv")
@Profile("dev") // 当配置为开发环境时，生效
public class DevPropertiesEnv implements PropertiesEnv {
    @Autowired
    private ConfigProperties properties;
    @Override
    public String getPropertiesEnv() {
        return properties.toString();
    }
}
