package com.timvanx.nowcoder;


//例如输入前序遍历序列{1,2,4,7,3,5,6,8}和
// 中序遍历序列{4,7,2,1,5,3,8,6}
// 则重建二叉树并返回


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Solution4 {

    public static void main(String... args) {
        //中左右
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        //左中右
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};

        TreeNode root = new Solution4().reConstructBinaryTree(pre, in);
        new Solution4().displayFirst(root);

    }

//    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
//        //构造后序的长度
//        int rootValue = pre[0];
//        TreeNode root =  new TreeNode(rootValue);
//        //当左子节点存在
//        if (in[0] != rootValue){
//            root.left = new TreeNode(pre[1]);
//        }
//
//        return root;
//    }


    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        TreeNode root =
                reConstructBinaryTree(pre, 0, pre.length - 1
                        , in, 0, in.length - 1);
        return root;
    }

    //前序遍历{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
    private TreeNode reConstructBinaryTree(int[] pre, int startPre
            , int endPre, int[] in, int startIn, int endIn) {

        if (startPre > endPre || startIn > endIn) {
            return null;
        }
        TreeNode root = new TreeNode(pre[startPre]);

        for (int i = startIn; i <= endIn; i++)
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(pre, startPre + 1
                        , startPre + i - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1
                        , endPre, in, i + 1, endIn);
                break;
            }

        return root;
    }


    /**
     * 递归遍历打印所有的节点，先序遍历（中左右）
     */
    private void subDisplayFirst(TreeNode current) {
        if (current != null) {
            System.out.print(current.val + " ");
            subDisplayFirst(current.left);
            subDisplayFirst(current.right);
        }
    }

    private void displayFirst(TreeNode root) {
        System.out.print("先序: ");
        subDisplayFirst(root);
        System.out.println(" /");
    }

}