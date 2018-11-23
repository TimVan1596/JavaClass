package com.antianbao.text.filesort914;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.*;

/**
 * 排序存入文件中
 *
 * @author TinbaoAn
 * @date 2018/9/14
 * <p>
 * 读取initPerson
 * 写入print
 */
public class PersonSort {

    public static void main(String[] args) {
        PersonSort ps = new PersonSort();
        List<Person> list = readStrFormFile();

        //按照序号排序后
        List<Person> ls = ps.sortByNo(list);
        try {
            FileWriter fw = new FileWriter("E:\\JAVA\\java_direction_class\\src\\com\\antianbao\\filesort914\\print", true);
            fw.write("按照序号排序后\r\n");
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        addStudentInfo(ls);

        //按照姓名排序后
        List<Person> ls1 = ps.sortByName(list);
        try {
            FileWriter fw = new FileWriter("E:\\JAVA\\java_direction_class\\src\\com\\antianbao\\filesort914\\print", true);
            fw.write("按照姓名排序后\r\n");
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        addStudentInfo(ls1);

        //按照身高排序后
        List<Person> ls2 = ps.sortByHeight(list);
        try {
            FileWriter fw = new FileWriter("E:\\JAVA\\java_direction_class\\src\\com\\antianbao\\filesort914\\print", true);
            fw.write("按照身高排序后\r\n");
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        addStudentInfo(ls2);

        //先排姓名 后排身高 最后序号
        List<Person> list1 = ps.sortByNo(list);
        List<Person> list2 = ps.sortByHeight(list1);
        List<Person> ls3 = ps.sortByName(list2);
        try {
            FileWriter fw = new FileWriter("E:\\JAVA\\java_direction_class\\src\\com\\antianbao\\filesort914\\print", true);
            fw.write("先排姓名 后排身高 最后序号\r\n");
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        addStudentInfo(ls3);

    }

    /**
     * 读取文件
     */
    public static List<Person> readStrFormFile() {
        List<Person> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("E:\\JAVA\\java_direction_class\\src\\com\\antianbao\\filesort914\\initPerson"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] info = line.split(",");
                list.add(new Person(info[0], info[1], info[2]));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 写入文件
     */
    public static void addStudentInfo(List<Person> list) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\JAVA\\java_direction_class\\src\\com\\antianbao\\filesort914\\print", true));
            for (Person p : list) {
                bw.write(p + "\r\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按照名字排序
     */
    public List<Person> sortByName(List<Person> list) {
        Collections.sort(list);
        return list;
    }

    /**
     * 按照序号排序
     */
    public List<Person> sortByNo(List<Person> list) {
        Collections.sort(list, new PersonComparatorByNo());
        return list;
    }

    /**
     * 按照身高排序
     */
    public List<Person> sortByHeight(List<Person> list) {
        Collections.sort(list, new PersonComparatorByHeight());
        return list;
    }

}
