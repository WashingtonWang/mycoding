package threadeg.eg5.one;

import java.math.BigInteger;

/**
 * @Description:  使用HashMap和同步机制来初始化缓存 《java并发编程实战》 P.86
 * @Auther: wangxiangyu
 * @Date: 2017/11/6 21:53
 */
public class ExpensiveFunction implements Computable<String, BigInteger>{
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        //在经过长时间计算后
        return new BigInteger(arg);
    }
}
