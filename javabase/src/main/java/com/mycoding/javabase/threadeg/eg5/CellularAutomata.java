//package com.mycoding.javabase.threadeg.eg5;
//
//import java.util.com.mycoding.javabase.concurrent.CyclicBarrier;
//
///**
// * @Description: This is description
// * @Auther: wangxiangyu
// * @Date: 2017/11/5 22:34
// */
//public class CellularAutomata {
//    private final Board mainBoard;
//    private final CyclicBarrier barrier;
//    //private final Worker
//
//    private class Worker implements Runnable{
//        private final Board board;
//        public Worker(Board board){
//            this.board = board;
//        }
//
//        @Override
//        public void run() {
//            while (!board.hasConverged()){
//                for (int x = 0; x < board.getMaxX(); x++){
//                    for (int y = 0; y < board.getMaxY(); y++){
//                        board
//                    }
//                }
//            }
//        }
//    }
//
//    private static class Board{
//        private boolean hasConverged(){
//            return true;
//        }
//
//        private int getMaxX(){
//            return 12;
//        }
//
//        private int getMaxY(){
//            return 12;
//        }
//
//        private void setNewValue(int x, int y, )
//    }
//
//
//}
