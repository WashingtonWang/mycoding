package com.xiangyu.algorithm.linkedlist;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/10/15 19:55
 * 反转单链表
 */
public class LinkedListTest {
    public static void main(String[] args) {
        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node tail = new Node(3);

        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(tail);

        Node h = head;
        while (h != null) {
            System.out.print(h.getData() + "->");
            h = h.getNext();
        }

        System.out.println();

        //调用反转方法
        reverse1(head);

        //tail = reverse2(head);

        while (tail != null) {
            System.out.print(tail.getData() + "->");
            tail = tail.getNext();
        }

    }

    //递归实现
    public static Node reverse1(Node head) {
        if (head == null || head.getNext() == null){
            return head;
        }

        Node next = head.getNext();
        Node pre = reverse1(next);
        next.setNext(head);
        head.setNext(null);

        return pre;
    }

    //遍历反转实现
    public static Node reverse2(Node head) {
        if (head == null){
            return head;
        }

        Node pre = head;
        Node cur = head.getNext();
        Node temp;
        while (cur != null){
            temp = cur;
            cur.setNext(pre);

            pre = cur;
            temp = temp.getNext();
        }

        return null;
    }

    static class Node<T> {
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
    }

}
