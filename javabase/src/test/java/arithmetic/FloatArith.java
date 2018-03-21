package arithmetic;

import java.math.BigDecimal;

public class FloatArith {
    static  Integer i;
    public static void main(String[] args){
        //floatArithOne();
        //bigDecimalArith();
        //longArith();
        if (i == 23)
            System.out.println("24234");
    }

    private static void longArith(){
        int itemsBought = 0;
        int funds = 100;
        for (int price = 10; funds >= price; price += 10){
            itemsBought++;
            funds -= price;
        }
        System.out.println(itemsBought + " items bought");
        System.out.println("Money left over: " + funds + " cents");
    }

    /**
     * 下面的方法是使用BigDecimal 缺点 1、用着不方便，2、性能慢
     */
    private static void bigDecimalArith(){
        final BigDecimal TEN_CENTS = new BigDecimal(".10");
        int itemsBought = 0;
        BigDecimal funds = new BigDecimal("1.00");
        for (BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)){
            itemsBought++;
            funds = funds.subtract(price);
        }
        System.out.println(itemsBought + " items bought");
        System.out.println("Money left over: $" + funds);
    }


    /**
     * 下面的方法是错误的计算方式（使用float,double不能计算金额等精确的运算）
     */
    private static void floatArithOne(){
        double funds = 1.00;
        int itemsBought = 0;
        for (double price = .10; funds >= price; price += .10){
            funds -= price;
            itemsBought++;
        }
        System.out.println(itemsBought + " items bought");
        System.out.println("Change : $" + funds);
    }
}
