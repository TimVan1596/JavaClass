package com.antianbao.filesort914;

import java.util.Comparator;
/**
 * 排序存入文件中
 *
 * @author TinbaoAn
 * @date 2018/9/14
 */
public class PersonComparatorByNo implements Comparator<Person> {
    @Override
    public int compare(Person h1, Person h2){
        int a = Integer.parseInt(h1.getNo());
        int b = Integer.parseInt(h2.getNo());

        return a - b ;
    }

}
