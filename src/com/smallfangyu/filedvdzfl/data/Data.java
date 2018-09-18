package com.smallfangyu.filedvdzfl.data;

import com.smallfangyu.filedvdzfl.model.DVD;

import java.io.*;
import java.util.ArrayList;

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

			dvdWrite();
        }


	return dvds;
	}
	/*
	把dvds写进文件里
	*/
	public void dvdWrite(){
		File file=new File("F:\\DVD\\dvdzfl.txt");

		if(! file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw=new FileWriter(file);
			BufferedWriter br=new BufferedWriter(fw);
			for(DVD dvd:dvds) {
				br.write(dvd.getId()+","+dvd.getDvdname()+","+dvd.getState());
				br.newLine();
			}
			br.close();
			fw.close();
           }catch(Exception e){

			e.printStackTrace();
		}
	}
	/*
   把dvds写进文件里,添加dvd
   */
	public void dvdAdd(DVD dvd){
		File file=new File("F:\\DVD\\dvdzfl.txt");

		if(! file.getParentFile().exists()){
			file.getParentFile().mkdirs();
		}
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw=new FileWriter(file,true);
			BufferedWriter br=new BufferedWriter(fw);

				br.write(dvd.getId()+","+dvd.getDvdname()+","+dvd.getState());
				br.newLine();

			br.close();
			fw.close();
		}catch(Exception e){

			e.printStackTrace();
		}
	}
	//从文件读数据
	public void dvdRead() {

		File file = new File("F:\\DVD\\dvdzfl.txt");
		//判断父类文件是否存在
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		try {
			if (file.exists()) {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
               String info;
               while((info=br.readLine())!=null) {
               String[] infos=info.split(",");
               dvds.add(new DVD(Integer.valueOf(infos[0]),infos[1],infos[2]));
			   }
				br.close();
				fr.close();
			} else {
				file.createNewFile();
				dvdRead();
			}
		} catch (Exception e) {

		}

	}
}
