package com.antianbao.mysql920;

import java.util.List;

/**
 * @author 安天宝
 * JAVA一班
 * 10月9日
 */
public class Test {
    public static void main(String[] args){
        JDBCUtilBook jdbcUtilBook = new JDBCUtilBook();
        List<BookInfo> books = jdbcUtilBook.queryStu();
        for (BookInfo book : books) {
            if(book.getPrice() > 50 && book.getPrice() < 100){
                System.out.println(book);
            }
        }
        double sum = 0;
        for (BookInfo book : books) {
            if(book.getTypeId() == 1){
                sum = sum + book.getPrice();
            }
        }
        System.out.println(sum);
    }
}
