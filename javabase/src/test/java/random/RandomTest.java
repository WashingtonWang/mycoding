package random;

import java.util.Random;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/9/21 14:47
 * <p>
 * Random类中实现的随机算法是伪随机，也就是有规则的随机。在进行随机时，随机算法的起源数字称为种子数(seed)，在种子数的基础上进行一定的变换，从而产生需要的随机数字。
 * <p>
 * 相同种子数的Random对象，相同次数生成的随机数字是完全相同的。也就是说，两个种子数相同的Random对象，第一次生成的随机数字完全相同，第二次生成的随机数字也完全相同。这点在生成多个随机数字时需要特别注意。
 * <p>
 * 下面介绍一下Random类的使用，以及如何生成指定区间的随机数组以及实现程序中要求的几率。
 * <p>
 * 1、Random对象的生成
 * <p>
 * Random类包含两个构造方法，下面依次进行介绍：
 * <p>
 * a、public Random()
 * <p>
 * 该构造方法使用一个和当前系统时间对应的相对时间有关的数字作为种子数，然后使用这个种子数构造Random对象。
 * <p>
 * b、public Random(long seed)
 * <p>
 * 该构造方法可以通过制定一个种子数进行创建。
 * <p>
 * 再次强调：种子数只是随机算法的起源数字，和生成的随机数字的区间无关。
 * <p>
 * 如果想避免出现随机数字相同的情况，则需要注意，无论项目中需要生成多少个随机数字，都只使用一个Random对象即可。
 */
public class RandomTest {
    public static void main(String[] args) {
        //randomTest();
        test1();
    }

    //两个种子数相同的Random对象，第一次生成的随机数字完全相同，第二次生成的随机数字也完全相同
    private static void randomTest() {
        Random random = new Random(10);
        int r1 = random.nextInt();
        System.out.println(r1);

        Random random1 = new Random(10);
        int r2 = random1.nextInt();
        System.out.println(r2);

    }

    private static void test1() {
        for (int i = 0; i < 10000; i++) {
            Random random = new Random();
            int nextInt = random.nextInt(4);
            System.out.println(nextInt);
        }

    }
}
