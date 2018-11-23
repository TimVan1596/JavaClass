package com.antianbao.text.filesort914;
/**
 * 排序
 * @author TinbaoAn
 * @date 2018/9/14
 */
public class Person implements Comparable<Person>{
    private String no;
    private String name;
    private String height;

    @Override
    public int compareTo(Person o){
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString(){
        return this.no+","+this.name+","+this.height;
    }

    public Person(String no, String name, String height) {
        this.no = no;
        this.name = name;
        this.height = height;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
