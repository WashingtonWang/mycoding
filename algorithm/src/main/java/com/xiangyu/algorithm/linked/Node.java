package com.xiangyu.algorithm.linked;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/3/10 12:50
 */
public class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * 生成链表
     *
     * @param num
     * @return
     */
    public static Node<Integer> getLinkedNode(Integer num) {
        if (num < 1) {
            return null;
        }
        Node<Integer> head = new Node<>(0);
        Node<Integer> cur = head;
        /**
         * 在jdk1.8.0_91版本中，当上述链表长度大于12000则会出现StackOverFlowError错误。说明对于该版本jdk栈的深度不能大于12000
         */
        for (int i = 1; i <= num; i++) {
            Node<Integer> node = new Node<>(i);
            cur.setNext(node);
            cur = node;
        }
        return head;
    }

    public static Node<Integer> getLinkedNode(int start, int step, int end) {
        if (start >= end) {
            return null;
        }

        Node<Integer> head = new Node<>(start);
        Node<Integer> node = head;
        for (int i = start + step; i <= end; i += step) {
            Node<Integer> nodeI = new Node<>(i);
            node.setNext(nodeI);
            node = nodeI;
        }

        return head;
    }

    /**
     * 生成链表环
     *
     * @param num
     * @return
     */
    public static Node<Integer> getLoopLinked(Integer num) {
        if (num < 1) {
            return null;
        }
        Node<Integer> head = new Node<>(0);
        Node<Integer> cur = head;
        /**
         * 在jdk1.8.0_91版本中，当上述链表长度大于12000则会出现StackOverFlowError错误。说明对于该版本jdk栈的深度不能大于12000
         */
        for (int i = 1; i <= num; i++) {
            Node<Integer> node = new Node<>(i);
            cur.setNext(node);
            cur = node;
        }

        Node<Integer> curTwo = head;
        int i = 0;
        while (curTwo.getNext() != null) {
            if (i == 5) {
                cur.setNext(curTwo);
                break;
            }
            curTwo = curTwo.getNext();
            ++i;
        }

        return head;
    }


    /**
     * 打印链表
     *
     * @param head
     */
    public static void printLinked(Node<Integer> head) {
        Node<Integer> h = head;
        while (h != null) {
            System.out.print(h.getData() + "->");
            h = h.getNext();
        }
        System.out.println();
    }

    /**
     * 打印链表环
     *
     * @param head
     */
    public static void printLoopLinked(Node<Integer> head) {
        Node<Integer> h = head;
        int i = 0;
        while (h != null) {
            if (i >= 17) {
                break;
            }
            System.out.print(h.getData() + "->");
            h = h.getNext();
            ++i;
        }

        System.out.println();
    }

}
