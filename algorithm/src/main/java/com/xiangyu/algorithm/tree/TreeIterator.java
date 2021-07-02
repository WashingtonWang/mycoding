package com.xiangyu.algorithm.tree;

import java.util.Stack;

/**
 * @Description: This is description
 * @Auther: wangxiangyu
 * @Date: 2021/5/26 10:48
 */
public class TreeIterator {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left2 = new TreeNode(2);
        TreeNode right2 = new TreeNode(3);
        root.left = left2;
        root.right = right2;

        TreeNode left3 = new TreeNode(4);
        TreeNode right3 = new TreeNode(5);
        left2.left = left3;
        left2.right = right3;

        TreeNode left4 = new TreeNode(6);
        TreeNode right4 = new TreeNode(7);
        right2.left = left4;
        right2.right = right4;

        TreeNode left5 = new TreeNode(8);
        TreeNode right5 = new TreeNode(9);
        left3.left = left5;
        left3.right = right5;
        //前序遍历
        System.out.println("--------前序遍历--------");
        preOrder(root);
        System.out.println();
        preOrderTraversal(root);
        System.out.println();
        //中序遍历
        System.out.println("--------中序遍历--------");
        inOrderTraversal(root);
        System.out.println();
        inOrderIterator(root);
        System.out.println();
    }

    /**
     * 前序遍历（DLR），是二叉树遍历的一种，也叫做先根遍历、先序遍历，可记做根左右。
     * 前序遍历首先访问根结点然后遍历左子树，最后遍历右子树。在遍历左、右子树时，仍然先访问根结点，然后遍历左子树，最后遍历右子树。
     * 若二叉树为空则结束返回，否则：
     * 1.访问根结点
     * 2.前序遍历左子树
     * 2.前序遍历右子树
     */
    //1.递归实现
    public static void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    //2.非递归实现
    public static void preOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();//用一个栈来存放树中的节点
        while (root != null || !stack.isEmpty()) {//只要当前节点不为空(即当前节点的左右子树没有访问完毕)或者栈中还有节点(还有节点没有访问)
            while (root != null) {//一直往左走
                stack.push(root);//根节点入栈
                System.out.print(root.val + " ");//打印根节点
                root = root.left;//访问左子树
            }
            root = stack.pop();//取出根节点
            root = root.right;//访问右子树
        }
    }

    /**
     * 中序遍历（LDR）是二叉树遍历的一种，也叫做中根遍历、中序周游。可记做左根右
     * 中序遍历首先遍历左子树，然后访问根结点，最后遍历右子树。
     * 若二叉树为空则结束返回，否则：
     * 1.中序遍历左子树
     * 2.访问根结点
     * 3.中序遍历右子树
     * 如图：中序遍历结果:DBEAFC
     */
    //1.递归实现
    public static void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.val + " ");
        inOrderTraversal(root.right);
    }

    //2.非递归实现
    public static void inOrderIterator(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()){//只要当前节点不为空(即当前节点的左右子树没有访问完毕)或者栈中还有节点(还有节点没有访问)
            while(root != null){
                stack.push(root);//根节点入栈
                root = root.left;//访问左子树
            }
            root = stack.pop();//取出左子树的根节点
            System.out.print(root.val + " ");//打印根节点
            root = root.right;//访问右子树
        }
    }


    static class TreeNode {//TreeNode类，后边同样
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int val) {
            this.val = val;
        }
    }
}


