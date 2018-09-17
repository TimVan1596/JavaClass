package com.smallfangyu.filedvd.data;

import com.smallfangyu.filedvd.model.DVD;

import java.util.ArrayList;
import java.io.*;

public class Data {
	
    ArrayList<DVD> dvds = new ArrayList<DVD>();
    public Data(){
		dvdRead();
    }
	public ArrayList<DVD> show(){
        if(dvds.size()==0) {
	DVD dvd1 = new DVD(1000, "《大校的女儿》", "已借出");
	DVD dvd2 = new DVD(1001, "《恰同学少年》", "可以借");
	DVD dvd3 = new DVD(1002, "《士兵突击》", "已借出");
	DVD dvd4 = new DVD(1003, "《士兵突击》", "可以借");
	dvds.add(dvd1);
	dvds.add(dvd2);
	dvds.add(dvd3);
	dvds.add(dvd4);
        }
	dvdWrite();

	return dvds;
	}
	/*
	把dvd集合写进文件里
	*/
	public void dvdWrite(){
		File file=new File("F:\\DVD\\dvd.txt");

		if(! file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fis=new FileOutputStream(file);
			ObjectOutputStream oit=new ObjectOutputStream(fis);
			 oit.writeObject(dvds);
			oit.close();

		}catch(Exception e){

			e.printStackTrace();
		}
	}

	//从文件读数据
	public void dvdRead() {

		File file = new File("F:\\DVD\\dvd.txt");
		//判断父类文件是否存在
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		try {
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream oit = new ObjectInputStream(fis);

				dvds = (ArrayList<DVD>) oit.readObject();
				oit.close();
				fis.close();
			} else {
				file.createNewFile();
				dvdRead();
			}
		} catch (Exception e) {

		}

	}
}
