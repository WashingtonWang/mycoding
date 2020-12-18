package encrypt.eg1;

import org.springframework.util.DigestUtils;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/8/24 16:32
 */
public class Md5Test {
    public static void main(String[] args) {
        md5Generator();

        //for (int i = 0; i < 100; i++) {
        //    int secretTimes = new Random().nextInt(4) + 2;
        //    System.out.print(secretTimes);
        //}
        //7dvF$M\J%n3sYWc7
        //4d173ded6408186ed339a2165bd3401d

    }

    public static void md5Generator() {
        String salt = SaltTest.saltGenerator();
        //977209254788bc013bd4c522f6ce0984
        String pass = "000000" + "|" + "7dvF$M\\J%n3sYWc7";
        for (int i = 0; i < 3; i++) {
            pass = DigestUtils.md5DigestAsHex(pass.getBytes());
        }
        //String s = DigestUtils.md5DigestAsHex(("000000xV}gxg4UE4o3<EM#").getBytes());
        System.out.println(pass);
    }
}
