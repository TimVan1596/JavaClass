package com.timvanx.homework;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TimVan
 * @date 2018/9/17 20:44
 */
class Shape implements Serializable {
}
class Line extends Shape {
}
class Square extends Shape {
}
public class Serialization {
    public static void main(String[] args) throws Exception {
        List<Shape> shapes = new ArrayList<Shape>();
        shapes.add(new Shape());
        shapes.add(new Line());
        shapes.add(new Square());
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("CADState.data"));
        out.writeObject(shapes);
        System.out.println(shapes);
        out.close();
    }
}
