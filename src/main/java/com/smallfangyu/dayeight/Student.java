package com.smallfangyu.dayeight;

import java.text.Collator;
import java.util.List;

public class Student implements Comparable<Student> {
    private int stuId;
    private String stuName;
    private int stuAge;
    

    
	public int getStuAge() {
		return stuAge;
	}
	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}
	public Student(int stuId, String stuName, int stuAge) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
		this.stuAge = stuAge;
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public Student(int stuId, String stuName) {
		super();
		this.stuId = stuId;
		this.stuName = stuName;
	}
	public Student() {
		super();
	}
	/**
	 * 便于对象输出，任何对象 都继承 Obejct的toString()
	 */
	@Override
	public String toString() {
		return "[学号：" + this.stuId + ", 姓名:" + this.stuName +",年龄"+this.stuAge+ "]";
	}
	@Override
	public int compareTo(Student arg) {
		// TODO 自动生成的方法存根
		//getInstance 来为给定的语言环境获得适当的 Collator 对象
		Collator cmp = Collator.getInstance(java.util.Locale.CHINA);
		//int result=this.stuName.compareTo(arg.stuName);
		int result=cmp.compare(this.stuName,arg.stuName);
         if(result==0){
         	int resultt=this.stuAge-arg.stuAge;
         	if(resultt==0) {
				return this.stuId-arg.stuId;
             }else{
				return this.stuAge - arg.stuAge;
			}
        }
		//return this.stuName.compareTo(arg.stuName);
		return cmp.compare(this.stuName,arg.stuName);
	}
    
}
