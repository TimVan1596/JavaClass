package com.zhangmin.com.aiit.jisaunqi2;

public class divOperation extends Operation{
    public divOperation() {
    }

    public int getResult() {
        int result = 0;
        if (this.getNum2() != 0) {
            result = this.getNum1() / this.getNum2();
        } else {
            System.out.println("除数不能为0");
        }

        return result;
    }
}
