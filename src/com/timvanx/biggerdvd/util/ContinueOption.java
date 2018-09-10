package com.timvanx.biggerdvd.util;

/**
 * 【是否继续】中数字-内容
 */
public enum ContinueOption {
    Next(1, "继续"), Back(0, "返回上一层");
    private final int key;
    private final String value;

    ContinueOption(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }


}