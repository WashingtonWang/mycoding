package concurrent.locks.eg1;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/9/18 09:48
 */
public class CodeBlock2 {
    public static void main(String[] args) {
        Food food = new Food(new Vegetables(), new Fruits());
        Thread thread1 = new Thread(food, "Thread1");
        Thread thread2 = new Thread(food, "Thread2");
        thread1.start();
        thread2.start();
    }
}

/**
 * 同步线程
 */
class Food implements Runnable {
    public Food(Vegetables vegetables, Fruits fruits) {
        this.vegetables = vegetables;
        this.fruits = fruits;
    }

    private Vegetables vegetables;
    private Fruits fruits;

    @Override
    public void run() {
        synchronized (vegetables) {
            for (int i = 0; i < 10; i++) {
                vegetables.addVegetables("cabbage" + i);
                System.out.println(Thread.currentThread().getName() + ":" + vegetables.getLastVegetables());

            }
        }

        synchronized (fruits) {
            for (int i = 0; i < 10; i++) {
                fruits.addFruits("apple" + i);
                System.out.println(Thread.currentThread().getName() + ":" + fruits.getLastFruits());
            }
        }

    }
}

class Fruits {
    private String[] list = new String[10];
    int index;

    public Fruits() {
    }

    public void addFruits(String fruitname) {
        if (index >= 0 && index <= 9) {
            list[index++] = fruitname;
        }
    }

    public String getLastFruits() {
        return list[index - 1];
    }
}

class Vegetables {
    private String[] list = new String[10];
    int index;

    public Vegetables() {
    }

    public void addVegetables(String Vegetablename) {
        if (index >= 0 && index <= 9) {
            list[index++] = Vegetablename;
        }
    }

    public String getLastVegetables() {
        return list[index - 1];
    }
}
