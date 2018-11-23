package com.antianbao.text.sort;

import java.util.*;
/**
 * 排序
 * @author TinbaoAn
 * @date 2018/9/13
 */
public class PersonSort {

    public static void main(String[] args) {
        PersonSort ps=new PersonSort();
        List<Person> list=ps.initPerson();

        ps.sortByNo(list);
        System.out.println("按照序号排序后");
        ps.printList(list);

        Collections.sort(list);
        System.out.println("按照姓名排序后");
        ps.printList(list);

        ps.sortByHeight(list);
        System.out.println("按照身高排序后");
        ps.printList(list);

        List<Person> list1 = ps.sortByNo(list);
        List<Person> list2 = ps.sortByHeight(list1);
        Collections.sort(list2);
        System.out.println("先排姓名 后排身高 最后序号");
        ps.printList(list);
    }

    public List<Person> initPerson() {
        List<Person> list=new ArrayList<>();
        list.add(new Person(1001,"Z张三",178));
        list.add(new Person(1002,"L李四",185));
        list.add(new Person(1004,"W王五",165));
        list.add(new Person(1003,"Z赵六",173));
        list.add(new Person(1005,"Z赵六",175));
        list.add(new Person(1006,"Z赵六",173));
        return list;
    }

    public List<Person> sortByNo(List<Person> list) {
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
