package overloadedeg.eg1;

import java.util.*;

public class SetList {

    public static void main(String[] args){
        //Set<Integer> set = new TreeSet<>();
        //List<Integer> collection = new ArrayList<>();
        //
        //for (int i  = -3; i < 3; i++){
        //    set.add(i);
        //    collection.add(i);
        //}
        ///**错误的情形*/
        ////for (int i = 0; i < 3; i++){
        ////    set.remove(i);
        ////    collection.remove(i);
        ////}
        //
        ///**正确的情形*/
        //for (int i = 0; i< 3; i++){
        //    set.remove(i);
        //    collection.remove((Integer)i);
        //}
        //
        //System.out.println(set + "  "+ collection);

        int[] digits = {1,34,5,6,7,5};
        System.out.println(Arrays.asList(digits));
        System.out.println(Arrays.toString(digits));

        System.out.println(1.03 - .42);
    }
}
