package com.smallfangyu.filedvd.data;

import com.smallfangyu.filedvd.model.DVD;

import java.util.ArrayList;


public class Data {
	
    ArrayList<DVD> dvds = new ArrayList<DVD>();
    
	public ArrayList<DVD> show(){
	DVD dvd1 = new DVD(1000, "《大校的女儿》", "已借出");
	DVD dvd2 = new DVD(1001, "《恰同学少年》", "可以借");
	DVD dvd3 = new DVD(1002, "《士兵突击》", "已借出");
	DVD dvd4 = new DVD(1003, "《士兵突击》", "可以借");
	dvds.add(dvd1);
	dvds.add(dvd2);
	dvds.add(dvd3);
	dvds.add(dvd4);
	return dvds;
	}
}
