package foreach;

import java.util.ArrayList;
import java.util.List;

/**
 * user: xiangyu.wang
 * date: 2019/1/27 16:10
 */
public class ForEg1 {

    public static void main(String[] args) {
        test2();
    }

    public static void test1(){
        for (int i = 0; i < 10; i++){
            System.out.println("i------>" + i);
            for (int j = 0; j < 10; j++){
                if (j == 5){
                    return;
                }
                System.out.println("j----->" + j);
            }
        }
        System.out.println("完事了");
    }

    public static void test2(){
        List<String> list = new ArrayList<>();
        list.add("0001");
        list.add("0002");
        list.add("0003");
        list.add("0004");
        list.add("0005");
        for (int i = 0; i < 10; i++){
            list.forEach(ll ->{
                if (ll.equals("0004")){
                    return;
                }
                System.out.println("ll----->" + ll);
            });
            System.out.println("i----->" + i);
        }

    }
}
