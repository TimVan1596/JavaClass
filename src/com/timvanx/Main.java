package com.timvanx;

//1、简答题：简述this关键字,super关键字的用法
// this:
// (1)this调用本类中的属性，也就是类中的成员变量；
// (2)this调用本类中的其他方法；
// (3)this调用本类中的其他构造方法，调用时要放在构造方法的首行。
//super:super能够用来訪问超类的构造方法和被子类所隐藏的方法。

//2、定义一个类CalF，完成加、减方法，然
//后再定义一个派生类CalS，完成乘、除方法

class CalF{
    public double add(double a,double b) {
        double ret = a+b;
        return ret;
    }

    public double minis(double a,double b) {
        double ret = a-b;
        return ret;
    }
}

class CalS extends CalF{
    public double mutiple(double a,double b) {
        return a*b;
    }

    public String chu(double a,double b) {

        if (b==0) {
            return "分母为0";
        }
        Double ret = a/b;

        return ret.toString();
    }
}

