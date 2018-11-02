package com.antianbao.text;

import java.util.Scanner;
import java.util.regex.Pattern;

public class SimpleFactoryMode {
    public static void main(String[] args) {
        System.out.println("请输入第一个数字:");
        Scanner scanner=new Scanner(System.in);
        String num1 = scanner.nextLine();
        System.out.println("请输入运算符(+-*/):");
        String oper = scanner.nextLine();
        System.out.println("请输入第二个数字:");
        String num2 = scanner.nextLine();
        //通过工厂获取运算类
        Operator operator = OperatorFactory.getOperator(oper);
        if(operator!=null){
            if(isDouble(num1) && isDouble(num2)){
                double result = operator.getResult(Double.parseDouble(num1), Double.parseDouble(num2));
                System.out.println("结果是:"+result);
            }else{
                System.out.println("请输入正确的数字!");
            }
        }
    }
    //判断浮点数（double和float）
    private static boolean isDouble(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]+)?$");//数字,包括整数和浮点
        return pattern.matcher(str).matches();
    }
}

//运算类接口
interface Operator {
    //获取结果
    public double getResult(double num1,double num2);

}

//加法运算
class AddOperator implements Operator {
    @Override
    public double getResult(double num1, double num2) {
        return num1+num2;
    }
}

//减法运算
class SubOperator implements Operator {
    @Override
    public double getResult(double num1, double num2) {
        return num1+num2;
    }
}

//乘法
class MulOperator implements Operator {
    @Override
    public double getResult(double num1, double num2) {
        return num1*num2;
    }
}

//除法
class DivOperator implements Operator {
    @Override
    public double getResult(double num1, double num2) {
        if(num2!=0){
            return num1/num2;
        }else{
            System.out.println("除数不能为0");
            return 0;
        }
    }
}

//创建运算类工厂
class OperatorFactory {
    //返回运算类对象
    public static Operator getOperator(String oper){
        if(oper.equals("+")){
            return new AddOperator();
        }else if(oper.equals("-")){
            return new SubOperator();
        }else if(oper.equals("*")){
            return new MulOperator();
        }else if(oper.equals("/")){
            return new DivOperator();
        }else{
            System.out.println("目前不支持这种操作");
            return null;
        }
    }
}

