package singleton.eg1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 以枚举类的形式 创建单例对象
 * user: xiangyu.wang
 * date: 2018/3/10 10:31
 */
public class SingletonEnumClass {
    public static void test(){
        ExecutorService es = SingletonEnumClass.ExecutorServiceInstance.INSTANCE.getInstance();
        System.out.println(es);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            new Thread(SingletonEnumClass::test).start();
        }
    }

    enum ExecutorServiceInstance{
        INSTANCE;

        private ExecutorService es;

        ExecutorServiceInstance(){
            es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        }
        public ExecutorService getInstance(){
            return es;
        }
    }
}
