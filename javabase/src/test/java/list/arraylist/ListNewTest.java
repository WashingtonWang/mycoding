package list.arraylist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/8/3 14:43
 */
public class ListNewTest {
    public static void main(String[] args) {
        mapTest1();
    }

    public static void test1(){
        List<String> list = new ArrayList<>(3);
        list.add("111");

        list.forEach(System.out::println);
    }

    public static void mapTest1(){
        Map<String, String> map = new HashMap<>(3);
        map.put("1", "1");

        map.forEach((k, v) ->{
            System.out.println(k + "|" + v);
        });
    }
}
