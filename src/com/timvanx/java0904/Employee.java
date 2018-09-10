package com.timvanx.java0904;

public class Employee {
    private int id;
    private String name;


    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.id = 1;
        System.out.println(emp.getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
