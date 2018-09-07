package com.company.nowcoder;
//用两个栈来实现一个队列，完成队列的Push和Pop操作。
//队列中的元素为int类型。

import java.util.Stack;

public class Solution5 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public static void main(String... args){
        Solution5 so = new Solution5();
        int[] arr = {11,19,2018,9,4};

        for (int i = 0; i < arr.length; i++) {
            so.stack1.push(arr[i]);
        }

        System.out.print("Arr: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(so.stack1.pop()+" ");
        }
        System.out.println("\\");


    }

    public void push(int node) {

    }

    public int pop() {
        return 1;
    }


}