package map.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2019/11/28 15:47
 * 测试数据如下：
 * 第一组：
 * getDif1开始查找不同元素的时间点为：1575292405863
 * getDif1结束查找不同元素的时间点为：1575292589460
 * getDif1查找不同元素花费的时间为： 183597
 * <p>
 * getDif2开始查找不同元素的时间点为：1575292589460
 * getDif2结束查找不同元素的时间点为：1575292737153
 * getDif2查找不同元素花费的时间为： 147693
 * <p>
 * getDif3开始查找不同元素的时间点为：1575292737153
 * getDif3结束查找不同元素的时间点为：1575292737222
 * getDif3查找不同元素花费的时间为： 69
 * 查找到不同的元素数量为：99999
 * 查找到不同的元素数量为：50001
 * 查找到不同的元素数量为：99999
 * <p>
 * 第二组：
 * getDif1开始查找不同元素的时间点为：1575293015943
 * getDif1结束查找不同元素的时间点为：1575293196690
 * getDif1查找不同元素花费的时间为： 180747
 * <p>
 * getDif2开始查找不同元素的时间点为：1575293196690
 * getDif2结束查找不同元素的时间点为：1575293361512
 * getDif2查找不同元素花费的时间为： 164822
 * <p>
 * getDif3开始查找不同元素的时间点为：1575293361512
 * getDif3结束查找不同元素的时间点为：1575293361592
 * getDif3查找不同元素花费的时间为： 80
 * 查找到不同的元素数量为：99999
 * 查找到不同的元素数量为：50001
 * 查找到不同的元素数量为：99999
 */
public class FindTwoListDiff {
    public static void main(String[] args) {

        List<String> listA = backList(1, 100 * 1000, "wangxiangyu____|");
        List<String> listB = backList(100 * 1000 / 2, 100 * 1000 + (100 * 1000 / 2), "wangxiangyu____|");

        //List<String> diffList1 = getDif1(listA, listB);
        //List<String> diffList2 = getDif2(listA, listB);
        //List<String> diffList3 = getDif3(listA, listB);
        //System.out.println("查找到不同的元素数量为：" + diffList1.size());
        //System.out.println("查找到不同的元素数量为：" + diffList2.size());
        //System.out.println("查找到不同的元素数量为：" + diffList3.size());


        getDiffrent6(listA, listB);
        getDiffrent5(listA, listB);
        getDiffrent4(listA, listB);
        getDiffrent3(listA, listB);
        getDiffrent2(listA, listB);
        getDiffrent(listA, listB);

    }

