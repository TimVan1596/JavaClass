package com.zhangmin.com.aiit.jisuanqi;

public class mulOperation extends Operation implements  Main {
    public mulOperation() {
    }

    public Operation cerateOperation() {
        return new mulOperation();
    }

    public int getResult() {
        return this.getNum1() * this.getNum2();
    }
}
