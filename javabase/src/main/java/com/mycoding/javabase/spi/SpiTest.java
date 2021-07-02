package com.mycoding.javabase.spi;

import java.util.ServiceLoader;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/1/23 下午10:28
 */
public class SpiTest {
    public static void main(String[] args) {
        ServiceLoader<UploadCDN> load = ServiceLoader.load(UploadCDN.class);
        for (UploadCDN cdn : load){
            cdn.upload("123");
        }
    }
}
