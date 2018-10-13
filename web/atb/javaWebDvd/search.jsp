<%@ page import="web.atb.javaWebDvd.dvd.JDBCUtilDvd" %>
<%@ page import="web.atb.javaWebDvd.dvd.Dvd" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\13 0013
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看DVD</title>
</head>
<body>
<div align='center'>
    <form action='./search.jsp' method='post'>
        搜关键字：<input type='text' title="序号书名状态" name='search' />
        <input type='submit' value='搜索' />
    </form>
</div>

<form action='../../delete.do' method='post'>
    <table border="1" width="700" align = "center">
        <tr>
            <th>选择</th>
            <th>序号</th>
            <th>书名</th>
            <th>状态</th>
            <th>操作</th>
            <th>操作</th>
            <th>操作</th>
        </tr>
        <%
            List<Dvd> dvds;
            JDBCUtilDvd jdbcUtilDvd = new JDBCUtilDvd();
            if(request.getParameter("search") == null){
                dvds = jdbcUtilDvd.queryStu();
            }else{
                //post解决中文乱码
                request.setCharacterEncoding("utf-8");
                //遍历结果集
                dvds = jdbcUtilDvd.search(request.getParameter("search"));
            }
            for (Dvd dvd : dvds) {
        %>
        <tr>
            <!-- 输出结果集 -->
            <td><input type='checkbox' name='check' title='choice' value='<%= dvd.getNo() %>'/></td>
            <td><%= dvd.getNo() %></td>
            <td><%= dvd.getName() %></td>
            <td><%= dvd.getState() %></td>
            <td><a href = '../../lend.do?no=<%= dvd.getNo() %>'>借出</a></td>
            <td><a href = '../../return.do?no=<%= dvd.getNo() %>'>归还</a></td>
            <td><a href = './jsp/choice/modify.jsp?no=<%= dvd.getNo() %>'>修改</a></td>
        </tr>
        <%
            }
        %>
        <tr>
            <td colspan = '2'><input type="submit" value="删除" style="text-align: left;"></td>
            <td colspan = '2'><a href = './jsp/choice/add.jsp'>添加</a></td>
            <td colspan = '2'><a href="./login.jsp">退出</a></td>
            <td colspan = '1'><a href="../../login.do">显示所有</a></td>
        </tr>
        <script type="text/javascript">
            if(<%= request.getAttribute("MSG")!=null %>){
                alert('<%=request.getAttribute("MSG") %>');
            }
        </script>
    </table>
</form>
</body>
</html>
