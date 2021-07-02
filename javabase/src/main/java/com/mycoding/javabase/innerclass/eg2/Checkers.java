package com.mycoding.javabase.innerclass.eg2;

/**
 * user: xiangyu.wang
 * date: 2017/12/8 18:16
 */
public class Checkers implements Game {

    private Checkers(){}

    private int moves = 0;
    private static final int MOVES = 3;

    @Override
    public boolean move() {
        System.out.println("Checkers move " + moves);
        return ++moves != MOVES;
    }

    //lamdba形式
    public static GameFactory factory = () -> new Checkers();

    //内部类形式
    public static GameFactory factory1 =
            new GameFactory() {
                @Override
                public Game getGame() {
                    return new Checkers();
                }
            };
}
