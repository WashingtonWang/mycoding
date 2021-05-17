package com.mycoding.javabase.spi;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/1/23 下午10:33
 */
public class YouKuCDN implements  UploadCDN{
    @Override
    public void upload(String url) {
        System.out.println("upload to youku cdn");
    }
}
