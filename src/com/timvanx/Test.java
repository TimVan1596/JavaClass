package com.timvanx;


/**
 * 求取斐波那契数列的第一百个数
 * */
public class Test {
    public interface Flyable {
        void fly();

    }

    public class Bird implements Flyable{
        @Override
        public void fly(){
            System.out.println("I can fly");
        }
    }


}