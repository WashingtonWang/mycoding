package enumeg.eg2;

import java.util.HashMap;
import java.util.Map;

public enum Operation {
    PLUS("+"){
        double apply(double x, double y) {return x + y;}
    },
    MINUX("-"){
        double apply(double x, double y){return x - y;}
    },
    TIMES("*"){
        double apply(double x, double y){return x * y;}
    },
    DIVIDE("/"){
        double apply(double x, double y){return x / y;}
    };

    private final String symbol;

    Operation(String symbol){
        this.symbol = symbol;
    }


    private static final Map<String, Operation> stringToEnum = new HashMap<>();

    static {
        for (Operation op : values())
            stringToEnum.put(op.toString(), op);
    }

    public Operation fromString(String symbol){
        return stringToEnum.get(symbol);
    }


    @Override
    public String toString(){
        return symbol;
    }

    abstract double apply(double x, double y);

    public static void main(String[] args){
        double x = Double.parseDouble("2");
        double y = Double.parseDouble("4");
        for (Operation op : Operation.values())
            System.out.printf("%f %s %f = %f%n",x, op, y, op.apply(x,y));
    }
}
