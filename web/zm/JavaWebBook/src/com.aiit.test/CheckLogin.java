package com.aiit.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aiit.model.Book;
import com.aiit.model.Login;
import com.aiit.service.BookService;
import com.aiit.service.LoginService;



/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/Login")
public class CheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name=request.getParameter("username");
		String password=request.getParameter("pwd");
		Login login=new Login(name,password);
		LoginService loginService=new LoginService();
		boolean isok=loginService.checkLogin(login);
		if(isok){
			BookService bookservice=new BookService();
			List<Book> books=bookservice.getAllBook();
			  request.getSession().setAttribute("BOOKS",books);
				request.getSession().setAttribute("LOGIN",login);
				   response.sendRedirect("show.jsp");
		}else{
			 request.getSession().setAttribute("MSG", "用户名或密码不正确");
	      	   response.sendRedirect("login.jsp"); 
		}
		
	}

}
