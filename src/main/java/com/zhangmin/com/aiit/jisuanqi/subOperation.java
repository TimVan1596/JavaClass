package com.zhangmin.com.aiit.jisuanqi;

public class subOperation extends Operation implements Main {
    public subOperation() {
    }

    public Operation cerateOperation() {
        return new subOperation();
    }

    public int getResult() {
        return this.getNum1() - this.getNum2();
    }
}
