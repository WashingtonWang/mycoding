package stringeg.eg1;

/**
 * user: xiangyu.wang
 * date: 2018/1/8 11:09
 */
public class Immutable {
    public static String upcase(String s){
        return s.toUpperCase();
    }

    public static void main(String[] args) {
        int x = 5;
        double y = 5.123123;
        System.out.println("Row 1: [" + x +" " + y +"]");
        System.out.format("Row 1: [%d, %f]\n", x, y);
        System.out.printf("Row 1: [%d, %f]\n",  x, y);
    }
}
