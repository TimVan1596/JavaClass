package com.smallfangyu.bookjavaweb;

import java.util.List;

public interface IBook {
    public List<Book> getUserList();

    public void insertUser(Book book);

    public void updateUser(Book book);

    public void deleteUser(int bookId);

    public Book getUser(int id);
}
