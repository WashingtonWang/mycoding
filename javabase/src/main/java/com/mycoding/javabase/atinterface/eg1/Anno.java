package com.mycoding.javabase.atinterface.eg1;

@FirstAnnotation("http://hrmzone.cn")
public class Anno {

    @Kitto("测试")
    private String test = "";

    @SecondAnnotation
    public String getDefault(){
        return "get default Annotation";
    }

    @SecondAnnotation(name = "desktophrm", url = "desktophrm.com")
    public String getDefine(){
        return "get define Annotation";
    }
}
