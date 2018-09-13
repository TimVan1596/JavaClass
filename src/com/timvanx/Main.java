package com.timvanx;

public class Main {
    public static void main(String[] args) {
        Child child = new Child();
    }
}

class Parent {
    static {
        System.out.println("parent static code block");
    }

    public Parent() {
        System.out.println("parent constructor");
    }
}

class Child extends Parent {
    static {
        System.out.println("child static code block");
    }

    public Child() {
        System.out.println("child constructor");
    }
}