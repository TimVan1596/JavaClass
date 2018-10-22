<%@ page import="com.sun.java.swing.plaf.windows.resources.windows" %>
<%@ page import="com.smallfangyu.data.DbUtil" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/12 0012
  Time: 20:12
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
    String sql="DELETE FROM dvd WHERE dvdno=?";
    Object[] params={no};
    int res=db.executeUpdate(sql,params);
    if(res>0){
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<script language='javascript'>alert('DVD删除成功');window.parent.location.href='/fy/servlet/toShowDvd';</script>");

        //location.href='/fy/servlet/toShowDvd';
    }
%>

</body>
</html>
