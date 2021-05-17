package com.mycoding.javabase.enumeg;

public enum BasicOperation implements OperationExt{
    PLUS("+"){
        public double apply(double x, double y) {return x + y;}
    },
    MINUX("-"){
        public double apply(double x, double y){return x - y;}
    },
    TIMES("*"){
        public double apply(double x, double y){return x * y;}
    },
    DIVIDE("/"){
        public double apply(double x, double y){return x / y;}
    };

    private final String symbol;

    BasicOperation(String symbol){
        this.symbol = symbol;
    }
    @Override
    public String toString(){
        return symbol;
    }
}
