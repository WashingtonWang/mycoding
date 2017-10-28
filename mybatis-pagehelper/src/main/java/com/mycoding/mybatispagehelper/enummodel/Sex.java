package com.mycoding.mybatispagehelper.enummodel;

public enum Sex {
    /**
     * 女
     */
    WOMAN(1),
    /**
     * 男
     */
    MAN(2);

    private int code;

    Sex(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
