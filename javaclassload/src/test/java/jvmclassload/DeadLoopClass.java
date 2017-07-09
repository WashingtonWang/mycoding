package jvmclassload;
/**
 * 下面的例子是在《深入了解java虚拟机》的227页 关于<clinit>()方法的初始化
 * Created by wangxiangyu on 2017/7/9.
 */
public class DeadLoopClass {
    static class DeadLoopClassInner{
        static {
            //此处if 必须加上，否则编译不通过
            if (true){
                System.out.println(Thread.currentThread() + "init DeadLoopClass");
                while (true){
                }
            }
        }

    }

    public static void main(String[] args){
        Runnable script = new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread() + "start");
                DeadLoopClass.DeadLoopClassInner inner = new DeadLoopClassInner();
                System.out.println(Thread.currentThread() + "run over");
            }
        };
        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);
        thread1.start();
        thread2.start();
    }

}
