package com.timvanx.nowcoder;


//大家都知道斐波那契数列，现在要求输入一个整数n，
// 请你输出斐波那契数列的第n项（从0开始，第0项为0）。
//n<=39

public class Solution7 {
    public static void main(String... args) {
        System.out.println(new Solution7().Fibonacci(0));
        System.out.println(new Solution7().Fibonacci(1));
        System.out.println(new Solution7().Fibonacci(2));
        System.out.println(new Solution7().Fibonacci(3));
        System.out.println(new Solution7().Fibonacci(4));
        System.out.println(new Solution7().Fibonacci(5));
        System.out.println(new Solution7().Fibonacci(6));
    }

    public int Fibonacci(int n) {
        int ret = 0;
        int first = 0, second = 1, third = 2;

        if (n <= 1) {
            ret = n;
        } else {
            ret = 1;
            int last = 0, curr = ret;
            for (int i = 1; i < n; i++) {
                int temp = curr + last;
                last = curr;
                curr = temp;
            }
            ret = curr;
        }


        return ret;
    }
}