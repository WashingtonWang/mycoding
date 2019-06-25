package map.hashmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * user: xiangyu.wang
 * date: 2019/4/12 10:45
 */
public class HashMapEg {

    private static final int MAXIMUM_CAPACITY_IMI = 1 << 30;

    public static void main(String[] args) {
        int r = tableSizeForImi(65);
        System.out.println(r);
    }

    public static void test1() {
        Map map = new HashMap();
        map.put("123", "123");
        System.out.println(map);

        HashSet<String> set = new HashSet<>();
        set.add("1231");
        set.add("312");

        set.contains("123");
    }

    /**
     * tableSizeFor的功能（不考虑大于最大容量的情况）是返回大于输入参数且最近的2的整数次幂的数
     * @param cap
     * @return
     */
    public static int tableSizeForImi(int cap) {
        int n = cap - 1;
        System.out.println("tableSizeForImi----> -1 | " + n + " | " + n);
        n |= n >>> 1;
        System.out.println("tableSizeForImi----> >>>1 | " + n + " | " + Integer.toBinaryString(n));
        n |= n >>> 2;
        System.out.println("tableSizeForImi----> >>>2 | " + n + " | " + Integer.toBinaryString(n));
        n |= n >>> 4;
        System.out.println("tableSizeForImi----> >>>4 | " + n + " | " + Integer.toBinaryString(n));
        n |= n >>> 8;
        System.out.println("tableSizeForImi----> >>>8 | " + n + " | " + Integer.toBinaryString(n));
        n |= n >>> 16;
        System.out.println("tableSizeForImi----> >>>16 | " + n + " | " + Integer.toBinaryString(n));
        int x = (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY_IMI) ? MAXIMUM_CAPACITY_IMI : n + 1;
        System.out.println("tableSizeForImi----> +1 | " + x + " | " + Integer.toBinaryString(x));
        return x;
    }
}
