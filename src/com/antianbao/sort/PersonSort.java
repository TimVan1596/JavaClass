package com.antianbao.sort;

import java.util.*;

public class PersonSort {

    public static void main(String[] args) {
        PersonSort ps=new PersonSort();
        List<Person> list=ps.initPerson();
        ps.sortByAge(list);
        System.out.println("按照序号排序后");
        ps.printList(list);

        ps.sortByHeight(list);
        System.out.println("按照身高排序后");
        ps.printList(list);
    }

    public List<Person> initPerson() {
        List<Person> list=new ArrayList<Person>();
        list.add(new Person(1001,"张三",178));
        list.add(new Person(1002,"李四",185));
        list.add(new Person(1004,"王五",165));
        list.add(new Person(1003,"赵六",173));
        return list;
    }

    public List<Person> sortByAge(List<Person> list) {
        Collections.sort(list,new PersonComparatorByNo());
        return list;
    }

    public List<Person> sortByHeight(List<Person> list) {
        Collections.sort(list,new PersonComparatorByHeight());
        return list;
    }

    public void printList(List<Person> list) {
        for(Person p:list){
            System.out.println(p);
        }
    }
}
