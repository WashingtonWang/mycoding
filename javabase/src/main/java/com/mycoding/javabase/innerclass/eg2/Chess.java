package com.mycoding.javabase.innerclass.eg2;

/**
 * user: xiangyu.wang
 * date: 2017/12/8 18:27
 */
public class Chess implements Game {

    private Chess(){}

    private int moves = 0;
    private static final int MOVES = 4;

    @Override
    public boolean move() {
        System.out.println("Chess move " + moves);
        return ++moves != MOVES;
    }

    public static GameFactory factory = () -> new Chess();

    public static GameFactory factory1 =
            new GameFactory() {
                @Override
                public Game getGame() {
                    return new Chess();
                }
            };
}
