package operatoreg.eg1;

/**
 * @Description: 1、Java中&叫做按位与，&&叫做短路与，它们的区别是：
 * & 既是位运算符又是逻辑运算符，&的两侧可以是int，也可以是boolean表达式，当&两侧是int时，
 * 要先把运算符两侧的数转化为二进制数再进行运算，而短路与（&&）的两侧要求必须是布尔表达式
 * & 会对左右两侧的表达式都进行验证 不管左侧的是否为true都会对右侧进行验证
 * 如果都为true  结果为true  如果有个为false  结果为false
 * user: xiangyu.wang
 * date: 2018/1/3 9:39
 */
public class OperatorEg {
    public static void main(String[] args) {
        int i = 1;
        int j = 3;
        //& 当逻辑运算符
        if (++i > 0 & ++j > 3) {
            System.out.println("true: " + i + "  " + j);
        } else {
            System.out.println("false: " + i + "  " + j);
        }
        //& 当按位运算符
        System.out.println(12 & 5);

        //&& 逻辑与运算符
        if (++i > 3 && ++j > 4) {
            System.out.println(i + "  " + j);
        } else {
            System.out.println(i + "  " + j);
        }

        int a = 1;
        int b = 3;
        //|| 逻辑或运算符
        if (++a > 1 || ++b > 3) {
            System.out.println(a + "  " + b);
        } else {
            System.out.println(a + "  " + b);
        }

        if (++a > 1 | ++b > 5) {
            System.out.println(a + "  " + b);
        } else {
            System.out.println(a + "  " + b);
        }

    }
}
