package com.antianbao.text;

import java.util.*;

public class ceshi {
    public static void main(String[]args){
        List al = new ArrayList();
        al.add("A");
        al.add("B");
        for(int i =0;i < al.size();i++){
            String str = al.get(i).toString();
            System.out.print(str);
        }
    }
}
