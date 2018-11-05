package com.aiit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aiit.common.JDBCUtils;
import com.aiit.model.Book;
import com.aiit.model.BookType;



public class BookDao {
	Connection conn = JDBCUtils.getConnection();
	PreparedStatement pst=null;
    Book book=null;
	ResultSet rs=null;
	List<Book> books=new ArrayList<Book>();
	public List<Book> getAllBooks(){
		String sql="select bookID,typeName,bookname,bookauthor,bookprice,booktime from bookinfo,booktype where bookinfo.typeid=booktype.typeID";
		try {
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next()){
				int bookid=rs.getInt(1);
				String typename=rs.getString(2);
				String bookname=rs.getString(3);
				String bookauthor=rs.getString(4);
				String bookprice=rs.getString(5);
				String booktime=rs.getString(6);
				BookType bookType=new BookType(typename);
				book=new Book (bookid, bookType,  bookname, bookauthor,  bookprice, booktime);
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
		
	}
	public void insertBook(Book book){
		String sql="insert into bookinfo(bookname,bookauthor,bookprice,booktime,typeid)values(?,?,?,?,?)";
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, book.getBookname());
			pst.setString(2, book.getBookauthor());
			pst.setString(3, book.getBookprice());
			pst.setString(4,  book.getBooktime());
			pst.setInt(5, book.getType().getTypeid());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Book> searchBooks(String name){
		String sql="select bookID,typeName,bookname,bookauthor,bookprice,booktime from bookinfo,booktype where bookinfo.typeid=booktype.typeID and bookname like ?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, "%"+name+"%");
			rs=pst.executeQuery();
			while(rs.next()){
				int bookid=rs.getInt(1);
				String typename=rs.getString(2);
				String bookname=rs.getString(3);
				String bookauthor=rs.getString(4);
				String bookprice=rs.getString(5);
				String booktime=rs.getString(6);
				BookType bookType=new BookType(typename);
				book=new Book (bookid, bookType,  bookname, bookauthor,  bookprice, booktime);
				books.add(book);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;	
	}
	public boolean deleteBook(String[] id){
		 boolean flag=false;
         String sql="delete from bookinfo where bookID = ?";
         try {
        	 conn.setAutoCommit(false);
			pst=conn.prepareStatement(sql);
			if(id.length>0){
			for(int i=0;i<id.length;i++){
				pst.setInt(1, Integer.parseInt(id[i]));
				pst.addBatch();
			}
			pst.executeBatch();
			conn.commit();
			flag=true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		return flag;
	}
	public Book findBooks( int id){
		String sql="select bookID,typeName,bookname,bookauthor,bookprice,booktime from bookinfo,booktype where bookinfo.typeid=booktype.typeID and bookID=? ";
		try {
			pst=conn.prepareStatement(sql);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			while(rs.next()){
				int bookid=rs.getInt(1);
				String typename=rs.getString(2);
				String bookname=rs.getString(3);
				String bookauthor=rs.getString(4);
				String bookprice=rs.getString(5);
				String booktime=rs.getString(6);
				BookType bookType=new BookType(typename);
				book=new Book (bookid, bookType,  bookname, bookauthor,  bookprice, booktime);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book;
		
		
	}
	public void updateBook(Book book){
		String sql="update bookinfo set bookname=?,bookauthor=?,bookprice=?,booktime=?,typeId=? where bookID=?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, book.getBookname());
			pst.setString(2, book.getBookauthor());
			pst.setString(3, book.getBookprice());
			pst.setString(4, book.getBooktime());
			pst.setInt(5, book.getType().getTypeid());
			pst.setInt(6, book.getBookid());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
