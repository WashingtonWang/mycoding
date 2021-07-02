package com.xiangyu.algorithm.ksinterview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/5/25 14:00
 * 求一个二叉树的最大宽度(B)
 * 1. 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。
 * 每一层的宽度被定义为该层中所有非空节点的数量
 */
public class WidthOfBinaryTreeTest {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7, null, null);
        TreeNode node8 = new TreeNode(8, null, null);
        TreeNode node9 = new TreeNode(9, null, null);
        TreeNode node10 = new TreeNode(10, null, null);
        TreeNode node11 = new TreeNode(11, null, null);
        TreeNode node12 = new TreeNode(12, null, null);
        TreeNode node6 = new TreeNode(6, node11, node12);
        TreeNode node5 = new TreeNode(5, node9, node10);
        TreeNode node4 = new TreeNode(4, node7, node8);
        TreeNode node3 = new TreeNode(3, node5, node6);
        TreeNode node2 = new TreeNode(2, node4, null);
        TreeNode node1 = new TreeNode(1, node2, node3);
        System.out.println(getMaxWidth(node1));
    }

    public static int getMaxWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        int curNodes = 0;
        int curLevel = 1;

        Map<TreeNode, Integer> levelMap = new HashMap<>();
        levelMap.put(root, curLevel);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            int curNodeLevel = levelMap.get(curNode);
            if (curNode.left != null) {
                queue.add(curNode.left);
                levelMap.put(curNode.left, curLevel + 1);
            }
            if (curNode.right != null) {
                queue.add(curNode.right);
                levelMap.put(curNode.right, curLevel + 1);
            }
            if (curLevel == curNodeLevel) {
                curNodes++;
            } else {
                max = Math.max(max, curNodes);
                curLevel++;
                curNodes = 1;
            }
        }
        max = Math.max(max, curNodes);

        return max;
    }


}




