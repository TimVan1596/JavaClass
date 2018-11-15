package com.zhangmin.com.aiit.jisaunqi2;

/*
* 计算工厂
*
* */
public class Main {
    public Main() {
    }

    public static Operation createOperate(String flag) {
        Operation operation = null;
        if (flag.equals("+")) {
            operation = new addOperation();
        } else if (flag.equals("-")) {
            operation = new subOperation();
        } else if (flag.equals("*")) {
            operation = new mulOperation();
        } else if (flag.equals("/")) {
            operation = new divOperation();
        }

        return operation;
    }
}
