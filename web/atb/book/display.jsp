<%@ page import="java.util.List" %>
<%@ page import="com.antianbao.book.JDBC" %>
<%@ page import="com.antianbao.book.book" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\12 0012
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理数据</title>
</head>
<body>
<div align='center'>
    <form action='./search.jsp' method='post'>
        <h4 >搜关键字：<input type='text' title="序号书名库存" name='search' style="width: 150px; height: 30px"/>
            <input type='submit' value='搜索' style="width: 50px; height: 30px"/>
        </h4>
    </form>
</div>
<form action='' method='post'>
    <table border="1" width="750" align = "center">
        <tr>
            <th>选择</th>
            <th>序号</th>
            <th>图片</th>
            <th>名称</th>
        </tr>
        <%
            //遍历结果集
            JDBC jdbcUtilDvd = new JDBC();
            List<book> books;
            //数据总数
            //查询的总条数num和页数pages和页面选择page1
            int num = 0,pages,page1;
            num = jdbcUtilDvd.findCount();
            if(num % book.PAGE_SIZE == 0){
                pages = num/book.PAGE_SIZE;
            }else{
                pages = num/book.PAGE_SIZE + 1;
            }

            if(request.getAttribute("page") == null){
                page1 = 1;
                books = jdbcUtilDvd.find(1);
            }else{
                page1 = (Integer) request.getAttribute("page");
                books = jdbcUtilDvd.find(page1);
            }
            for (book book : books) {
        %>
        <tr>
            <td align="center"><%= book.getBookid() %></td>
            <td align="center"><%= book.getBookname() %></td>
            <td align="center"><%= book.getDouble() %></td>
            <td align="center"><%= book.getDatetime() %></td>
        </tr>
        <%
            }
        %>
        <tr>
            <td colspan = '4' align="right">
                <a href = '../../webTestLogin.do?page=1'>首页</a>
                <%
                    if(page1 == 1){
                %>
                <a href = '../../webTestLogin.do?page=1'>上一页</a>
                <%
                }else{
                %>
                <a href = '../../webTestLogin.do?page=<%= page1-1%>'>上一页</a>
                <%
                    }
                %>
                <%
                    for(int i=1;i<pages+1;i++){
                %>
                <a href = '../../webTestLogin.do?page=<%= i %>'><%= i %></a>
                <%
                    }
                %>
                <%
                    if(page1 == pages){
                %>
                <a href = '../../webTestLogin.do?page=<%= pages%>'>下一页</a>
                <%
                }else{
                %>
                <a href = '../../webTestLogin.do?page=<%= page1+1%>'>下一页</a>
                <%
                    }
                %>
                <a href = '../../webTestLogin.do?page=<%= pages%>'>尾页</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
