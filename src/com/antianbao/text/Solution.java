package com.antianbao.text;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        try {
            System.out.println("请输入一个整数(n<=39)：");
            Scanner s = new Scanner(System.in);
            int n = s.nextInt();
            Solution solution = new Solution();
            System.out.println("斐波那契数列的第" + n + "项：");
            System.out.println(solution.Fibonacci(n));
        } catch (Exception e) {
            System.out.println("请输入数字！");
        }
    }

    public int Fibonacci(int n) {
        int[] num = new int[100];
        num[0] = 0;
        num[1] = 1;
        for (int i = 2; i < 100; i++) {
            num[i] = num[i - 1] + num[i - 2];
        }
        return num[n];
    }
}
