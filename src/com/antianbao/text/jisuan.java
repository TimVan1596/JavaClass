package com.antianbao.text;

import java.util.Scanner;

interface ShapeArea {
    /**
     * 求一个形状的面积
     */
    double doublegetArea();

    /**
     * 求一个形状的周长
     */
    double doublegetPerimeter();
}

/**
 * 求圆形
 */
class Circle implements ShapeArea {
    double r;
    double PI = 3.1415;

    public Circle(double r) {
        this.r = r;
    }

    @Override
    public double doublegetArea() {
        return PI * r * r;
    }

    @Override
    public double doublegetPerimeter() {
        return 2 * PI * r;
    }

    @Override
    public String toString() {
        return "半径：" + r
                + ",周长：" + doublegetPerimeter()
                + ",面积：" + doublegetArea();
    }
}

/**
 * 求长方形
 */
class Rectangle implements ShapeArea {
    double width;
    double heigth;

    public Rectangle(double width, double heigth) {
        this.width = width;
        this.heigth = heigth;
    }

    @Override
    public double doublegetArea() {
        return width * heigth;
    }

    @Override
    public double doublegetPerimeter() {
        return (width + heigth) * 2;
    }

    @Override
    public String toString() {
        return "宽度：" + width
                + ",长度：" + heigth
                + ",周长：" + doublegetPerimeter()
                + ",面积：" + doublegetArea();
    }
}

public class jisuan {
    public static void main(String[] args) {
        try{
            System.out.println("请选择需求的形状");
            System.out.println("----1.长方形----");
            System.out.println("-----2.圆形-----");
            Scanner s = new Scanner(System.in);
            int choice = s.nextInt();
            switch (choice){
                case 1:
                    System.out.println("请输入长方形的宽度：");
                    int width = s.nextInt();
                    System.out.println("请输入长方形的长度：");
                    int height = s.nextInt();
                    Rectangle ec = new Rectangle(width, height);
                    System.out.println(ec);
                    break;
                case 2:
                    System.out.println("请输入圆的半径：");
                    int r = s.nextInt();
                    Circle cr = new Circle(r);
                    System.out.println(cr);
                    break;
                default:
                    System.out.println("请输入正确的选择！");
                    break;
            }
        }catch (java.util.InputMismatchException e){
            System.out.println("请输入数字！");
        }
    }
}
