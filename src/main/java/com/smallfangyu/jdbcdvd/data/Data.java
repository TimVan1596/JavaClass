package com.smallfangyu.jdbcdvd.data;

import com.smallfangyu.jdbcdvd.model.DVD;

import java.util.*;

public class Data {

	ArrayList<DVD> dvds = new ArrayList<DVD>();
	DbUtil db = new DbUtil();

	/**
	 * 把数据库里DVD的信息添加进集合里
	 *
	 * @return
	 */
	public ArrayList<DVD> dvdList() {
		ArrayList<String> tableSelect=new ArrayList<String>(){
			{
				add("dvdno");
				add("dvdname");
				add("state");
			}
		};
		List<List<String>> list=db.select(tableSelect,"dvd",null,null,null);

		for(List<String> li:list) {
            dvds.add(new DVD(Integer.parseInt(li.get(0)), li.get(1), li.get(2)));
        }

		return dvds;
	}

}
