package com.mycoding.javabase.genericeg.eg1;

import java.util.Date;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/2/18 下午4:54
 */
public class GenericTest {

    public static void main(String[] args) {
        DateInter dateInter = new DateInter();
        dateInter.setValue(new Date());
    }
}

class Pair<T> {

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

class DateInter extends Pair<Date> {

    @Override
    public void setValue(Date value) {
        super.setValue(value);
    }

    @Override
    public Date getValue() {
        return super.getValue();
    }
}
