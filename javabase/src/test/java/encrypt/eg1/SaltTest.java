package encrypt.eg1;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/8/24 15:05
 */
public class SaltTest {

    private static final Random RANDOM = new Random();

    public static void main(String[] args) {
        saltGenerator();
    }

    public static String saltGenerator() {
        String str1 = "abcdefghijklmnopqrstuvwxyz";
        String str2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String str3 = "12345465870";
        String str4 = "!`~@#$%^&*()_+-=|}]{[\"':;?/>.<,'\\";

        /**符号生成 3位*/
        String symbol = symbolGenerator(str4);

        String number = numberGenerator(str3);

        String uppCase = uppercaseGenerator(str2);

        String lowCase = lowercaseGenerator(str1);

        String allStr = symbol + number + uppCase + lowCase;
        System.out.println(allStr);

        StringBuilder sb = new StringBuilder();
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        int times = 0;
        long a = System.currentTimeMillis();
        do {
            int next = RANDOM.nextInt(allStr.length());
            System.out.println(++times);
            if (!Objects.isNull(hashMap.get(next))) {
                continue;
            }
            hashMap.put(next, 1);

            sb.append(allStr.charAt(next));
        } while (sb.length() < allStr.length());
        System.out.println(System.currentTimeMillis() - a);

        System.out.println(sb.toString());

        return sb.toString();
    }

    /**
     * 符号生成 3位
     */
    private static String symbolGenerator(String str4) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int r = RANDOM.nextInt(str4.length());
            char s = str4.charAt(r);
            sb.append(s);
        }

        return sb.toString();
    }

    /**
     * 数字生成 3位
     */
    private static String numberGenerator(String str3) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int r = RANDOM.nextInt(str3.length());
            char s = str3.charAt(r);
            sb.append(s);
        }

        return sb.toString();
    }

    /**
     * 大写生成 5位
     */
    private static String uppercaseGenerator(String str2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int r = RANDOM.nextInt(str2.length());
            char s = str2.charAt(r);
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 小写生成 5位
     */
    private static String lowercaseGenerator(String str1) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int r = RANDOM.nextInt(str1.length());
            char s = str1.charAt(r);
            sb.append(s);
        }
        return sb.toString();
    }
}
