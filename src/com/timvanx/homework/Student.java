package com.timvanx.homework;


import java.text.Collator;
import java.util.*;

public class Student {

    /**
     * 个人信息
     */
    private int id;
    private String name;
    private int age;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        // 留个思考：
        // 集合里装入3个学生， 你能按照姓名排序（中文、英文） 输出名？
        // 按照年龄排序输出吗？
        //排序为升序，优先级： name > age > id
        Student student1 = new Student(1, "李雷", 22);
        Student student2 = new Student(2, "韩梅梅", 21);
        Student student3 = new Student(3, "隔壁老王", 48);
        Student student4 = new Student(4, "小方宇", 19);
        Student student5 = new Student(5, "小方宇", 2);
        Student student6 = new Student(6, "隔壁老王", 48);
        List<Student> studentArrayList = new ArrayList<>();

        studentArrayList.add(student1);
        studentArrayList.add(student2);
        studentArrayList.add(student3);
        studentArrayList.add(student4);
        studentArrayList.add(student5);
        studentArrayList.add(student6);

        //法一：使用比较器方法进行排序
        ComparatorStudent comparatorStudent =
                new ComparatorStudent();
        Collections.sort(studentArrayList, comparatorStudent);

        for (Student student : studentArrayList) {
            System.out.println(student.toString());
        }


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    /**
     * ComparatorStudent 使用比较器方法进行排序
     */
    private static class ComparatorStudent implements Comparator<Student> {
        public ComparatorStudent() {
        }

        /**
         * <p>Student类的比较器实现-升序</p>
         * <p>优先级：name(拼音) > id > age</p>
         */
        @Override
        public int compare(Student o1, Student o2) {
            //使用Collator进行本地化（默认为Locale.getDefault() ）
            Collator collator = Collator.getInstance();
            //对name进行拼音排序
            int flag = collator.getCollationKey(o1.getName()).compareTo(
                    collator.getCollationKey(o2.getName()));
            if (flag == 0) {
                flag = o1.getAge() - o2.getAge();
                if (flag == 0) {
                    flag = o1.getId() - o2.getId();
                }
            }

            //升序
            return flag;
        }
    }


}



