package com.xiangyu.algorithm;

import com.xiangyu.algorithm.linked.Node;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/3/24 18:21
 */
public class MyTest4All {
    public static void main(String[] args) {
        Node<Integer> node = Node.getLinkedNode(10);
        Node.printLinked(node);

        Node<Integer> result = reverse(node);
        Node.printLinked(result);
    }

    private static Node<Integer> reverse(Node<Integer> head) {
        if (head == null || head.getNext() == null) {
            return head;
        }

        Node<Integer> current = head, pre = null;
        while (current != null) {
            Node<Integer> next = current.getNext();
            current.setNext(pre);
            pre = current;
            current = next;
        }

        return pre;
    }

}
