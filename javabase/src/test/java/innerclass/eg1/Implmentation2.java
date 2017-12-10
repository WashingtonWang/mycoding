package innerclass.eg1;

/**
 * @Description: 用匿名内部类 去构造工厂方法  《Think in java》  P.199
 * user: xiangyu.wang
 * date: 2017/12/8 17:36
 */
public class Implmentation2 implements Service {

    private Implmentation2(){}

    @Override
    public void  method1() {
        System.out.println("Implmentation2  method1");
    }

    @Override
    public void method2() {
        System.out.println("Implmentation2  method2");
    }
    //lamdba形式
    public static ServiceFactory factory = () -> new Implmentation2();

    //内部类形式
    public static ServiceFactory factory1 =
            new ServiceFactory() {
                @Override
                public Service getService() {
                    return new Implmentation2();
                }
            };

}
