package com.timvanx.homework;

/**
 * @author TimVan
 * @date 2018/9/11 22:30
 */

class Person{
    private String name ;
    private int age ;

    public Person(String name){
        this.name =  name ;
    }
    public Person(String name , int age){
        //完成name属性赋值
        this(name);
        this.age = age ;
    }
}