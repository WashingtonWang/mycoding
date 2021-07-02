package com.mycoding.javabase.callbyvalue;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/2/18 下午7:22
 * java值调用
 */
public class CallByValueTest {
    public static void main(String[] args) {
        //1. swap
        int num1 = 10;
        int num2 = 20;
        swap(num1, num2);
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);

        //2. change
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(arr[0]);
        change(arr);
        System.out.println(arr[0]);

        //3.
        Student s1 = new Student("小张");
        Student s2 = new Student("小李");
        swap(s1, s2);
        System.out.println("s1:" + s1.getName());
        System.out.println("s2:" + s2.getName());

        SubStudent subStudent = new SubStudent();
        subStudent.setName("aa");
        subStudent.setAddr("北京市海淀区");
        subStudent.setHair("black");
        System.out.println(subStudent.toString());
    }

    //1. 基本类型
    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }

    //2. 对象类型
    public static void change(int[] array) {
        // 将数组的第一个元素变为0
        array[0] = 0;
    }

    //3.
    public static void swap(Student x, Student y) {
        Student temp = x;
        x = y;
        y = temp;
        System.out.println("x:" + x.getName());
        System.out.println("y:" + y.getName());
    }
}

class Student {
    private String name;
    public String addr;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    private void fuTest(){
        System.out.println("fuTest");
    }
}

class SubStudent extends Student {

    private String hair;

    public void test() {
        System.out.println(addr);
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    @Override
    public String toString() {
        return "SubStudent{" +
                "name='" + this.getName() + '\'' +
                "addr='" + addr + '\'' +
                ", hair='" + hair + '\'' +
                '}';
    }
}
