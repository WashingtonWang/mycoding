package com.xiangyu.lambdaeg.eg1;

public class Track {

    private String name;
    private int Length;

    public Track(String name, int length) {
        this.name = name;
        Length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return Length;
    }

    public void setLength(int length) {
        Length = length;
    }
}
