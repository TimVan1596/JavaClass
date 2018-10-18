package com.antianbao.sort;
/**
 * 排序
 * @author TinbaoAn
 * @date 2018/9/13
 */
public class Person implements Comparable<Person>{
    private int no;
    private String name;
    private int height;

    @Override
    public int compareTo(Person o){
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString(){
        return this.no+","+this.name+","+this.height;
    }

    public Person(int no, String name, int height) {
        this.no = no;
        this.name = name;
        this.height = height;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
