package bitoperation.eg1;

/**
 * user: xiangyu.wang
 * date: 2019/6/19 15:02
 *
 * 在进行位运算时，需要注意以下几点。 　　
 * (1)>>>和>>的区别是：在执行运算时，>>>运算符的操作数高位补0，而>>运算符的操作数高位移入原来高位的值。 　　
 * (2)右移一位相当于除以2，左移一位(在不溢出的情况下)相当于乘以2；移位运算速度高于乘除运算。 　　
 * (3)若进行位逻辑运算的两个操作数的数据长度不相同，则返回值应该是数据长度较长的数据类型。 　　
 * (4)按位异或可以不使用临时变量完成两个值的交换，也可以使某个整型数的特定位的值翻转。 　　
 * (5)按位与运算可以用来屏蔽特定的位，也可以用来取某个数型数中某些特定的位。 　　
 * (6)按位或运算可以用来对某个整型数的特定位的值置l。
 *
 * 按位与 & >>>>>>>>>>>>>>>>
 * 运算规则是：当运算符两边相同位置都是1时，结果返回1，其他情况都返回0。
 * 判断奇偶性：任意数与1取位与，结果为1则是奇数
 *
 * 按位或 \ >>>>>>>>>>>>>>>>
 * 运算规则是：当运算符两边相同位置都是0时，结果返回0，其他情况都返回1。
 *
 * 按位非 ~ >>>>>>>>>>>>>>>>
 * 运算规则是：将运算符后二进制数反转，0变1，1变 。
 *
 * 按位异或 >>>>>>>>>>>>>>>>
 * 参与运算的两个值，如果两个相应位相同，则结果为0，否则为1。即：0^0=0， 1^0=1， 0^1=1， 1^1=0
 * 例如：10100001^00010001=10110000
 * 　　　0^0=0,0^1=1 0异或任何数＝任何数
 * 　　　1^0=1,1^1=0 1异或任何数－任何数取反
 * 　　　任何数异或自己＝把自己置0
 * (1)按位异或可以用来使某些特定的位翻转，如对数10100001的第2位和第3位翻转，可以将数与00000110进行按位异或运算。 　　　　　　　　　 　　　　　　　　　　10100001^00000110=10100111 //1010 0001 ^ 0x06 = 1010 0001 ^ 6
 * (2)通过按位异或运算，可以实现两个值的交换，而不必使用临时变量。
 * 例如交换两个整数a，b的值，可通过下列语句实现：
 *  a=10100001,b=00000110
 *  a=a^b； 　　//a=10100111
 *  b=b^a； 　　//b=10100001
 *  a=a^b； 　　//a=00000110
 * (3)异或运算符的特点是：数a两次异或同一个数b（a=a^b^b）仍然为原值a.
 */
public class BigOperationTest {

    public static void main(String[] args) {
        //operationTest();
        //changeAdress();
        //judgeOddEven(6);
        //getRemainder(18, 8);
        //getAbsoluteValue(-10001);
        //getAbsoluteValueOptimize(-10001122);
        //signReversal(1);
        changeBinaryLocation(Integer.MAX_VALUE);
    }

    /**
     * 位运算
     */
    static void operationTest(){
        int r = 0 ^ 0;
        System.out.println(r);
        int a = 1 ^ 3;
        System.out.println(a);

        System.out.println(6 >> 2);
        System.out.println(-9 >> 1);
    }


    /**
     * 交换值
     */
    static void changeAdress(int x , int y){
        int a = x, b = y;
        a = a ^ b;
        System.out.println("a: " + a);
        b = b ^ a;
        System.out.println("b: " +b);
        a = a ^ b;
        System.out.println("a -> " + a + " | b -> " + b);
    }

    /**
     * 判断奇偶数
     * @param x
     */
    static void judgeOddEven(int x){
        System.out.println(Integer.toBinaryString(x));
        if ((x & 1) == 0){
            System.out.println(x + " 是偶数");
        } else {
            System.out.println(x + " 是奇数");
        }
    }

    /**
     * 取余数
     * 注意：只能对2^n的数值进行取余计算
     */
    static void getRemainder(int x, int y){
        if (x < 1 || y == 0 || y == 1 || x < y){
            return;
        }
        int a = x % y;
        System.out.println("普通求余：" + a);

        int b = x & (y - 1);
        System.out.println("位运算求余：" + b);
    }

    /**
     * 变换符号位
     */
    static void signReversal(int x){
        int y = ~x + 1;
        System.out.println("变换符号位后结果：" + y);
    }

    /**
     * 求绝对值
     * 位操作也可以用来求绝对值，对于负数可以通过对其取反后加1来得到正数。对-6可以这样：
     *       1111 1010(二进制) –取反->0000 0101(二进制) -加1-> 0000 0110(二进制)
     * 来得到6。
     * 因此先移位来取符号位，int i = a >> 31;要注意如果a为正数，i等于0，为负数，i等于-1。然后对i进行判断——如果i等于0，直接返回。否之，返回~a+1。完整代码如下：
     * //by MoreWindows( http://blog.csdn.net/MoreWindows )
     * int my_abs(int a)
     * {
     * 	int i = a >> 31;
     * 	return i == 0 ? a : (~a + 1);
     * }
     * 现在再分析下。对于任何数，与0异或都会保持不变，与-1即0xFFFFFFFF异或就相当于取反。因此，a与i异或后再减i（因为i为0或-1，所以减i即是要么加0要么加1）也可以得到绝对值。所以可以对上面代码优化下：
     * //by MoreWindows( http://blog.csdn.net/MoreWindows )
     * int my_abs(int a)
     * {
     * 	int i = a >> 31;
     * 	return ((a ^ i) - i);
     * }
     * 注意这种方法没用任何判断表达式，而且有些笔面试题就要求这样做，因此建议读者记住该方法（^_^讲解过后应该是比较好记了）。
     * ---------------------
     * 作者：MoreWindows
     * 来源：CSDN
     * 原文：https://blog.csdn.net/morewindows/article/details/7354571
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     *
     * 优化前代码如下：
     */
    static void getAbsoluteValue(int x){
        int i = x >> 31;
        System.out.println("符号位是：" + i);
        int z = i == 0 ? x : (~x + 1);
        System.out.println("绝对值是" + z);
    }

    static void getAbsoluteValueOptimize(int x){
        int i = x >> 31;
        System.out.println("符号位是：" + i);
        int z = (x ^ i) - i;
        System.out.println("绝对值是" + z);
    }

    /**
     * 交换二进制的高低位
     * @param x
     */
    static void changeBinaryLocation(int x){
        System.out.println("交换前x = " + Integer.toBinaryString(x));
        int y = (x >> 16) | (x << 16);
        System.out.println("交换后y = " + Integer.toBinaryString(y));
    }
}