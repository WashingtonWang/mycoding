package com.mycoding.javabase.innerclass;

/**
 * @Description: 使用.this 与.new 《java编程思想》 P.193
 * user: xiangyu.wang
 * date: 2017/11/17 9:20
 */
public class DotThis {
    void f(){
        System.out.println("DotThis.f()");
    }

    public class Inner{
        public DotThis outer(){
            return DotThis.this;
        }
    }

    public Inner inner(){
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dt = new DotThis();
        DotThis.Inner dti = dt.inner();
        dti.outer().f();
    }
}
