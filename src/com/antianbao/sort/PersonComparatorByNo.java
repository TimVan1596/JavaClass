package com.antianbao.sort;

import java.util.Comparator;

public class PersonComparatorByNo implements Comparator<Person> {

    public int compare(Person h1,Person h2){
        return h1.getNo() - h2.getNo();
    }

}
