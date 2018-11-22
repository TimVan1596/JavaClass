package com.timvanx.algorithm.graphy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private int[][] elem;
    private int vector;
    private int edge;

    public UnDirectGraph(int vector, int edge) {
        this.vector = vector;
        this.edge = edge;
        elem = new int[vector][vector];

        drawEdge();
    }

    //绘制无向图的边
    private void drawEdge(){
        Scanner scanner = new Scanner(System.in);


        System.out.println("请输入"+this.edge+"条边的起点和终点");
        for (int i = 0; i <edge ; i++) {
            int edgeVecStart = 0,edgeVecEnd = 0;
            System.out.println("请输入第"+i+"条边的起点");
            edgeVecStart = scanner.nextInt();
            System.out.println("第"+i+"条边的终点");
            edgeVecEnd = scanner.nextInt();
            elem[edgeVecStart][edgeVecEnd] = 1;
            elem[edgeVecEnd][edgeVecStart] = 1;


        }
    }

    //打印无向图（邻接矩阵）
    public void printUnDirectGraph(){
        for (int i = 0; i < vector; i++) {
            for (int j = 0; j < vector; j++) {
                System.out.print(elem[i][j]+" \t");
            }
            System.out.println();
        }

        System.out.println();
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
        unDirectGraph.printUnDirectGraph();


    }
}
