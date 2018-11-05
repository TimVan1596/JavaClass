package com.zhangmin.com.aiit.jisuanqi;

public class addOperation extends Operation implements Main {
    public addOperation() {
    }

    public Operation cerateOperation() {
        return new addOperation();
    }

    public int getResult() {
        return this.getNum1() + this.getNum2();
    }
}
