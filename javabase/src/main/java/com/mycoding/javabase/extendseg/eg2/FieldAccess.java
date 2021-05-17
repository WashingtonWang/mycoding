package com.mycoding.javabase.extendseg.eg2;

/**
 * 域和静态方法  《java编程思想》 p.156
 * user: xiangyu.wang
 * date: 2017/11/10 9:25
 */
public class FieldAccess {
    public static void main(String[] args) {
        Super sup = new Sub();
        System.out.println("sup.field = " + sup.field + " . sup.getField() = " + sup.getField());
        Sub sub = new Sub();
        System.out.println("sub.field = " + sub.field + ". sub.getField() = "
                + sub.getField() + " sub.getSupField() = " + sub.getSuperField());
    }
}

class Sub extends Super {
    public int field = 1;
    public int getField(){
        return field;
    }
    public int getSuperField(){
        return super.getField();
    }
}

class Super {
    public int field = 0;
    public int getField(){
        return field;
    }
}
