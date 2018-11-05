package com.zhangmin.com.aiit.jisuanqi;

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
        Main factory = null;
        Operation operation = null;
        switch(flag.hashCode()) {
            case 42:
                if (flag.equals("*")) {
                     factory = new mulOperation();
                    operation = factory.cerateOperation();
                    operation.setNum1(num1);
                    operation.setNum2(num2);
                    System.out.println("计算结果：" + operation.getResult());
                }
                break;
            case 43:
                if (flag.equals("+")) {
                    factory = new addOperation();
                    operation = factory.cerateOperation();
                    operation.setNum1(num1);
                    operation.setNum2(num2);
                    System.out.println("计算结果：" + operation.getResult());
                }
                break;
            case 45:
                if (flag.equals("-")) {
                    factory = new subOperation();
                    operation = factory.cerateOperation();
                    operation.setNum1(num1);
                    operation.setNum2(num2);
                    System.out.println("计算结果：" + operation.getResult());
                }
                break;
            case 47:
                if (flag.equals("/")) {
                    factory = new divOperation();
                    operation = factory.cerateOperation();
                    operation.setNum1(num1);
                    operation.setNum2(num2);
                    System.out.println("计算结果：" + operation.getResult());
                }
        }

    }
}
