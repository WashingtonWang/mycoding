package bitoperation.eg1;

/**
 * 位运算
 * user: xiangyu.wang
 * date: 2018/3/21 13:47
 */
public class BitTest {
    public static void main(String[] args) {
        int index = 12;
        int hash = 6134537;
        System.out.println((index) & hash);
        //int a = bitTest1(12345);
        //System.out.println(Integer.toBinaryString(12345));
        //System.out.println(a);
        ////打印二进制
        //System.out.println(Integer.toBinaryString(a));

    }

    /**
     * 下面程序 可以让"00011111" 二进制的 1 分布的更均匀
     * @param hash
     * @return
     */
    static int bitTest1(int hash){
        hash ^= (hash >>> 20) ^ (hash >>> 12);
        int h = hash ^ (hash >>> 7) ^ (hash >>> 4);
        return h;
    }
}
