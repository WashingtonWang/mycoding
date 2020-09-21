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

    }

    public static void md5Generator() {
        String salt = SaltTest.saltGenerator();
        //977209254788bc013bd4c522f6ce0984
        String pass = "123456" + "|" + salt;
        for (int i = 0; i < 3; i++) {
            pass = DigestUtils.md5DigestAsHex(pass.getBytes());
            System.out.println(pass);
        }
        //String s = DigestUtils.md5DigestAsHex(("000000xV}gxg4UE4o3<EM#").getBytes());
        System.out.println(pass);
    }
}
