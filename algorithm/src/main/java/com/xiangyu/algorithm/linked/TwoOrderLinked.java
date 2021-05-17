package com.xiangyu.algorithm.linked;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/3/10 19:58
 */
public class TwoOrderLinked {
    public static void main(String[] args) {
        Node<Integer> left = Node.getLinkedNode(0, 5, 30);
        Node.printLinked(left);

        Node<Integer> right = Node.getLinkedNode(2, 5, 30);
        Node.printLinked(right);

        Node<Integer> newNode = merge(left, right);
        Node.printLinked(newNode);

        //Node<Integer> result = recursion(left, right);
        //Node.printLinked(result);
    }

    public static Node<Integer> merge(Node<Integer> left, Node<Integer> right) {
        if (left == null || right == null) {
            return left == null ? right : left;
        }
        //merge after node
        Node<Integer> preHead = new Node<>(-1);

        Node<Integer> pre = preHead;
        while (left != null && right != null) {
            if (left.getData() <= right.getData()) {
                pre.setNext(left);
                left = left.getNext();
            } else {
                pre.setNext(right);
                right = right.getNext();
            }
            pre = pre.getNext();
        }

        pre.setNext(left == null ? right : left);

        return preHead.getNext();
    }

    //递归
    public static Node<Integer> recursion(Node<Integer> left, Node<Integer> right) {
        if (left == null || right == null) {
            return left == null ? right : left;
        }

        if (left.getData() < right.getData()) {
            left.setNext(recursion(left.getNext(), right));
            return left;
        } else {
            right.setNext(recursion(left, right.getNext()));
            return right;
        }
    }
}
