package bigdecimal;

import java.math.BigDecimal;

/**
 * user: xiangyu.wang
 * date: 2018/11/13 18:09
 */
public class BiDecimalTest {
    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal(12.5555);
        BigDecimal bd1 = new BigDecimal(12.1);
        BigDecimal bd2 = new BigDecimal(12.999999);
        System.out.println(bd.scale());
        System.out.println(bd1.scale());
        System.out.println(bd2.scale());
    }
}
