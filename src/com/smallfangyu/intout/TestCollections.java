package com.smallfangyu.intout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.smallfangyu.intout.Student;


public class TestCollections {

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

//		  ArrayList<String>  names=new  ArrayList<String>();
//		  names.add("China");
//		  names.add("USA");
//		  names.add("English");
		  //按照首字母 排序
		  //回忆： Arrays 数组操作类
//		   Object[] cc = names.toArray();
//		  Arrays.sort(cc);
//		  for (int i = 0; i < cc.length; i++) {
//			System.out.println(cc[i]);
//		}
		//如今： Collections  集合操作类
//		  Collections.sort(names); //根据其元素的natural ordering对指定的列表进行排序
//		  for (String name : names) {
//			System.out.println(name);
//		}
		  // 留个思考： 集合里装入3个学生， 你能按照姓名排序（中文、英文） 输出名？ 按照年龄排序输出吗？
		//构建2个学生
		
			Student student1=new Student(1, "李雷",22);
			Student student2=new Student(2, "韩梅梅",21);
			Student student3=new Student(3, "吉姆",23);
			
			//创建文件路径
			File file=new File("F:\\新建文件夹\\test.txt");
			//判断目录文件夹是否存在
			if(!(file.getParentFile().exists())) {
				file.getParentFile().mkdirs();
			}
			
			FileOutputStream fos=new FileOutputStream(file);
			
			try {
				ArrayList<Student> students=new ArrayList<Student>();
				students.add(student1);
				students.add(student2);
				students.add(student3);
			ObjectOutputStream out=new ObjectOutputStream(fos);
			
			out.writeObject(students);
			out.close();
			}catch(FileNotFoundException e){
		        e.printStackTrace();
		      }catch(IOException e){
		        e.printStackTrace();
		      }
		      
			
			
			FileInputStream fis=new FileInputStream(file);
			try {
				
				ObjectInputStream in=new ObjectInputStream(fis);
				ArrayList<Student> students=(ArrayList<Student>) in.readObject();
				in.close();
				for(Student stu:students) {
				 System.out.println(stu);}
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	        
			
//			 Collections.sort(students);
//			for (Student student : students) {
//				System.out.println(student);
//			}
		  
	}

}
