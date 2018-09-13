package com.timvanx.homework;

public class Father {
    private String name;
    private int age;
    private String cardNo;

    public Father() {

    }

    public Father(String name, int age, String cardNo) {
        this.name = name;
        this.age = age;
        this.cardNo = cardNo;
    }

    public void run() {
        System.out.println(name + "is running");
    }

    public static void main(String[] args) {
        Sun sun = new Sun("儿子",2,"110");
        sun.run();
    }
}

class Sun extends Father {
    public Sun(String name, int age, String cardNo) {
        // 使用super关键字调用父类的构造函数
        super(name,age,cardNo);

    }

    @Override
    public void run() {
        // 使用super关键字，先调用父类的run方法操作
        super.run();
        System.out.println("正在执行子类的run方法");
    }
}