package threadeg.threadlocal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/6/25 14:34
 * 因为传送逻辑是在创建子线程的时候完成的，子线程创建后，主线程在修改InheritableThreadLocal变量的值，是无法传给子线程的
 * 创建子线程完成后，原则上子线程和父线程中InheritableThreadLocal变量的值在没有关联，各自调用set/get/remove都只影响本线程中的值
 * 如果InheritableThreadLocal变量的值是引用类型，通过get方法获取到对象后，直接修改了该对象的属性，则父线程和子线程都会受影响
 *
 * 原文链接：https://blog.csdn.net/zhouping118/article/details/99862034
 */
public class ThreadLocalInheritableTest {

        private static List getList(String param) {
            List rst = new ArrayList<>();
            rst.add(param);

            return rst;
        }

        private static final InheritableThreadLocal<List> threadLocal = new InheritableThreadLocal<>();

        public static void test(Consumer<InheritableThreadLocal<List>> consumer) throws InterruptedException {
            threadLocal.set(getList("test"));

            Thread child = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()){
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("child子线程中threadLocal的值：" + threadLocal.get());
                }
            });

            System.out.println("before主线程中threadLocal的值：" + threadLocal.get());
            child.start();
            TimeUnit.MILLISECONDS.sleep(1);
            consumer.accept(threadLocal);
            System.out.println("after主线程中threadLocal的值：" + threadLocal.get());
            TimeUnit.MILLISECONDS.sleep(3);
            child.interrupt();
        }

        public static void main(String[] args) throws InterruptedException {
            //**创建子线程完成后，主线程调用set方法修改值，不会影响到子线程
            test(local -> local.set(getList("test1")));
            System.out.println("===========================");
            //**保存list对象时，通过get方法获取，然后修改list的值，则会影响到子线程
            test(local -> local.get().set(0, "test2"));
        }
}
