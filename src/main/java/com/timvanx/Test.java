package com.timvanx;


import java.net.Socket;


abstract interface A {
    public static final int a = 10;

    public abstract void foo();
}

public class Test {
    public static void aMethod() throws Exception {
        try {
            Integer.parseInt("abc");
        } catch (Exception e) {
            System.out.print("err1 ");
        }
    }

    public static void main(String args[]) {
        try {
            aMethod();
        } catch (Exception e) {
            System.out.print("err2 ");
        } finally {
            System.out.print("finally");
        }
    }
}
