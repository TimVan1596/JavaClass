package com.timvanx;

import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Rodent[] animals = new Rodent[4];
        animals[0] = new Rodent(){ };
        animals[1] = new Mouse();
        animals[2] = new Gebil();
        animals[3] = new Hamster();

        for(Rodent r : animals){
            r.Eat();
        }
    }
}

abstract class Rodent{
    public void Eat(){
        System.out.println("rodent is eating");
    }
}

class Mouse extends Rodent{
    @Override
    public void Eat() {
        System.out.println("mouse is eating");
    }
}

class Gebil extends Mouse{
    @Override
    public void Eat() {
        System.out.println("gebil is eating");
    }
}

class Hamster extends Gebil{
    @Override
    public void Eat() {
        System.out.println("hamster is eating");
    }
}