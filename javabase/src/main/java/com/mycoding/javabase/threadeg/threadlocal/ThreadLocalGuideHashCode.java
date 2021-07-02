package com.mycoding.javabase.threadeg.threadlocal;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/3/4 下午11:23
 * 每当创建一个ThreadLocal对象，这个ThreadLocal.nextHashCode 这个值就会增长 0x61c88647 。
 * <p>
 * 这个值很特殊，它是斐波那契数 也叫 黄金分割数。hash增量为 这个数字，带来的好处就是 hash 分布非常均匀。
 */
public class ThreadLocalGuideHashCode {
    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
        int hashCode = 0;
        for (int i = 0; i < 16; ++i) {
            hashCode = i * HASH_INCREMENT + HASH_INCREMENT;
            int bucket = hashCode & 16 - 1;
            System.out.println(i + " 在桶中位置: " + bucket);
        }
    }
}
