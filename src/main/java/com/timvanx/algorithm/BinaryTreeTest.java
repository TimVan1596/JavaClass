package com.timvanx.algorithm;
/**
 * 二叉树示例
 * @author TimVan
 * @date 2018-9-3 15:03:05
 * */
public class BinaryTreeTest {

    public static void main(String... args){

        System.out.println("hello");


    }
}


/**
 * 二叉树
 * */
class BinaryTree {

    /**
     * 公共变量
     * root 根节点
     * */
    Node root;

    /**
     * 对进行二叉树插入操作
     * @param key 键
     * @param value 值
     * @return boolean 是否插入成功
     * */
    public boolean insert(int key , double value){
        Node newNode = new Node();
        newNode.key = key;
        newNode.value = value;


        if (root == null){
            root = newNode;
        }
        else{

            Node parent = root;
            Node current;
            while (true){
                current = parent;

                if (current.leftNode == null){
                    current.leftNode = newNode;
                    return true;
                }
                else if (current.rightNode == null){
                    current.rightNode = newNode;
                    return true;
                }

                parent = current.leftNode;
            }

        }

        return false;
    }

    /**
     * 递归遍历打印所有的节点，先序遍历（中左右）
     * */
    private void subDisplayFirst(Node current){
        if (current != null){
            System.out.print(current.key+" ");
            subDisplayFirst(current.leftNode);
            subDisplayFirst(current.rightNode);
        }
    }

    public void displayFirst(){
        System.out.print("先序:");
        subDisplayFirst(root);
        System.out.println(" end");
    }
}


/**
 * 二叉树节点
 * */
class Node{

    /**
     * 公共变量
     * @param key value 键值对
     * @param leftNode rightNode 左右节点
     * */

    int key;
    double value;
    Node leftNode;
    Node rightNode;

    public void display(){
        System.out.println("key = "+ key +"- value = "+ value);
    }

}


