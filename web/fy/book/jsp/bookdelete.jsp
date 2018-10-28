<%@ page import="com.smallfangyu.bookjavaweb.DbUtil" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/27 0027
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    DbUtil db=new DbUtil();
    int no=Integer.parseInt(request.getParameter("no"));
    String sql="DELETE FROM book WHERE id=?";
    Object[] params={no};
    int res=db.executeUpdate(sql,params);
    if(res>0){
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<script language='javascript'>alert('BOOK删除成功');window.location.href='/book/showbook';</script>");

        //location.href='/fy/servlet/toShowDvd';
    }
%>
</body>
</html>
