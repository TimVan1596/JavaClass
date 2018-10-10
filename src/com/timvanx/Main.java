package com.timvanx;

abstract class Person {
    public void Sing() {
        System.out.print("L1");//L1
    }

    public void Sing(int volume) {
        System.out.print("L2");//L2
    }
}

class Female extends Person {
    public void sing() {
        System.out.print("L3");//L3
    }

    public void sing(int volume) {
        System.out.print("L4");//L4
    }
}

public class Main {
    public static void main(String[] args) {
        Person p = new Female();
        p.Sing(5);
    }
}
