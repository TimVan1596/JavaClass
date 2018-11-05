package com.aiit.service;

import java.util.List;

import com.aiit.dao.BookDao;
import com.aiit.model.Book;

public class BookService {
	BookDao bookDao=new BookDao();
public List<Book> getAllBook(){
	return bookDao.getAllBooks();	
}
public void addBook(Book book){
	bookDao.insertBook(book);
}
public List<Book> searchBook(String name){
	return bookDao.searchBooks(name);	
}
public boolean deleteBook(String[] id){
	return bookDao.deleteBook(id);
	
}
public Book findBook( int id){
	return bookDao.findBooks(id);
	
}
public void modifyBook(Book book){
	bookDao.updateBook(book);
}
}
