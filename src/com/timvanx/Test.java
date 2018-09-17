package com.timvanx;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class Person {}

public class Test implements Serializable {
    private Person p = new Person();
    public static void main(String[] args) {
        Test t = new Test();
        try {
            FileOutputStream fos = new FileOutputStream("person.dat");
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(t);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}