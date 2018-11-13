package com.timvanx.algorithm.graphy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

/**
 * 无向图 类
 * @author TimVan
 * @date 2018年11月14日00:14:04
 * */
class UnDirectGraph {

    /**
     * elem = 权
     * vector = 顶点个数
     * edge = 边的个数
     * */
    private List<List<Integer>> elem;
    private int vector;
    private int edge;

    public UnDirectGraph(int vector, int edge) {
        this.vector = vector;
        this.edge = edge;
    }

    public void createVector(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入顶点的个数  ");


        System.out.println("请输入"+this.edge+"条边");
    }
}


/**
 *  建立有向图
 * @author TimVan
 * @date 2018/11/8 22:27
 */
public class Date1108 {


    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Date1108.class);
        int vector = 5;
        int edge = 5;
        UnDirectGraph unDirectGraph = new UnDirectGraph(vector,edge);


    }
}
