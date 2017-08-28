package enumeg.eg2;

public enum ExtendedOperation implements OperationExt{

    EXP("^"){
        public double apply(double x, double y){
            return Math.pow(x,y);
        }
    },
    REMAINDER("%"){
        public double apply(double x, double y){
            return x % y;
        }
    };

    private final String symbol;

    ExtendedOperation(String symbol){
        this.symbol = symbol;
    }
    @Override
    public String toString(){
        return symbol;
    }

}