    /**
     * 1.方法一：两次循环数组+contains()方法：
     *
     * @param l1
     * @param l2
     * @return
     */
    private static List<String> getDif1(List<String> l1, List<String> l2) {
        long startTime = System.currentTimeMillis();
        System.out.println("getDif1开始查找不同元素的时间点为：" + startTime);
        List<String> dif = new ArrayList<>();
        for (String str : l1) {
            if (!l2.contains(str)) {
                dif.add(str);
            }
        }
        for (String str : l2) {
            if (!l1.contains(str)) {
                dif.add(str);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("getDif1结束查找不同元素的时间点为：" + endTime);
        System.out.println("getDif1查找不同元素花费的时间为： " + String.valueOf(endTime - startTime));
        //System.out.println(dif);
        System.out.println();
        return dif;

    }

    /**
     * 2.方法二：removeAll()+retainAll()方法：
     *
     * @param l1
     * @param l2
     * @return
     */
    private static List<String> getDif2(List<String> l1, List<String> l2) {
        long startTime = System.currentTimeMillis();
        System.out.println("getDif2开始查找不同元素的时间点为：" + startTime);
        List<String> dif = new ArrayList<>();
        List<String> res = new ArrayList<>();
        dif.addAll(l1);
        //先求出两个list的交集；
        dif.retainAll(l2);
        res.addAll(l1);
        res.addAll(l2);
        //用合集去掉交集，就是不同的元素；
        res.removeAll(dif);
        long endTime = System.currentTimeMillis();
        System.out.println("getDif2结束查找不同元素的时间点为：" + endTime);
        System.out.println("getDif2查找不同元素花费的时间为： " + String.valueOf(endTime - startTime));
        //System.out.println(res);
        System.out.println();
        return dif;

    }

    /**
     * 3.方法三：把数组放进map,key:string,value:次数，最后提取value=1的key;
     *
     * @param l1
     * @param l2
     * @return
     */
    private static List<String> getDif3(List<String> l1, List<String> l2) {
        long startTime = System.currentTimeMillis();
        System.out.println("getDif3开始查找不同元素的时间点为：" + startTime);
        List<String> dif = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for (String str : l1) {
            map.put(str, 1);
        }
        for (String str : l2) {
            if (map.get(str) != null) {
                map.put(str, 2);
                continue;
            } else {
                map.put(str, 1);
            }
        }
        for (Map.Entry<String, Integer> en : map.entrySet()) {
            if (en.getValue() == 1) {
                dif.add(en.getKey());
            }
        }
        //System.out.println(dif);
        long endTime = System.currentTimeMillis();
        System.out.println("getDif3结束查找不同元素的时间点为：" + endTime);
        System.out.println("getDif3查找不同元素花费的时间为： " + String.valueOf(endTime - startTime));
        return dif;

    }

    /**
     * 生成list
     *
     * @param index
     * @param sum
     * @param str
     * @return
     */
    private static List<String> backList(int index, int sum, String str) {
        List<String> list = new ArrayList<>();
        for (int i = index; i <= sum; i++) {
            StringBuilder sb = new StringBuilder(str + String.valueOf(i));
            list.add(sb.toString());
            //System.out.println(sb.toString());
        }

        return list;
    }


    /**
     * 查找两个list不同元素测试结果如下:
     * 第一组：
     * getDiffrent6 total times 72854157
     * getDiffrent5 total times 61990953
     * getDiffrent4 total times 38912116
     * getDiffrent3 total times 27980909
     * getDiffrent2 total times 101104332107
     * getDiffrent total times 16426426852
     * <p>
     * 第二组：
     * getDiffrent6 diff.size: 50000
     * getDiffrent6 total times 74886989
     * getDiffrent5 diff.size: 99999
     * getDiffrent5 total times 67861952
     * getDiffrent4 diff.size: 99999
     * getDiffrent4 total times 32952925
     * getDiffrent3 diff.size: 99999
     * getDiffrent3 total times 30290800
     * getDiffrent2 diff.size: 50001
     * getDiffrent2 total times 108428012272
     * getDiffrent diff.size: 0
     * getDiffrent total times 18576729265
     * <p>
     * <p>
     * 获取两个List的不同元素
     *
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getDiffrent5(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        List<String> diff = new ArrayList<>();
        List<String> maxList = list1;
        List<String> minList = list2;
        if (list2.size() > list1.size()) {
            maxList = list2;
            minList = list1;
        }
        Map<String, Integer> map = new HashMap<>(maxList.size());
        for (String string : maxList) {
            map.put(string, 1);
        }
        for (String string : minList) {
            if (map.get(string) != null) {
                map.put(string, 2);
                continue;
            }
            diff.add(string);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                diff.add(entry.getKey());
            }
        }
        System.out.println("getDiffrent5 diff.size: " + diff.size());
        System.out.println("getDiffrent5 total times " + (System.nanoTime() - st));
        return diff;

    }

    /**
     * 获取两个List的不同元素
     *
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getDiffrent4(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        Map<String, Integer> map = new HashMap<>(list1.size() + list2.size());
        List<String> diff = new ArrayList<>();
        List<String> maxList = list1;
        List<String> minList = list2;
        if (list2.size() > list1.size()) {
            maxList = list2;
            minList = list1;
        }
        for (String string : maxList) {
            map.put(string, 1);
        }
        for (String string : minList) {
            Integer cc = map.get(string);
            if (cc != null) {
                map.put(string, ++cc);
                continue;
            }
            map.put(string, 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                diff.add(entry.getKey());
            }
        }
        System.out.println("getDiffrent4 diff.size: " + diff.size());
        System.out.println("getDiffrent4 total times " + (System.nanoTime() - st));
        return diff;

    }

    /**
     * 获取两个List的不同元素
     *
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getDiffrent3(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        Map<String, Integer> map = new HashMap<>(list1.size() + list2.size());
        List<String> diff = new ArrayList<>();
        for (String string : list1) {
            map.put(string, 1);
        }
        for (String string : list2) {
            Integer cc = map.get(string);
            if (cc != null) {
                map.put(string, ++cc);
                continue;
            }
            map.put(string, 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                diff.add(entry.getKey());
            }
        }
        System.out.println("getDiffrent3 diff.size: " + diff.size());
        System.out.println("getDiffrent3 total times " + (System.nanoTime() - st));
        return diff;
    }

    /**
     * 获取连个List的不同元素
     *
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getDiffrent2(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        list1.retainAll(list2);
        System.out.println("getDiffrent2 diff.size: " + list1.size());
        System.out.println("getDiffrent2 total times " + (System.nanoTime() - st));
        return list1;
    }

    /**
     * 获取两个List的不同元素
     *
     * @param list1
     * @param list2
     * @return
     */
    private static List<String> getDiffrent(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        List<String> diff = new ArrayList<>();
        for (String str : list1) {
            if (!list2.contains(str)) {
                diff.add(str);
            }
        }
        System.out.println("getDiffrent diff.size: " + diff.size());
        System.out.println("getDiffrent total times " + (System.nanoTime() - st));
        return diff;
    }


    /**
     * list2 在list1 中没有的值
     */
    private static List<String> getDiffrent6(List<String> list1, List<String> list2) {
        long st = System.nanoTime();
        Map<String, Integer> map = new HashMap<>();
        List<String> diff = new ArrayList<>();

        for (String string : list1) {
            map.put(string, 1);
        }

        for (String string : list2) {
            if (map.get(string) == null) {
                diff.add(string);
            }
        }

        System.out.println("getDiffrent6 diff.size: " + diff.size());
        System.out.println("getDiffrent6 total times " + (System.nanoTime() - st));
        return diff;
    }

}
