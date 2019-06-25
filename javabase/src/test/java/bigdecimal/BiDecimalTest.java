package bigdecimal;

import java.math.BigDecimal;

/**
 * user: xiangyu.wang
 * date: 2018/11/13 18:09
 */
public class BiDecimalTest {
    public static void main(String[] args) {
        subtractTest1();
    }

    public static void test1(){
        BigDecimal bd = new BigDecimal(12.5555);
        BigDecimal bd1 = new BigDecimal(12.1);
        BigDecimal bd2 = new BigDecimal(12.999999);
        System.out.println(bd.scale());
        System.out.println(bd1.scale());
        System.out.println(bd2.scale());
    }

    public static void subtractTest1(){
        BigDecimal s1 = new BigDecimal(20);
        BigDecimal s2 = new BigDecimal(40);

        BigDecimal s3 = s1.subtract(s2);
        System.out.println(s3);
        BigDecimal s4 = s3.add(s1);
        System.out.println(s4);
    }

}
