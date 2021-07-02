package com.xiangyu.algorithm.linked;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2020/10/15 19:55
 * 反转单链表
 */
public class LinkedReverseTest {
    public static void main(String[] args) {
        //生成链表
        Node<Integer> head = Node.getLinkedNode(10);

        Node<Integer> h = head;
        while (h != null) {
            System.out.print(h.getData() + "->");
            h = h.getNext();
        }

        System.out.println();

        //调用反转方法
        //head = reverse1(head);
        head = reverse2(head);

        while (head != null) {
            System.out.print(head.getData() + "->");
            head = head.getNext();
        }

    }


    //递归实现
    public static Node<Integer> reverse1(Node<Integer> head) {
        if (head == null || head.getNext() == null) {
            return head;
        }

        Node<Integer> next = head.getNext();
        Node<Integer> pre = reverse1(next);
        next.setNext(head);
        head.setNext(null);

        return pre;
    }

    //遍历反转实现
    public static Node<Integer> reverse2(Node<Integer> head) {
        if (head == null || head.getNext() == null) {
            return head;
        }

        Node<Integer> pre = head;
        Node<Integer> cur = head.getNext();
        while (cur.getNext() != null) {
            Node<Integer> temp = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = temp;
        }
        cur.setNext(pre);
        head.setNext(null);

        return cur;
    }
}
