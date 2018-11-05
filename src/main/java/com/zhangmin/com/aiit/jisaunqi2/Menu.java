package com.zhangmin.com.aiit.jisaunqi2;

import java.util.Scanner;

public class Menu {
    public Menu() {
    }

    public static void main(String[] args) {
        System.out.println("请输入第一个数字：");
        Scanner scanner = new Scanner(System.in);
        int num1 = scanner.nextInt();
        System.out.println("请输入第二个数字：");
        int num2 = scanner.nextInt();
        System.out.println("请选择你的运算符：+ - * /");
        String flag = scanner.next();
        Operation operation = Main.createOperate(flag);
        operation.setNum1(num1);
        operation.setNum2(num2);
        int result = operation.getResult();
        System.out.println("计算结果：" + result);
    }
}
