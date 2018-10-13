<%@ page import="web.atb.javaWebDvd.dvd.JDBCUtilDvd" %>
<%@ page import="web.atb.javaWebDvd.dvd.Dvd" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\12 0012
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示DVD</title>
</head>
<body>
<div align='center'>
    <form action='./atb/javaWebDvd/search.jsp' method='post'>
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
            //遍历结果集
            JDBCUtilDvd jdbcUtilDvd = new JDBCUtilDvd();
            List<Dvd> dvds = jdbcUtilDvd.queryStu();
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
            <td><a href = './atb/javaWebDvd/jsp/choice/modify.jsp?no=<%= dvd.getNo() %>'>修改</a></td>
        </tr>
        <%
            }
        %>
        <tr>
            <td colspan = '2'><input type="submit" value="删除" style="text-align: left;"></td>
            <td colspan = '2'><a href = './atb/javaWebDvd/jsp/choice/add.jsp'>添加</a></td>
            <td colspan = '2'><a href="./atb/javaWebDvd/login.jsp">退出</a></td>
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
