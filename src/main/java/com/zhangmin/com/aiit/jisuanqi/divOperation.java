package com.zhangmin.com.aiit.jisuanqi;

public class divOperation extends Operation implements Main {
    public divOperation() {
    }

    public Operation cerateOperation() {
        return new divOperation();
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
