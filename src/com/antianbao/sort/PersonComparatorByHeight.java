package com.antianbao.sort;

import java.util.Comparator;

public class PersonComparatorByHeight implements Comparator<Person> {

    public int compare(Person h1,Person h2){
        return h2.getHeight() - h1.getHeight();
    }

}
