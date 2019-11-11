package threadeg.deadlock;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/10/31 08:20
 *
 * 死锁是指两个或两个以上的进程在执行过程中，由于竞争资源或者由于彼此通信而造成的一种阻塞的现象，若无外力作用，它们都将无法推进下去。
 * 此时称系统处于死锁状态，这些永远在互相等待的进程称为死锁进程。
 *
 * 方式一（在synchronized的方法中调用对方对象的方法）
 */
public class MyTest {
    public static void main(String[] args) {
        MyThreadTestOne myOne = new MyThreadTestOne("A");
        MyThreadTestOne myTwo = new MyThreadTestOne("B");

        myOne.setLocks(myTwo);
        myTwo.setLocks(myOne);

        Thread one = new Thread(myOne, "Thread-one");
        one.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread two = new Thread(myTwo, "Thread-two");
        two.start();
    }
}

class MyThreadTestOne implements Runnable{

    private String objName;
    private MyThreadTestOne lockThread;

    public MyThreadTestOne(String objName){
        this.objName = objName;
    }

    public void setLocks(MyThreadTestOne my){
        this.lockThread = my;
    }

    public synchronized void intoB(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lockThread.intoA();
    }

    public synchronized void intoA(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lockThread.intoB();
    }

    @Override
    public void run() {
        if (objName.equals("A")){
            intoA();
        } else {
            intoB();
        }
    }
}
