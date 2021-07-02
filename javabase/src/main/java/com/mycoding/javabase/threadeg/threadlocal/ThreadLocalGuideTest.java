package com.mycoding.javabase.threadeg.threadlocal;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/3/4 下午10:52
 */
public class ThreadLocalGuideTest {
    private final List<String> messages = new ArrayList<>();

    public static final ThreadLocal<ThreadLocalGuideTest> holder = ThreadLocal.withInitial(ThreadLocalGuideTest::new);

    public static void add(String message) {
        holder.get().messages.add(message);
    }

    public static List<String> clear() {
        List<String> messages = holder.get().messages;
        holder.remove();

        System.out.println("size: " + holder.get().messages.size());
        return messages;
    }

    public static void main(String[] args) {
        ThreadLocalGuideTest.add("一枝花算不算浪漫");
        System.out.println(holder.get().messages);
        ThreadLocalGuideTest.clear();
    }
}
