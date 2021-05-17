package com.mycoding.javabase.threadeg.eg8;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @deprecated 并发的谜题解答器  《java并发编程实战》  P.153
 * user: xiangyu.wang
 * date: 2017/12/2 13:16
 */
public class ConcurrentPuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final ExecutorService exec;
    private final ConcurrentMap<P, Boolean> seen;
    final ValueLatch<Node<P,M>> solution = new ValueLatch<Node<P,M>>();

    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle, ExecutorService exec, ConcurrentMap<P, Boolean> seen) {
        this.puzzle = puzzle;
        this.exec = exec;
        this.seen = seen;
    }

    public List<M> solve() throws InterruptedException{
        try {
            P p = puzzle.initialPosition();
            exec.execute(newTask(p, null, null));
            //阻塞直到找到解答
            Node<P,M> solnNode = solution.getValue();;
            return (solnNode == null) ? null : solnNode.asMoveList();
        }finally {
            exec.shutdown();
        }
    }

    protected Runnable newTask(P p, M m, Node<P, M> n){
        return new SolverTask(p, m, n);
    }

    class SolverTask extends Node<P,M> implements Runnable{

        SolverTask(P pos, M move, Node<P, M> prev) {
            super(pos, move, prev);
        }

        @Override
        public void run() {
            if (solution.isSet() || seen.putIfAbsent(pos, true) != null){
                return; //已经找到了解答或者已经遍历了这个位置
            }
            if (puzzle.isGoal(pos)){
                solution.setValue(this);
            }else{
                for (M m : puzzle.legalMoves(pos)){
                    exec.execute(newTask(puzzle.move(pos,m), m, this));
                }
            }
        }
    }
}

/**
 * 在解决器中找不到解答   《java并发编程实战》  P.155
 * @param <P>
 * @param <M>
 */
class PuzzleSolver<P,M> extends ConcurrentPuzzleSolver<P,M>{

    private final AtomicInteger taskCount = new AtomicInteger(0);

    public PuzzleSolver(Puzzle<P, M> puzzle, ExecutorService exec, ConcurrentMap<P, Boolean> seen) {
        super(puzzle, exec, seen);
    }

    protected Runnable newTask(P p, M m, Node<P, M> n){
        return new CountingSolverTask(p, m, n);
    }

    class CountingSolverTask extends SolverTask{
        CountingSolverTask(P pos, M move, Node<P, M> prev){
            super(pos, move, prev);
            taskCount.incrementAndGet();
        }
        public void run(){
            try {
                super.run();
            }finally {
                if (taskCount.decrementAndGet() == 0){
                    solution.setValue(null);
                }
            }
        }
    }

}

/**
 * @Description:  由ConcurrentPuzzleSolver使用的携带结果的闭锁  《java并发编程实战》  P.154
 * @param <T>
 */
class ValueLatch<T>{
    private T value = null;
    private final CountDownLatch done = new CountDownLatch(1);
    public boolean isSet(){
        return (done.getCount() == 0);
    }
    public synchronized void setValue(T newValue){
        if (!isSet()){
            value = newValue;
            done.countDown();
        }
    }
    public T getValue() throws InterruptedException{
        done.await();
        synchronized (this){
            return value;
        }
    }
}

/**
 * @deprecated  表示“搬箱子”之类谜题的抽象类  《java并发编程实战》  P.151
 * @param <P>
 * @param <M>
 */
interface Puzzle<P, M>{
    P initialPosition();
    boolean isGoal(P position);
    Set<M> legalMoves(P position);
    P move(P position, M move);
}

/**
 * @deprecated 用于谜题解决框架的链表节点  《java并发编程实战》  P.151
 * @param <P>
 * @param <M>
 */
@Immutable
class Node<P, M>{
    final P pos;
    final M move;
    final Node<P, M> prev;

    Node(P pos, M move, Node<P, M> prev) {
        this.pos = pos;
        this.move = move;
        this.prev = prev;
    }

    List<M> asMoveList(){
        List<M> solution = new LinkedList<>();
        for (Node<P, M> n = this; n.move != null; n = n.prev){
            solution.add(0, n.move);
        }
        return solution;
    }
}

/**
 * @deprecated 串行的谜题解答器  《java并发编程实战》  P.152
 * @param <P>
 * @param <M>
 */
class SequentialPuzzleSolver<P, M>{
    private final Puzzle<P, M> puzzle;
    private final Set<P> seen = new HashSet<>();

    public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
    }

    public List<M> solve(){
        P pos = puzzle.initialPosition();
        return search(new Node<P, M>(pos, null, null));
    }

    private List<M> search(Node<P,M> node){
        if (!seen.contains(node.pos)){
            seen.add(node.pos);
            if (puzzle.isGoal(node.pos)){
                return node.asMoveList();
            }
            for (M move : puzzle.legalMoves(node.pos)){
                P pos = puzzle.move(node.pos, move);
                Node<P, M> child = new Node<P, M>(pos, move, node);
                List<M> result = search(child);
                if (result != null){
                    return result;
                }
            }
        }
        return null;
    }
}
