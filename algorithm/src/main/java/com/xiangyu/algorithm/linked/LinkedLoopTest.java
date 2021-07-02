package com.xiangyu.algorithm.linked;

import java.util.Objects;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/3/10 12:59
 * 链表环检测
 */
public class LinkedLoopTest {
    public static void main(String[] args) {
        //生成链表环
        Node<Integer> head = Node.getLoopLinked(10);
        Node.printLoopLinked(head);

        //查找坏是否存在
        //boolean result = isLoopList(head);
        //System.out.println(result);

        Node<Integer> node = startNode(head);
        System.out.println(node.getData());
    }

    /**
     * 链表中是否存在环
     *
     * @param head
     * @return
     */
    public static boolean isLoopList(Node<Integer> head) {
        if (Objects.isNull(head)) {
            return false;
        }
        Node<Integer> slow = head;
        Node<Integer> fast = head;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    /**
     * 查找环的起点
     *
     * @param head
     * @return
     */
    public static Node<Integer> startNode(Node<Integer> head) {
        if (head == null) {
            return null;
        }

        Node<Integer> slow, fast;
        slow = fast = head;
        boolean isLoop = false;
        while (fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (fast == slow) {
                isLoop = true;
                break;
            }
        }

        if (isLoop) {
            fast = head;
            while (slow != null && slow.getNext() != null) {
                slow = slow.getNext();
                fast = fast.getNext();
                if (slow == fast) {
                    return slow;
                }
            }
        }

        return null;
    }
}
