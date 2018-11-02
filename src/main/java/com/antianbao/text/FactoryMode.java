package com.antianbao.text;

import java.util.Scanner;

public class FactoryMode {
    public static void main(String[] args) {
        System.out.println("请输入第一个操作数：");
        Scanner scanner = new Scanner(System.in);
        String num1 = scanner.nextLine();
        System.out.println("请输入操作符:");
        String oper = scanner.nextLine();
        System.out.println("请输入第二个操作数：");
        String num2 = scanner.nextLine();
        double result = 0.0;
        CalculatorFactory cf = null;
        if ("+".equals(oper)) {
            cf = new AddFactory();
            Calculator add = cf.getCalclator();
            add.setNum1(Double.parseDouble(num1));
            add.setNum2(Double.parseDouble(num2));
            result = add.getResult();
        } else if ("-".equals(oper)) {
            cf = new SubFactory();
            Calculator add = cf.getCalclator();
            add.setNum1(Double.parseDouble(num1));
            add.setNum2(Double.parseDouble(num2));
            result = add.getResult();
        } else if ("*".equals(oper)) {
            cf = new MulFactory();
            Calculator add = cf.getCalclator();
            add.setNum1(Double.parseDouble(num1));
            add.setNum2(Double.parseDouble(num2));
            result = add.getResult();
        } else if ("/".equals(oper)) {
            cf = new DivFactory();
            Calculator add = cf.getCalclator();
            add.setNum1(Double.parseDouble(num1));
            add.setNum2(Double.parseDouble(num2));
            result = add.getResult();
        } else {
            result = -9999;
        }
        System.out.println(num1 + oper + num2 + "=" + result);
    }
}

abstract class Calculator {
    private double num1;
    private double num2;
    public double getNum1() {
        return num1;
    }
    void setNum1(double num1) {
        this.num1 = num1;
    }
    public double getNum2() {
        return num2;
    }
    void setNum2(double num2) {
        this.num2 = num2;
    }
    public abstract double getResult();
}

//加法
class AddCalculator extends Calculator{
    @Override
    public double getResult() {
        return this.getNum1()+this.getNum2();
    }
}

//减法
class SubCalculator extends Calculator{
    @Override
    public double getResult() {
        return this.getNum1()-this.getNum2();
    }
}

//乘法
class MulCalculator extends Calculator{
    @Override
    public double getResult() {
        return this.getNum1()*this.getNum2();
    }
}

//除法
class DivCalculator extends Calculator{
    @Override
    public double getResult() {
        return this.getNum1()/this.getNum2();
    }
}

interface CalculatorFactory {
    Calculator getCalclator ();
}

//加法
class AddFactory implements CalculatorFactory{
    @Override
    public Calculator getCalclator() {
        return new AddCalculator();
    }
}

//减法
class SubFactory implements CalculatorFactory{
    @Override
    public Calculator getCalclator() {
        // TODO Auto-generated method stub
        return new SubCalculator();
    }
}

//乘法
class MulFactory implements CalculatorFactory{
    @Override
    public Calculator getCalclator() {
        return new MulCalculator();
    }
}

//除法
class DivFactory implements CalculatorFactory{
    @Override
    public Calculator getCalclator() {
        return new DivCalculator();
    }
}