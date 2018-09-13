package com.antianbao.sort;

import java.util.Comparator;
/**
 * 排序
 * @author TinbaoAn
 * @date 2018/9/13
 */
public class PersonComparatorByNo implements Comparator<Person> {
    @Override
    public int compare(Person h1,Person h2){
        return h1.getNo() - h2.getNo();
    }

}
