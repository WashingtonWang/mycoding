package com.mycoding.javabase.innerclass.eg2;

/**
 * user: xiangyu.wang
 * date: 2017/12/8 18:30
 */
public class Games {

    public static void playGame(GameFactory factory){
        Game s = factory.getGame();
        while (s.move()){

        }
    }

    public static void main(String[] args) {
        playGame(Checkers.factory);
        playGame(Chess.factory);
    }
}