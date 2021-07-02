package com.xiangyu.algorithm.ksinterview;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/5/25 14:18
 */
public class BroadAndDeepFirstSearchTest {
    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        TreeNode second = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        head.rightNode = three;
        head.leftNode = second;
        second.rightNode = five;
        second.leftNode = four;
        three.rightNode = seven;
        three.leftNode = six;
        System.out.print("广度优先遍历结果：");
        broadFirstSearch(head);
        System.out.println();
        System.out.print("深度优先遍历结果：");
        depthFirstSearch(head);

    }

    //广度优先遍历是使用队列实现的
    public static void broadFirstSearch(TreeNode head) {
        if (head == null) {
            return;
        }
        Queue<TreeNode> myQueue = new LinkedList<>();
        myQueue.add(head);
        while (!myQueue.isEmpty()) {
            TreeNode node = myQueue.poll();
            System.out.print(node.data + " ");
            if (node.leftNode != null) {
                myQueue.add(node.leftNode);
            }
            if (node.rightNode != null) {
                myQueue.add(node.rightNode);
            }
        }
    }

    //深度优先遍历，先遍历左边，后遍历右边,栈先进后出
    public static void depthFirstSearch(TreeNode head) {
        if (head == null) {
            return;
        }

        Stack<TreeNode> myStack = new Stack<>();
        myStack.add(head);
        while (!myStack.isEmpty()) {
            TreeNode node = myStack.pop();
            System.out.print(node.data + " ");
            if (node.rightNode != null) {
                myStack.push(node.rightNode);
            }
            if (node.leftNode != null) {
                myStack.push(node.leftNode);
            }
        }
    }

    /**
     * 二叉树数据结构
     */
    public static class TreeNode {
        int data;
        TreeNode leftNode;
        TreeNode rightNode;

        public TreeNode() {
        }

        public TreeNode(int d) {
            data = d;
        }

        public TreeNode(TreeNode left, TreeNode right, int d) {
            leftNode = left;
            rightNode = right;
            data = d;
        }
    }
}
