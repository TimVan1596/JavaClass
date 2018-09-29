package com.timvanx;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public Test() {
    }

    static void print(ArrayList al) {

    }

    public static void main(String[] args) {
        List plist = new ArrayList();
        plist.add("A");
        plist.add("B");
        for (int i = 0; i < plist.size(); i++) {
            String str = plist.get(i).toString();
            System.out.println(str);
        }
    }
}