package com.antianbao.text.sort;

import java.util.Comparator;
/**
 * 排序
 * @author TinbaoAn
 * @date 2018/9/13
 */
public class PersonComparatorByHeight implements Comparator<Person> {
    @Override
    public int compare(Person h1,Person h2){
        return h2.getHeight() - h1.getHeight();
    }

}
