package com.smallfangyu.dayeight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.smallfangyu.dayeight.Student;

public class TestCollections {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		  List<String>  names=new  ArrayList<String>();
		  names.add("China");
		  names.add("USA");
		  names.add("English");
		  //按照首字母 排序
		  //回忆： Arrays 数组操作类
//		   Object[] cc = names.toArray();
//		  Arrays.sort(cc);
//		  for (int i = 0; i < cc.length; i++) {
//			System.out.println(cc[i]);
//		}
		//如今： Collections  集合操作类
		  Collections.sort(names); //根据其元素的natural ordering对指定的列表进行排序
		  for (String name : names) {
			System.out.println(name);
		}
		  // 留个思考： 集合里装入3个学生， 你能按照姓名排序（中文、英文） 输出名？ 按照年龄排序输出吗？
		//构建2个学生
		Student student1 = new Student(1, "李雷", 22);
		Student student2 = new Student(2, "韩梅梅", 21);
		Student student3 = new Student(3, "隔壁老王", 48);
		Student student4 = new Student(4, "小明", 19);
		Student student5 = new Student(5, "小明", 2);
		Student student6 = new Student(6, "隔壁老王", 48);
		List<Student> students=new ArrayList<Student>();
			students.add(student1);
			students.add(student2);
			students.add(student3);
		    students.add(student4);
		    students.add(student5);
		    students.add(student6);
			Collections.sort(students);
			for (Student student : students) {
				System.out.println(student);
			}
		  
	}

}
