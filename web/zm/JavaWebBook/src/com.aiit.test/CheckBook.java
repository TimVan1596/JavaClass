package com.aiit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aiit.model.Book;
import com.aiit.model.BookType;
import com.aiit.service.BookService;



/**
 * Servlet implementation class CheckBook
 */
@WebServlet("/CheckBook")
public class CheckBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookService bookService=new BookService();
		int id=Integer.parseInt(request.getParameter("id"));
		Book book=bookService.findBook(id);
		request.setAttribute("BOOK", book);
		request.getRequestDispatcher("modify.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		BookService bookService=new BookService();
		String param=request.getParameter("param");
		if(param.equals("add")){
			String bookname=request.getParameter("bookname");
			String bookauthor=request.getParameter("bookauthor");
			String bookprice=request.getParameter("bookprice");
			String booktime=request.getParameter("booktime");
			int typeid=Integer.parseInt(request.getParameter("type"));
			BookType bookType=new BookType(typeid);
			Book book=new Book( bookType, bookname, bookauthor, bookprice, booktime);
			bookService.addBook(book);
			List<Book> books=bookService.getAllBook();
			request.setAttribute("BOOKS",books);
			request.getRequestDispatcher("show.jsp").forward(request, response);
		} else if(param.equals("search")){
			String name=request.getParameter("searchs");
			if(!name.equals(" ")){
			List<Book> books=bookService.searchBook(name);
			request.setAttribute("BOOKS",books);
			request.getRequestDispatcher("search.jsp").forward(request, response);
			}else{
				List<Book> books=bookService.getAllBook();
				request.setAttribute("Books",books);
				request.getRequestDispatcher("search.jsp").forward(request, response);
			}
		}else if(param.equals("delete")){
			String[] id =request.getParameterValues("select");		
			boolean flag=bookService.deleteBook(id);
			if(flag){
				List<Book> books=bookService.getAllBook();
				 request.getSession().setAttribute("BOOKS", books);
				 response.sendRedirect("show.jsp"); 
		         //out.print("<script>alert('删除成功！');</script>"); 
			}else{
			     //out.print("<script>alert('删除失败！');</script>");
			}
		}
		else if(param.equals("modify")){
			int bookid=Integer.parseInt(request.getParameter("bookid"));
			String bookname=request.getParameter("bookname");
			String bookauthor=request.getParameter("bookauthor");
			String bookprice=request.getParameter("bookprice");
			String booktime=request.getParameter("booktime");
			int typeid=Integer.parseInt(request.getParameter("type"));
			BookType bookType=new BookType(typeid);
			Book book=new Book(bookid, bookType, bookname, bookauthor, bookprice, booktime);
			bookService.modifyBook(book);
			List<Book> books=bookService.getAllBook();
			request.setAttribute("BOOKS",books);
			request.getRequestDispatcher("show.jsp").forward(request, response);
		}
	}

}
